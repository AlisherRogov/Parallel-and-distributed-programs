package bmstu.zmq.labs;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;


public class Proxy {
    public static void main(String[] args) {
        ZContext context = new ZContext();
        ZMQ.Socket backend = context.createSocket(SocketType.ROUTER);
        Socket frontend
    }
}
