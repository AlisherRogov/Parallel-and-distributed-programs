package bmstu.akka.labs;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import bmstu.akka.labs.Actors.StoreActor;

public class PseudoAnonymizationApp {
    public static void main (String[] args) {
        ActorSystem system = ActorSystem.create("anonymizer");
        ActorRef storeActor = system.actorOf(Props.create(StoreActor.class));
    }
}
