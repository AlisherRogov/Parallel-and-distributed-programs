package bmstu.zmq.labs;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

import java.util.Scanner;

public class Client {
    private static String CLIENT_ADDRESS = "tcp://localhost:5569";
    public static void main(String[] args) {
        ZContext context = new ZContext();
        ZMQ.Socket socket = context.createSocket(SocketType.REQ);
        socket.connect(CLIENT_ADDRESS);
        Scanner in = new Scanner(System.in);

        while(true) {
            String command = in.nextLine();
            System.out.println(command);
            socket.send(command, 0);
            String reply = socket.recvStr();
            if(reply.equals("ERROR")) break;
            System.out.println(reply);
        }

        context.destroySocket(socket);
        context.destroy();
    }
}
