package bmstu.akka.labs.Actors;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;
import bmstu.akka.labs.Messages.GetTestResult;
import bmstu.akka.labs.Messages.ResponseTestResult;

import java.util.HashMap;
import java.util.Map;

public class StorageActor extends AbstractActor {
    private Map<String, Integer> storage = new HashMap<>();

    @Override
    public Receive createReceive() {
        return ReceiveBuilder
                .create()
                .match(GetTestResult.class, msg -> {
                    if(storage.containsKey(msg.getUrl())) {
                        sender().tell(new ResponseTestResult())
                    }
                })
                .match(StoreTestResult.class)
    }
}
