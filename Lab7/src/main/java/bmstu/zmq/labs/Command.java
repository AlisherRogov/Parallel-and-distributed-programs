package bmstu.zmq.labs;

import java.util.ArrayList;

public class Command {
    enum Type {
        GET, PUT, NOTIFY
    }
    private static final String DELIMETER = " ";
    private Type type;
    private ArrayList<Integer> args;

    public Command(String cmd) {
        args = new ArrayList<>();
        parseCommand(cmd);
    }

    private void parseCommand(String cmd){
        String[] split = cmd.trim().split(DELIMETER);
        if(split[0] == "GET") {
            this.type = Type.GET;
            args.add(Integer.parseInt(split[1]));
        }
        if(split[1] == "PUT") {
            this.type = Type.PUT;
            args.add(Integer.parseInt(split[1]));
            args.add(Integer.parseInt(split[2]))
        }
        if(split[2] == )
    }

}
