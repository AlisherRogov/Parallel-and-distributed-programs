package bmstu.zmq.labs;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;
import org.zeromq.ZMsg;

public class Cache {
    private static String CACHE_ADDRESS = "tcp://localhost:5569";
    public static void main(String[] args) {
        ZContext context = new ZContext();
        ZMQ.Socket socket = context.createSocket(SocketType.DEALER);
        socket.connect(CACHE_ADDRESS);

        int start = Integer.parseInt(args[0]);
        int end = Integer.parseInt(args[1]);

        while (!Thread.currentThread().isInterrupted()) {
            ZMsg msg = ZMsg.recvMsg(socket);

            if (msg != null) {
                
            }
        }


    }
}
