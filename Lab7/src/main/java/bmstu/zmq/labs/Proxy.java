package bmstu.zmq.labs;

import org.zeromq.*;

import java.util.ArrayList;


public class Proxy {

    private static String ClientAddress = "tcp://localhost:5555";
    private static String address = "tcp://localhost:5569";
    private ArrayList<StorageInfo> activeStorages = new ArrayList<>();

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

                Command cmd = new Command(msg.getLast().toString());

                if (cmd.getType() == "GET") {
                    
                }
            }

        }




        context.destroy();
    }

    private static void sendToCache(ZFrame clientId, Command cmd) {
        ZMsg msg = new ZMsg();
        m
    }

}
