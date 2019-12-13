package bmstu.akka.labs;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.http.javadsl.Http;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.http.javadsl.server.Route;
import akka.pattern.Patterns;
import akka.pattern.Patterns$;
import bmstu.akka.labs.Messages.GetMessage;
import scala.compat.java8.FutureConverters;
;
import java.util.concurrent.CompletionStage;

import static akka.http.javadsl.server.Directives.*;

public class ServerRoutes {
    private ActorSystem system;
    private ActorRef storeActor;

    public ServerRoutes(ActorSystem system, ActorRef storeActor) {
        this.system = system;
        this.storeActor = storeActor;
    }

    private static int stringToInt(String number) {
        return Integer.parseInt(number);
    }

    public Route getRoutes() {
        return route (
                get(() -> parameter("url",  url -> parameter("count", countString -> {
                    int count = stringToInt(countString);
                    return count == 0 ? completeWithFuture(requestUrl(url)) :  completeWithFuture()
                })))
        )
    }

    private CompletionStage<HttpResponse> requestUrl (String url) {
        return Http.get(system).singleRequest(HttpRequest.create(url));
    }

    private CompletionStage<HttpResponse> redirectRequest(String url, int count) {
       FutureConverters.toJava( Patterns.ask(storeActor, new GetMessage(), 5000))
               .thenApply()
               .
    }
}
