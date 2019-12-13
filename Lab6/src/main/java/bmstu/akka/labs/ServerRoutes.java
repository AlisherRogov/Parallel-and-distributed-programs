package bmstu.akka.labs;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.http.javadsl.Http;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.http.javadsl.model.Query;
import akka.http.javadsl.model.Uri;
import akka.http.javadsl.server.Route;
import akka.pattern.Patterns;
import akka.pattern.Patterns$;
import bmstu.akka.labs.Messages.GetMessage;
import bmstu.akka.labs.Messages.ResponseMessage;
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

    private Uri getUri(String address) {
        return Uri.create("http://" + address);
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
        );
    }

    private CompletionStage<HttpResponse> requestUrl (String url) {
        return Http.get(system).singleRequest(HttpRequest.create(url));
    }

    private CompletionStage<HttpResponse> redirectRequest(String url, int count) {
       FutureConverters.toJava( Patterns.ask(storeActor, new GetMessage(), 5000))
               .thenApply(o -> (ResponseMessage)o)
               .thenCompose(msg -> requestUrl(getUri(msg.getAddress()).query(Query.create())
    }
}
