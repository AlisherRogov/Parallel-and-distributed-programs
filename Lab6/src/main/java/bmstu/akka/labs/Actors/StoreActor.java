package bmstu.akka.labs.Actors;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;
import bmstu.akka.labs.Messages.GetMessage;
import bmstu.akka.labs.Messages.ResponseMessage;
import bmstu.akka.labs.Messages.StoreMessage;

import java.util.Random;

public class StoreActor extends AbstractActor {
    private String[] addresses;
    private Random random;

    public StoreActor() {
        this.random = new Random();
    }

    private String getRandomAddress() {
        return addresses[]
    }

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(StoreMessage.class, msg -> addresses = msg.getAddresses())
                .match(GetMessage.class, msg -> sender().tell(new ResponseMessage())
    }
}
