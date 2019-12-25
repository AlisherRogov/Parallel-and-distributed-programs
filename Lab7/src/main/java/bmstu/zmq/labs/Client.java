package bmstu.zmq.labs;

import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class Client {
    public static void main(String[] args) {
        ZContext context = new ZContext();
        ZMQ.Socket socket = new ZMQ.Socket();
    }
}
