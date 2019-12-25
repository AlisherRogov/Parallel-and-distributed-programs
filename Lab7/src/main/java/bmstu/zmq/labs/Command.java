package bmstu.zmq.labs;

public class Command {
    enum Type {
        GET, PUT, NOTIFY
    }
    private static final String DELIMETER = " ";
    private Type type;
    private Integer[] args;

    public Command(String cmd) {
        parseCommand(cmd);
    }

    private void parseCommand(String cmd){
        String[] split = cmd.trim().split(DELIMETER);
        if(split[ ])
    }

}
