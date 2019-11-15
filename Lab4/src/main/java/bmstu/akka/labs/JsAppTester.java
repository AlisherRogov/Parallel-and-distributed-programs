package bmstu.akka.labs;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class JsAppTester {
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("Lab4");
        ActorRef storeActor = system.actorOf(Props.create(StoreActor.class));


    }
}
