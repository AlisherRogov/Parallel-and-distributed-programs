package bmstu.akka.labs.Messages;

import java.util.ArrayList;

public class PackageTestsResults {
    private String packageID;
    private ArrayList<String> results;

    public PackageTestsResults(String packageID, ArrayList<String> results) {
        this.packageID = packageID;
        this.results = results;
    }

    public String getPackageID() {
        return packageID;
    }

    public ArrayList<String> getResults() {
        return results;
    }
}
