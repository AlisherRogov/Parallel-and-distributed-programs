package bmstu.akka.labs.Actors;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

import java.util.HashMap;

public class StorageActor extends AbstractActor {
    private Map<String, Integer> storage = new HashMap<>()

    @Override
    public Receive createReceive() {
        return ReceiveBuilder
                .create()
                .match(GetTestResult.class, msg ->  )
                .match(StoreTestResult.class)
    }
}
