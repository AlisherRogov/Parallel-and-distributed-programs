package bmstu.zmq.labs;

public class Command {
    enum Type {
        GET, PUT, NOTIFY
    }
    private Type type;
    private Integer[] args;

    public Command(String cmd) {
        parseCommand(cmd);
    }

    private void parseCommand(String cmd){
        
    }

}
