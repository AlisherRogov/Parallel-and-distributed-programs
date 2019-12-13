package bmstu.akka.labs;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.http.javadsl.server.Route;
;import java.net.http.HttpResponse;
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
                    return count == 0 ? completeWithFuture()
                })))
        )
    }

    private static CompletionStage<HttpResponse> 
}
