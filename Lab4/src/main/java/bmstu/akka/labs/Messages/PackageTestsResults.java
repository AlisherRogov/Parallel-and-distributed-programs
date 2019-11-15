package bmstu.akka.labs.Messages;

import java.util.ArrayList;

public class PackageTestsResults {
    private int packageID;
    private ArrayList<String> results;

    public PackageTestsResults(int packageID, ArrayList<String> results) {
        this.packageID = packageID;
        this.results = results;
    }
    

}
