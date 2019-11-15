package bmstu.akka.labs.Actors;

import akka.actor.ActorRef;

public class TestsRouter {
    private ActorRef storeActor;
    private ActorRef testPerfomRouter;

    public TestsRouter() {
        this.storeActor = getContext().actor;
        this.testPerfomRouter = testPerfomRouter;
    }


}
