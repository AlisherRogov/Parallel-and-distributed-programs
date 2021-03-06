package bmstu.zmq.labs;

import org.zeromq.*;

import java.util.ArrayList;


public class Proxy {

    private static String CLIENT_ADDRESS = "tcp://localhost:5569";
    private static String CACHE_ADDRESS = "tcp://localhost:5555";
    private static ArrayList<StorageInfo> activeStorages = new ArrayList<>();

    public static void main(String[] args) {
        ZContext context = new ZContext();

        ZMQ.Socket backend = context.createSocket(SocketType.ROUTER);
        ZMQ.Socket frontend = context.createSocket(SocketType.ROUTER);

        backend.bind(CACHE_ADDRESS);
        frontend.bind(CLIENT_ADDRESS);

        ZMQ.Poller items = context.createPoller(2);
        items.register(frontend, ZMQ.Poller.POLLIN);
        items.register(backend, ZMQ.Poller.POLLIN);

        while (!Thread.currentThread().isInterrupted()) {
            items.poll();
            if (items.pollin(0)) {
                System.out.println(0);
                ZMsg msg = ZMsg.recvMsg(frontend);
                System.out.println(msg.toString());
                ZFrame clientId = msg.getFirst();
                Command cmd = new Command(msg.getLast().toString());
                if (cmd.getType().equals("GET")) {
                    int key = cmd.getIndex();
                    boolean isKeyValid = sendGet(key, msg, backend);
                    if (!isKeyValid) {
                        System.out.println("not in range or runtime");
                        sendToClient(clientId, "ERROR", frontend);
                    }
                }
                if (cmd.getType().equals("PUT")) {
                    int key = cmd.getIndex();
                    boolean isKeyValid = sendPut(key, msg, backend);
                    if (!isKeyValid) {
                        System.out.println("not in range or runtime");
                        sendToClient(clientId, "ERROR", frontend);
                    } else {
                        System.out.println(clientId);
                        System.out.println("nice");
                        sendToClient(clientId, "NICE", frontend);
                    }
                }
            } else if (items.pollin(1)) {
                ZMsg msg = ZMsg.recvMsg(backend);
                System.out.println(msg.toString());
            //    String storageId = msg.getFirst().toString();
              //  System.out.println(storageId);
                String cmd = msg.getLast().toString();
                Command command = new Command(cmd);
                if (command.getType().equals("NOTIFY")) {
                    insertStorage(msg.getFirst(), command.getFirstIndex(), command.getSecondIndex());
                }
                if(command.getType().equals("RESULT")) {
                    System.out.println(msg);
                    frontend.send(msg.toString(), 0);
                    msg.pop();
                    sendToClient(msg.getFirst(), msg.getLast().toString(), frontend);
                   // msg.send(frontend);
                }
            }
            removeDeadStorages();
        }
        context.destroySocket(frontend);
        context.destroySocket(backend);
        context.destroy();
    }

    private static boolean isInsideInterval(int key, int start, int end) {
        return (start <= key) && (key <= end);
    }

    private static boolean sendGet(int key, ZMsg frame, ZMQ.Socket backend) {
        for(StorageInfo storageInfo : activeStorages) {
            if(isInsideInterval(key, storageInfo.firstIndex, storageInfo.lastIndex) && storageInfo.idDead()) {
                sendToCache(storageInfo.getStorageID(), frame, backend);
                return true;
            }
        }
        return false;
    }

    private static boolean sendPut(int key, ZMsg frame, ZMQ.Socket backend) {
        boolean isKeyValid = false;
        for(StorageInfo storageInfo : activeStorages) {
            if(isInsideInterval(key, storageInfo.firstIndex, storageInfo.lastIndex) && storageInfo.idDead()) {
                sendToCache(storageInfo.getStorageID(), frame, backend);
                isKeyValid = true;
            }
        }
        return isKeyValid;
    }

    private static void sendToCache(ZFrame  storageId, ZMsg frame, ZMQ.Socket backend) {
        System.out.println(frame.toString());
        ZMsg msg = new ZMsg();
        msg.add(storageId);
        msg.add("");
        msg.add(frame.getFirst());
        msg.add("");
        msg.add(frame.getLast().toString()); // command
        System.out.println("message to cache has been sent");
        System.out.println(msg.toString());
        msg.send(backend, false);
    }

    private static void sendToClient(ZFrame clientId, String result, ZMQ.Socket frontend) {
        ZMsg msg = new ZMsg();
        msg.add(clientId);
        msg.add("");
        msg.add(result);
        msg.send(frontend, false);
    }


    private static void insertStorage(ZFrame storageId, int firstIndex, int lastIndex) {
        StorageInfo storageInfo = new StorageInfo(storageId, firstIndex, lastIndex);
        for (StorageInfo storedStorages : activeStorages) {
            if (storedStorages.getStorageID().equals(storageId)) {
                storedStorages.updateNotifyTime();
                return;
            }
        }
        activeStorages.add(storageInfo);
    }

    private static void removeDeadStorages() {
        activeStorages.removeIf(StorageInfo::idDead);
    }


}
