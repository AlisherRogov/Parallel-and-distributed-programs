package bmstu.akka.labs;

import akka.NotUsed;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.ConnectHttp;
import akka.http.javadsl.Http;
import akka.http.javadsl.ServerBinding;
import akka.pattern.Patterns;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;
import akka.stream.javadsl.Keep;
import akka.stream.javadsl.Sink;
import akka.stream.javadsl.Source;
import bmstu.akka.labs.Actors.StorageActor;
import bmstu.akka.labs.Messages.GetTestResult;
import bmstu.akka.labs.Messages.ResponseTestResult;
import bmstu.akka.labs.Messages.StoreTestResult;
import org.asynchttpclient.AsyncHttpClient;
import akka.http.javadsl.model.HttpResponse;

import java.io.IOException;
import java.time.Duration;
import java.util.Collections;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static org.asynchttpclient.Dsl.asyncHttpClient;


public class StressTestingApp {
    private static final String URL_KEY = "testUrl";
    private static final String COUNT_KEY = "count";

    private static final String DEFAULT_URL = "http://bmstu.ru/";
    private static final String DEFAULT_COUNT = "1";

    private static final int TIMEOUT_MS = 5000;
    static final String HOST = "http://localhost";
    private static final int PORT = 8080;

    public static void main(String[] args) throws IOException {
        ActorSystem system = ActorSystem.create("routes");
        ActorRef storeRef = system.actorOf(Props.create(StorageActor.class));

        final Http http = Http.get(system);
        final ActorMaterializer materializer = ActorMaterializer.create(system);

       final Flow<HttpRequest, HttpResponse, NotUsed> routeFlow = Flow.of(HttpRequest.class)
               .map(request -> {
                       String url = request.getUri().query().get(URL_KEY).orElse(DEFAULT_URL);
                       Integer count = Integer.parseInt(request.getUri().query().get(COUNT_KEY).orElse(DEFAULT_COUNT));
                       return new GetTestResult(url, count);
               })
               .mapAsync(4, pair ->
                       Patterns.ask(storeRef, pair, Duration.ofMillis(TIMEOUT_MS))
                               .thenCompose(msg -> {
                                   ResponseTestResult response = (ResponseTestResult) msg;
                                   if (response.isCounted()) {
                                       return CompletableFuture.completedFuture(response);
                                   }
                                   return Source.from(Collections.singletonList(pair))
                                           .toMat(testSink(), Keep.right()).run(materializer)
                                           .thenCompose(time -> CompletableFuture.completedFuture(
                                                   new ResponseTestResult(false, pair.getUrl(), time / pair.getCount())));
                               }))
               .map(response -> {
                   if(!response.isCounted()) {
                       StoreTestResult storeTestResult = new StoreTestResult(response.getUrl(), response.getTime());
                       storeRef.tell(storeTestResult, ActorRef.noSender());
                   }
                   return HttpResponse.create().withStatus(200).withEntity(response.getTime() + "ms");
               });


        final CompletionStage<ServerBinding> binding = http.bindAndHandle(
                routeFlow,
                ConnectHttp.toHost(HOST, PORT),
                materializer
        );
        System.out.println("Server online at http://localhost:8080/\nPress RETURN to stop...");
        System.in.read();
        binding
                .thenCompose(ServerBinding::unbind)
                .thenAccept(unbound -> system.terminate()); // and shutdown when done

    }


    static final Sink<GetTestResult, CompletionStage<Long>> testSink() {
        return Flow.<GetTestResult>create()
                .mapConcat(msg -> Collections.nCopies(msg.getCount(), msg.getUrl()))
                .mapAsync(4,  pair -> {
                    Long startTime = System.currentTimeMillis();
                    AsyncHttpClient asyncHttpClient = asyncHttpClient();
                    return asyncHttpClient
                            .prepareGet(pair)
                            .execute()
                            .toCompletableFuture()
                            .thenCompose(response ->
                                    CompletableFuture.completedFuture(System.currentTimeMillis() - startTime));
                }).toMat(Sink.fold(0L, Long::sum), Keep.right());
    }
}
