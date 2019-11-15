package bmstu.akka.labs.Messages;

public class TestResultRequest {
    private String packageID;

    public TestResultRequest(String packageID) {
        this.packageID = packageID;
    }

    public String getPackageID() {
        return packageID;
    }
}
