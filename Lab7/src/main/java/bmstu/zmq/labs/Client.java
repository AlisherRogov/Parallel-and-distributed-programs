package bmstu.zmq.labs;

import bmstu.zmq.labs.Command;
import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;
import org.zeromq.ZMsg;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;
import java.io.Closeable;
import java.util.logging.Logger;

public class Client implements Closeable{
    private static final Logger log = Logger.getLogger(Client.class.getName());
    ZContext context;
    ZMQ.Socket Socket;
    public static void main(String[] args) throws IOException {
        try (
                Scanner scanner = new Scanner(System.in);
                ZContext context = new ZContext();
                Client client = new Client(context)
        ) {
            client.connect("tcp://localhost:5569");
            client.handle(scanner, System.out);
        }
    }
    public Client(ZContext context) {
        this.context = context;
        this.Socket = context.createSocket(SocketType.REQ);
    }

    private void connect(String clientAddr) {
        Socket.connect(clientAddr);
    }

    private void handle(Scanner scanner, PrintStream out) {
        while (!Thread.currentThread().isInterrupted()){
            out.print("> ");
            Command cmd = new Command(scanner.nextLine());
            if(cmd.getType().equals("GET")){
                String res = SendGetMsg(cmd);
                out.println(res != null ? res : "ERROR");
            } else if(cmd.getType().equals("PUT")){
                String result = SendPutMsg(cmd);
                out.println(result);
            }
        }
    }

    private String SendPutMsg(Command putCmd) {
        ZMsg msg = ZMsg.newStringMsg(putCmd.toString());
        log.info("Sending message: " + msg);
        msg.send(Socket);

        ZMsg rec = ZMsg.recvMsg(Socket);
        return rec.popString();
    }

    private String SendGetMsg(Command getCmd) {
        ZMsg msg = ZMsg.newStringMsg(getCmd.toString());
        log.info("Sending message: " + msg);
        msg.send(Socket);

        ZMsg rec = ZMsg.recvMsg(Socket);
        log.info("Received message: " + rec);
        Command cmd = new Command(rec.popString());
        if (!cmd.getType().equals("ERROR")){
            return rec.toString();
        } else {
            return null;
        }
    }

    @Override
    public void close() throws IOException {
        context.destroySocket(Socket);
    }
}