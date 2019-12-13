package bmstu.akka.labs;

import akka.NotUsed;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;
import bmstu.akka.labs.Actors.StoreActor;

public class PseudoAnonymizationApp {
    public static void main (String[] args) {
        ActorSystem system = ActorSystem.create("anonymizer");
        ActorRef storeActor = system.actorOf(Props.create(StoreActor.class));
        final ActorMaterializer materializer = ActorMaterializer.create(system);
        final ServerRoutes serverRoutes = new ServerRoutes(system, storeActor);
        final Flow<HttpRequest, HttpResponse, NotUsed> routeFlow = ServerRoutes
    }
}
