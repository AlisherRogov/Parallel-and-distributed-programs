package bmstu.akka.labs.Messages;

public class ResponseTestResult {
    private boolean hasInfo;
    private String Url;
    private Long time;

    public ResponseTestResult(boolean hasInfo, String Url, Long time) {
        this.hasInfo = hasInfo;
        this.Url = Url;
        this.time = time;
    }

    public boolean 

    public String getUrl() {
        return Url;
    }

    public Long getTime() {
        return time;
    }
}
