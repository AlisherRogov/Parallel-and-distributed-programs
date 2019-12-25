package bmstu.zmq.labs;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        ZContext context = new ZContext();
        ZMQ.Socket socket = context.createSocket(SocketType.REQ);
        socket.connect("tcp://localhost:5555");
        Scanner scanner = new Scanner();

        while(!Thread.currentThread().isInterrupted()){
            String
        }

        context.destroySocket(socket);
        context.destroy();
    }
}
