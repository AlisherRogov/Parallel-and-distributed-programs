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
        Scanner in = new Scanner(System.in);

        while(!Thread.currentThread().isInterrupted()) {
            Command cmd = new Command(in.nextLine());
            if (cmd.getType() == "")
        }

        context.destroySocket(socket);
        context.destroy();
    }
}
