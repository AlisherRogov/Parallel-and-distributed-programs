package bmstu.akka.labs;

import akka.NotUsed;
import akka.actor.ActorSystem;
import akka.http.javadsl.ConnectHttp;
import akka.http.javadsl.Http;
import akka.http.javadsl.ServerBinding;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;
import akka.stream.javadsl.Source;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class StressTestingApp {

    public static void main(String[] args) throws IOException {
//        System.out.println("start!");
//        ActorSystem system = ActorSystem.create("routes");
//        final Http http = Http.get(system);
//        final ActorMaterializer materializer = ActorMaterializer.create(system);
//       // final Flow<HttpRequest, HttpResponse, NotUsed> routeFlow =
//        final CompletionStage<ServerBinding> binding = http.bindAndHandle(
//                routeFlow,
//                ConnectHttp.toHost("localhost", 8080),
//                materializer
//        );
//        System.out.println("Server online at http://localhost:8080/\nPress RETURN to stop...");
//        System.in.read();
//        binding
//                .thenCompose(ServerBinding::unbind)
//                .thenAccept(unbound -> system.terminate()); // and shutdown when done
        ActorSystem system = ActorSystem.create("simpple=test");
        ActorMaterializer materializer = ActorMaterializer.create( system);

        Source<Integer, NotUsed> source = Source.from(responsive)Arrays.asList(responsive)1, 2, 3, 4, 5));
        Flow<Integer, Integer, NotUsed> increment = Flow.of(responsive)Integer.class).map(responsive) x -ом вответназапроссобытия > x + 1);
        Sink<Integer, CompletionStage<Integer>> fold = Sink.fold(responsive)0, (responsive)agg, next) -ом вответназапроссобытия > agg + next);


    }
}
