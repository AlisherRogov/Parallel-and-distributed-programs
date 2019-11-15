package bmstu.akka.labs.Actors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;

public class TestsRouter extends AbstractActor {
    private ActorRef storeActor;
    private ActorRef testPerfomRouter;

    public TestsRouter() {
        this.storeActor = getContext().actorOf(Props.create());
        this.testPerfomRouter = testPerfomRouter;
    }


}
