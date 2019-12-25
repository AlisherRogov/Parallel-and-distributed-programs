package bmstu.zmq.labs;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;


public class Proxy {

    private static String ClientAddress;
    private static String address;

    public static void main(String[] args) {
//        ZContext context = new ZContext();
//        ZMQ.Socket backend = context.createSocket(SocketType.ROUTER);
//        ZMQ.Socket frontend = context.createSocket(SocketType.ROUTER);
//
//        backend.bind(address);
//        frontend.bind(ClientAddress);
//
//        ZMQ.Poller items = context.createPoller(2);
//        items.register(frontend, ZMQ.Poller.POLLIN);
//        items.register(backend, ZMQ.Poller.POLLIN);
//
//        while (!Thread.currentThread().isInterrupted()) {
//
//        }
        ZContext context = new ZContext();
        ZMQ.Socket socket = context.createSocket(SocketType.REP);
        socket.bind("tcp://localhost:5555");
        System.out.println("bind");
        for (int i = 0; i < 10; i++) {
            socket.send("request"+i, 0);
            String reply = socket.recvStr();
            System.out.println("reply" + i+ "result="+ reply);
        }
        context.destroySocket(socket);
        context.destroy();
    }

}
