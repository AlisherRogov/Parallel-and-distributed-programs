package bmstu.akka.labs.Actors;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;
import bmstu.akka.labs.Messages.PackageTestsResults;
import bmstu.akka.labs.Messages.SingleTestResult;
import bmstu.akka.labs.Messages.TestResultRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TestsResultStorage extends AbstractActor {
    private Map<String, ArrayList<String>> store = new HashMap<>();

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(SingleTestResult.class, this::getSingleTestResult)
                .match(TestResultRequest.class, this::sendPackageTestsResult)
                .build();
    }

    private void getSingleTestResult(SingleTestResult m) {
        if (store.containsKey(m.getPackageID())) {
            store.get(m.getPackageID()).add(m.getResult());
        } else {
            ArrayList<String> res = new ArrayList<>();
            res.add(m.getResult());
        }
        store.put(m.getPackageID(), m.getResult());
    }

    private void sendPackageTestsResult(TestResultRequest request) {
        String packageID = request.getPackageID();
        sender().tell(new PackageTestsResults(packageID, store.get(packageID)), getSelf());
    }
}
