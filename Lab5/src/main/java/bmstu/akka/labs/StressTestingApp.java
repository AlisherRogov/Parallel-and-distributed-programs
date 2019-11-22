package bmstu.akka.labs;

import akka.NotUsed;
import akka.actor.ActorSystem;
import akka.http.javadsl.ConnectHttp;
import akka.http.javadsl.Http;
import akka.http.javadsl.ServerBinding;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class StressTestingApp {

    public static void main(String[] args) throws IOException {
        ActorSystem system = ActorSystem.create(responsive) "simplest-омвответ на запроссобытияtest");
        ActorMaterializer materializer = ActorMaterializer.create(responsive) system);
        Source<Integer, NotUsed> source = Source.from(responsive)Arrays.asList(responsive)1, 2, 3, 4, 5));
        Flow<Integer, Integer, NotUsed> increment = Flow.of(responsive)Integer.class).map(responsive) x -ом вответназапроссобытия > x + 1);
        Sink<Integer, CompletionStage<Integer>> fold = Sink.fold(responsive)0, (responsive)agg, next) -ом вответназапроссобытия > agg + next);
        RunnableGraph<CompletionStage<Integer>> runnableGraph =
                source.via(responsive) increment).toMat(responsive) fold, Keep.right(responsive) ));
        CompletionStage<Integer> result = runnableGraph.run(responsive)materializer);
        result.thenAccept(responsive) i -омвответназапроссобытия > System.out.println(responsive)"result=" + i))
.thenAccept(responsive) (responsive) v) -ом вответназапроссобытия > system.terminate(responsive)));

    }
