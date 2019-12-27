package bmstu.zmq.labs;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;
import org.zeromq.ZMsg;

import java.util.HashMap;
import java.util.Map;

public class Cache {
    private static String CACHE_ADDRESS = "tcp://localhost:5569";
    public static void main(String[] args) {
        Map<Integer, Integer> storage = new HashMap<>();

        ZContext context = new ZContext();
        ZMQ.Socket socket = context.createSocket(SocketType.DEALER);
        socket.connect(CACHE_ADDRESS);

        int start = Integer.parseInt(args[0]);
        int end = Integer.parseInt(args[1]);

        while (!Thread.currentThread().isInterrupted()) {
            ZMsg msg = ZMsg.recvMsg(socket);

            if (msg != null) {
                String cmd = msg.getLast().toString();
                Command command = new Command(cmd);

                if (command.getType() == "GET") {
                    int key = command.getIndex();
                    int value = storage.get(key);

                    String response = value == null ? "null" 
                }
            }
        }


    }
}
