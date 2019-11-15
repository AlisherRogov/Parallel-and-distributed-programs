package bmstu.akka.labs.Actors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.routing.RoundRobinPool;
import bmstu.akka.labs.Messages.TestResultRequest;

public class TestsRouter extends AbstractActor {
    private ActorRef storeActor;
    private ActorRef testPerfomRouter;
    private int TEST_PERFORM_POOL_SIZE = 5;

    public TestsRouter() {
        this.storeActor = getContext().actorOf(Props.create(TestsResultStorage::new), "TestsResultStorage");
        this.testPerfomRouter = getContext().actorOf(new RoundRobinPool(TEST_PERFORM_POOL_SIZE)
                .props(Props.create(TestPerform.class, storeActor)), "router");
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(TestResultRequest.class,  this::receiveTestResultRequest)
                .match()
    }

    private void receiveTestResultRequest(TestResultRequest req) {
        this.storeActor.tell(req, getSender());
    }


}
