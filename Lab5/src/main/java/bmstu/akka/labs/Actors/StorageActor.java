package bmstu.akka.labs.Actors;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

public class StorageActor extends AbstractActor {

    @Override
    public Receive createReceive() {
        return ReceiveBuilder
                .create()
                .match(GetTestResult.class)
                .match(TestResult.class)
    }
}
