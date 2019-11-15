package bmstu.akka.labs.Messages;

public class SingleTestResult {
    private String packageID, result;

    public SingleTestResult(String packageID, String result) {
        this.packageID = packageID;
        this.result = result;
    }

    public String getPackageID() {
        return packageID;
    }

    public String getResult() {
        return result;
    }
}
