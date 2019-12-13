package bmstu.akka.labs.Actors;

import akka.actor.AbstractActor;

import java.util.Random;

public class StoreActor extends AbstractActor {
    private String[] addresses;
    private Random random;

    public StoreActor() {
        this.random = new Random();
    }
}
