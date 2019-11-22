package bmstu.akka.labs.Actors;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;
import bmstu.akka.labs.Messages.GetTestResult;
import bmstu.akka.labs.Messages.ResponseTestResult;

import java.util.HashMap;
import java.util.Map;

public class StorageActor extends AbstractActor {
    private Map<String, Long> storage = new HashMap<>();

    @Override
    public Receive createReceive() {
        return ReceiveBuilder
                .create()
                .match(GetTestResult.class, msg -> {
                    boolean isCounted = storage.containsKey(msg.getUrl());
                    if(isCounted) {
                        sender().tell(new ResponseTestResult(isCounted, msg.getUrl(), storage.get(msg.getUrl()) ))
                    }
                })
                .match(StoreTestResult.class)
    }
}
