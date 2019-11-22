package bmstu.akka.labs.Messages;

public class GetTestResult {
    private String url;
    private int count;

    public  GetTestResult(String url, int count) {
        this.url = url;
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public String getUrl() {
        return url;
    }
}
