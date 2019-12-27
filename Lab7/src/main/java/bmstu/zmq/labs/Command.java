package bmstu.zmq.labs;

import java.util.ArrayList;

public class Command {
    enum Type {
        GET, PUT, NOTIFY, ERROR
    }

    private static final String DELIMETER = " ";
    private Type type;
    private ArrayList<Integer> args;

    public Command(String cmd) {
        args = new ArrayList<>();
        parseCommand(cmd);
    }

    private void parseCommand(String cmd) {
        String[] split = cmd.trim().split(DELIMETER);
        if (split[0] == "GET") {
            this.type = Type.GET;
            args.add(Integer.parseInt(split[1]));
        }
        if (split[0] == "PUT") {
            this.type = Type.PUT;
            args.add(Integer.parseInt(split[1]));
            args.add(Integer.parseInt(split[2]));
        }
        if (split[0] == "NOTIFY") {
            this.type = Type.NOTIFY;
            args.add(Integer.parseInt(split[1]));
            args.add(Integer.parseInt(split[2]));
        }
        if (split[0] == "ERROR") {
            this.type = Type.ERROR;
        }
    }

    public String getType() {
        return type.name();
    }

    public Integer getIndex() {
        if (this.type == Type.GET) return args.get(1);
        return null;
    }

    public 
}


