package bmstu.akka.labs.Actors;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;
import bmstu.akka.labs.Messages.PackageTestsResults;
import bmstu.akka.labs.Messages.SingleTestResult;
import bmstu.akka.labs.Messages.TestResultRequest;

import java.util.HashMap;
import java.util.Map;

public class TestsResultStorage extends AbstractActor {
    private Map<String, String> store = new HashMap<>();

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(SingleTestResult.class, this::getSingleTestResult)
                .match(TestResultRequest.class, req-> sender().tell(
                        new StoreMessage(req.getKey(), store.get(req.getKey()),self())
                )).build();
    }

    private void getSingleTestResult(SingleTestResult m) {
        store.put(m.getPackageID(), m.getResult());
    }

    private void sendPackageTestsResult(TestResultRequest request) {
        String packageID = request.getPackageID();
        sender().tell(new PackageTestsResults(packageID, store.get(packageID)), );
    }
}
