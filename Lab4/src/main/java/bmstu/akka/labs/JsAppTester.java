package bmstu.akka.labs;

import akka.NotUsed;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.ConnectHttp;
import akka.http.javadsl.Http;
import akka.http.javadsl.ServerBinding;
import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.http.javadsl.server.AllDirectives;
import akka.http.javadsl.server.Route;
import akka.pattern.Patterns;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;
import bmstu.akka.labs.Actors.TestsRouter;
import bmstu.akka.labs.Messages.TestResultRequest;
import scala.concurrent.Future;

import java.io.IOException;
import java.util.concurrent.CompletionStage;
//import java.util.concurrent.Future;

import static akka.http.javadsl.server.Directives.*;

public class JsAppTester extends AllDirectives {
    public static void main(String[] args) throws IOException {
        ActorSystem system = ActorSystem.create("Lab4");
        final Http http = Http.get(system);
        final ActorMaterializer materializer = ActorMaterializer.create(system);
        JsAppTester tester = new JsAppTester();
        final Flow<HttpRequest, HttpResponse, NotUsed> routeFlow =
                tester.createRoute(system).flow(system, materializer);
        final CompletionStage<ServerBinding> binding = http.bindAndHandle(
                routeFlow,
                ConnectHttp.toHost("localhost", 8080),
                materializer
        );
        System.out.println("Server online at http://localhost:8080/\nPress RETURN to stop...");
        System.in.read();
        binding
                .thenCompose(ServerBinding::unbind)
                .thenAccept(unbound -> system.terminate());
    }

    private Route createRoute(ActorSystem system) {
        ActorRef testsRouter = system.actorOf(Props.create(TestsRouter::new));
        return route(
                post(() ->
                        entity(Jackson.unmarshaller(PackageTests.class) , msg -> {
                            testsRouter.tell(msg, ActorRef.noSender());
                            return complete("Test has started. \n");
                        })
                ),
                get(() ->
                        parameter("packageId", packageID -> {
                            Future<Object> result = Patterns.ask(
                                    testsRouter,
                                    new TestResultRequest(packageID),
                                    5000
                            );
                            return completeOKWithFuture(result, Jackson.marshaller());
                        }))

        );
    }
}
