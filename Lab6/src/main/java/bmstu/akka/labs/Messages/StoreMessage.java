package bmstu.akka.labs.Messages;

public class StoreMessage {
    private final String[] addresses;

    public StoreMessage(String[] addresses) {
        this.addresses = addresses;
    }
}
