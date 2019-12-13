package bmstu.akka.labs.Actors;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;
import bmstu.akka.labs.Messages.StoreMessage;

import java.util.Random;

public class StoreActor extends AbstractActor {
    private String[] addresses;
    private Random random;

    public StoreActor() {
        this.random = new Random();
    }

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(StoreMessage.class, msg -> addresses = msg.getAddresses())
                .match(Ge)
    }
}
