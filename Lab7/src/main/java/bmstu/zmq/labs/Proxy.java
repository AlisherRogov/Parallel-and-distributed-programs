package bmstu.zmq.labs;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;
import org.zeromq.ZMsg;


public class Proxy {

    private static String ClientAddress = "tcp://localhost:5555";
    private static String address = "tcp://localhost:5569";

    public static void main(String[] args) {
        ZContext context = new ZContext();

        ZMQ.Socket backend = context.createSocket(SocketType.ROUTER);
        ZMQ.Socket frontend = context.createSocket(SocketType.ROUTER);

        backend.bind(address);
        frontend.bind(ClientAddress);

        ZMQ.Poller items = context.createPoller(2);
        items.register(frontend, ZMQ.Poller.POLLIN);
        items.register(backend, ZMQ.Poller.POLLIN);

        while (!Thread.currentThread().isInterrupted()) {
            items.poll();
            if (items.pollin(0)) {
                ZMsg msg = ZMsg.recvMsg(frontend);
                System.out.println(msg.getFirst().toString());
                System.out.println(msg.getLast().toString());
                System.out.println(msg.toString());
            }

        }




        context.destroy();
    }

}
