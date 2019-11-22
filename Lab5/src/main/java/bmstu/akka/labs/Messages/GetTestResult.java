package bmstu.akka.labs.Messages;

public class GetTestResult {
    private String Url;
    private int count;

    public  GetTestResult(String Url, int count) {
        this.Url = Url;
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public String getUrl() {
        return Url;
    }
}
