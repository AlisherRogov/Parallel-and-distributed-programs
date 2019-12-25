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
        Scanner scanner = new Scanner(System.in);

        while(!Thread.currentThread().isInterrupted()){
            String cmd =  scanner.nextLine();
        }

        context.destroySocket(socket);
        context.destroy();
    }
}
