package bmstu.akka.labs.Actors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.routing.RoundRobinPool;
import bmstu.akka.labs.Messages.JsFunction;
import bmstu.akka.labs.Messages.TestResultRequest;
import bmstu.akka.labs.PackageTests;

import java.util.stream.Stream;

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
                .match(PackageTests.class)
    }

    private void receiveTestResultRequest(TestResultRequest req) {
        this.storeActor.tell(req, getSender());
    }

    private  void receivePackageTests(PackageTests tests) {
        tests.getTests().stream()
                .map(test -> new JsFunction(tests.getPackageID(), tests.getFunctionName(),
                        tests.getJsScript(), test.getParams(), test.getExpectedResults()))
                .forEach(msg -> this.testPerfomRouter.tell(msg, ));
    }


}
