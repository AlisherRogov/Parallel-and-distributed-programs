package bmstu.akka.labs;

import akka.NotUsed;
import akka.actor.ActorSystem;
import akka.actor.Cancellable;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.*;
import jdk.internal.util.xml.impl.Pair;
import scala.concurrent.duration.FiniteDuration;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;

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
        ActorSystem system = ActorSystem.create("simpple-test");
        ActorMaterializer materializer = ActorMaterializer.create( system);
        Source<Integer, Cancellable> source = Source.tick(
        FiniteDuration.create( 0, TimeUnit.SECONDS),
        FiniteDuration.create( 100, TimeUnit.MILLISECONDS), 1);
        Source<Integer, Cancellable> incremented = source.map( x -> x + 1);
        Sink<Integer, CompletionStage<Integer>> fold = Sink.fold( 0, ( agg, next) -> agg + next);
        RunnableGraph<Pair<Cancellable, CompletionStage<Integer>>> graph =
                incremented.toMat(fold, Keep.both());// 0
        Pair<Cancellable, CompletionStage<Integer>> run = graph.run( materializer);
        Thread.sleep( 2000);
        run.second().thenAccept( i -> System.out.println("result=" + i));
        run.first().cancel();
        system.terminate();


    }
}
