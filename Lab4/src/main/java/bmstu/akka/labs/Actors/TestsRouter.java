package bmstu.akka.labs.Actors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;

public class TestsRouter extends AbstractActor {
    private ActorRef storeActor;
    private ActorRef testPerfomRouter;

    public TestsRouter() {
        this.storeActor = getContext().actor;
        this.testPerfomRouter = testPerfomRouter;
    }


}
