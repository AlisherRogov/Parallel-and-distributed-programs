package bmstu.akka.labs.Actors;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

import java.util.HashMap;
import java.util.Map;

public class TestsResultStorage extends AbstractActor {
    private Map<String, String> store = new HashMap<>();

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
    }
}
