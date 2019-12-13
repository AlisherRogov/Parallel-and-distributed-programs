package bmstu.akka.labs.Messages;

public class ResponseMessage {
    private  final String address;

    public ResponseMessage(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }
}
