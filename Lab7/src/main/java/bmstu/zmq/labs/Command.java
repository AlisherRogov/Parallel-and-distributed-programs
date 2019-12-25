package bmstu.zmq.labs;

public class Command {
    enum Type {
        GET, PUT, NOTIFY
    }
    private Type type;
    private Integer[] args;

    public Command(Type type, Integer ...args) {
        this.type = type;
        this.args = args;
    }
}
