package bmstu.akka.labs.Actors;

import akka.actor.ActorRef;

public class TestsRouter {
    private ActorRef storeActor;
    private ActorRef testPerfomRouter;

    public TestsRouter(ActorRef storeActor, ActorRef testPerfomRouter) {
        this.storeActor = storeActor;
        this.testPerfomRouter = testPerfomRouter;
    }

    
}
