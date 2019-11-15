package bmstu.akka.labs.Actors;

import akka.actor.AbstractActor;

import java.util.HashMap;
import java.util.Map;

public class TestsResultStorage extends AbstractActor {
    private Map<String, String> store = new HashMap<>();

    public Receive createReceive
}
