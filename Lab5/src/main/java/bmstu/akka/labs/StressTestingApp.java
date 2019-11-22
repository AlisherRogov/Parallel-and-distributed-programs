package bmstu.akka.labs;


import akka.NotUsed;
import akka.actor.ActorSystem;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.ConnectHttp;
import akka.http.javadsl.Http;
import akka.http.javadsl.ServerBinding;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;
import jdk.internal.util.xml.impl.Pair;

import java.io.IOException;


import java.net.http.HttpResponse;
import java.util.Map;
import java.util.concurrent.CompletionStage;


public class StressTestingApp {

    public static void main(String[] args) throws IOException {
        ActorSystem system = ActorSystem.create("routes");
        final Http http = Http.get(system);
        final ActorMaterializer materializer = ActorMaterializer.create(system);

       final Flow<HttpRequest, HttpResponse, NotUsed> routeFlow = Flow.of(HttpRequest.class)
               .map(request -> {
                       String url = request.getUri().query().get("testURL").orElse("ufc.com");
                       Integer count = Integer.parseInt(request.getUri().query().get("count").orElse("10"));
                       return new Pair<>(url, count);
               })
               .mapAsync(4, pair ->
                       Patt)



        final CompletionStage<ServerBinding> binding = http.bindAndHandle(
                routeFlow,
                ConnectHttp.toHost("localhost", 8080),
                materializer
        );
        System.out.println("Server online at http://localhost:8080/\nPress RETURN to stop...");
        System.in.read();
        binding
                .thenCompose(ServerBinding::unbind)
                .thenAccept(unbound -> system.terminate()); // and shutdown when done

    }
}
