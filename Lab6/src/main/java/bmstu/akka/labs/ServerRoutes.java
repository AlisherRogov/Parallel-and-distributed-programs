package bmstu.akka.labs;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.http.javadsl.Http;
import akka.http.javadsl.model.*;
import akka.http.javadsl.server.Route;
import akka.japi.Pair;
import akka.pattern.Patterns;
import bmstu.akka.labs.Messages.GetMessage;
import bmstu.akka.labs.Messages.ResponseMessage;
import scala.compat.java8.FutureConverters;
;
import java.util.concurrent.CompletionStage;

import static akka.http.javadsl.server.Directives.*;

public class ServerRoutes {

    private final static String SCHEME = "http://";
    private static final String URL_ARG_NAME = "url";
    private static final String COUNT_ARG_NAME = "count";
    private static final int TIMEOUT_MS = 5000;

    private ActorSystem system;
    private ActorRef storeActor;

    public ServerRoutes(ActorSystem system, ActorRef storeActor) {
        this.system = system;
        this.storeActor = storeActor;
    }

    private Uri getUri(String address) {
        return Uri.create(SCHEME + address);
    }

    private static int stringToInt(String number) {
        return Integer.parseInt(number);
    }

    public Route getRoutes() {
        return route (
                get(() -> parameter(URL_ARG_NAME,  url -> parameter(COUNT_ARG_NAME, countString -> {
                    int count = stringToInt(countString);
                    return count == 0 ? completeWithFuture(requestUrl(url)) :  completeWithFuture(redirectRequest(url, count - 1));
                })))
        );
    }

    private CompletionStage<HttpResponse> requestUrl (String url) {
        return Http.get(system).singleRequest(HttpRequest.create(url));
    }

    private CompletionStage<HttpResponse> redirectRequest(String url, int count) {
       return FutureConverters.toJava( Patterns.ask(storeActor, new GetMessage(), TIMEOUT_MS))
               .thenApply(o -> (ResponseMessage)o)
               .thenCompose(msg -> requestUrl(getUri(msg.getAddress())
                       .query(Query.create(Pair.create(URL_ARG_NAME, url), Pair.create(COUNT_ARG_NAME, Integer.toString(count))))
                       .toString()));
    }
}
