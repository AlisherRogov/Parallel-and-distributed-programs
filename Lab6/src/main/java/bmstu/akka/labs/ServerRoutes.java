package bmstu.akka.labs;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.http.javadsl.server.Route;
;import static akka.http.javadsl.server.Directives.*;

public class ServerRoutes {
    private ActorSystem system;
    private ActorRef storeActor;

    public ServerRoutes(ActorSystem system, ActorRef storeActor) {
        this.system = system;
        this.storeActor = storeActor;
    }

    private int stringToInt(String number) {
        return Integer.parseInt()
    }

    public Route getRoutes() {
        return route (
                get(() -> parameter("url",  url -> parameter("count", countString -> {
                    int count =
                })))
        )
    }
}
