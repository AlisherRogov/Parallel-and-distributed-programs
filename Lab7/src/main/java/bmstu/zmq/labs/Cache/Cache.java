package bmstu.zmq.labs.Cache;

import bmstu.zmq.labs.Command;
import org.zeromq.*;

import java.util.HashMap;
import java.util.Map;

public class Cache {
    private static final int TIMEOUT_MS = 3000;
    private static final String CACHE_ADDRESS = "tcp://localhost:5555";

    public static void main(String[] args) {
        Map<Integer, Integer> storage = new HashMap<>();

        ZContext context = new ZContext();
        ZMQ.Socket socket = context.createSocket(SocketType.DEALER);
        socket.connect(CACHE_ADDRESS);

        int start = Integer.parseInt(args[0]);
        int end = Integer.parseInt(args[1]);
        long timeoutTime = System.currentTimeMillis() + TIMEOUT_MS;
        sendNotify(socket, start, end);

        while (!Thread.currentThread().isInterrupted()) {
            ZMsg msg = ZMsg.recvMsg(socket, false);
            if (msg != null) {
                System.out.println(msg.getLast());
                String cmd = msg.getLast().toString();
                System.out.println(cmd);
                Command command = new Command(cmd);
                String clientId = msg.getFirst().toString();
                if (command.getType().equals("GET")) {
                    Integer key = command.getIndex();
                    Integer value = storage.get(key);
                    ZMsg reply = new ZMsg();
                    reply.add(clientId);
                    reply.add((String) null);
                    reply.add(Command.response(value));
                    reply.send(socket);
                }
                if (command.getType().equals("PUT")) {
                    int index = command.getIndex();
                    int value = command.getValue();
                    storage.put(index, value);
                    msg.destroy();
                }
                if (System.currentTimeMillis() >= timeoutTime) {
                    timeoutTime = System.currentTimeMillis() + TIMEOUT_MS;
                    sendNotify(socket, start, end);
                }
            }
        }
        context.destroySocket(socket);
        context.destroy();
    }

    private static void sendNotify(ZMQ.Socket socket, int firstIndex, int lastIndex) {
        socket.send(Command.notify(firstIndex, lastIndex));
    }
}
