package bmstu.akka.labs.Actors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.routing.RoundRobinPool;

public class TestsRouter extends AbstractActor {
    private ActorRef storeActor;
    private ActorRef testPerfomRouter;
    private int TEST_PERFORM_POOL_SIZE = 5;

    public TestsRouter() {
        this.storeActor = getContext().actorOf(Props.create(TestsResultStorage::new), "TestsResultStorage");
        this.testPerfomRouter = getContext().actorOf(new RoundRobinPool(TEST_PERFORM_POOL_SIZE)
                .props(Props.create(new TestPerform(storeActor))));
    }


}
