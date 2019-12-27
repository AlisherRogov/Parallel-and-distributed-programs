package bmstu.zmq.labs;

import java.util.ArrayList;

public class Command {
    enum Type {
        GET, PUT, NOTIFY, ERROR, RESULT
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
        if (split[0] == "RESULT") {
            this.type = Type.RESULT;
            args.add(Integer.parseInt(split[1]));
        }
    }

    public String getType() {
        return type.name();
    }

    public Integer getIndex() {
        if (this.type == Type.GET || this.type == Type.PUT) return args.get(0);
        return null;
    }

    public Integer getValue() {
        if (this.type == Type.PUT) return args.get(1);
        return null;
    }

    public Integer getFirstIndex() {
        if (this.type == Type.NOTIFY) return args.get(0);
        return null;
    }

    public Integer getSecondIndex() {
        if (this.type == Type.NOTIFY) return args.get(1);
        return null;
    }

    public static String response(int result) {
        return "RESULT: " + result;
    }
}


