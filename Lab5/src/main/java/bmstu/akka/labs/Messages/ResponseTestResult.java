package bmstu.akka.labs.Messages;

public class ResponseTestResult {
    private boolean hasInfo;
    private String url;
    private Long time;

    public ResponseTestResult(boolean hasInfo, String url, Long time) {
        this.hasInfo = hasInfo;
        this.url = url;
        this.time = time;
    }

    public boolean isCounted() {
        return hasInfo;
    }

    public String getUrl() {
        return url;
    }

    public Long getTime() {
        return time;
    }
}
