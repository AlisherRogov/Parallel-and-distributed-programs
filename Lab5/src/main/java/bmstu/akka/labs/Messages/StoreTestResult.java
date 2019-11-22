package bmstu.akka.labs.Messages;

public class StoreTestResult {
    private String url;
    private long time;

    public StoreTestResult(String url, long time) {
        this.url = url;
        this.time = time;
    }

    public String getUrl() {
        return url;
    }

    public long getTime() {
        return time;
    }
}
