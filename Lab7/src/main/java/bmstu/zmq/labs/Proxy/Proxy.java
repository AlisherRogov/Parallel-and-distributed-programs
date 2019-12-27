package bmstu.zmq.labs.Proxy;

import bmstu.zmq.labs.Command;
import org.zeromq.*;

import java.util.ArrayList;


public class Proxy {

    private static String clientAddress = "tcp://localhost:5555";
    private static String cacheAddress = "tcp://localhost:5569";
    private static ArrayList<StorageInfo> activeStorages = new ArrayList<>();

    public static void main(String[] args) {
        ZContext context = new ZContext();

        ZMQ.Socket backend = context.createSocket(SocketType.ROUTER);
        ZMQ.Socket frontend = context.createSocket(SocketType.ROUTER);

        backend.bind(cacheAddress);
        frontend.bind(clientAddress);

        ZMQ.Poller items = context.createPoller(2);
        items.register(frontend, ZMQ.Poller.POLLIN);
        items.register(backend, ZMQ.Poller.POLLIN);

        while (!Thread.currentThread().isInterrupted()) {
            items.poll();
            if (items.pollin(0)) {
                ZMsg msg = ZMsg.recvMsg(frontend);
                ZFrame clientId = msg.getFirst();
                Command cmd = new Command(msg.getLast().toString());
                if (cmd.getType() == "GET") {
                    int key = cmd.getIndex();
                    boolean isKeyValid = sendGet(key, msg, backend);
                    if (!isKeyValid) {
                        sendToClient(clientId, "ERROR", frontend);
                    }
                }
                if (cmd.getType() == "PUT") {
                    int key = cmd.getIndex();
                    boolean isKeyValid = sendPut(key, msg, backend);
                    if (!isKeyValid) {
                        sendToClient(clientId, "ERROR", frontend);
                    }
                }
            } else if (items.pollin(1)) {
                ZMsg msg = ZMsg.recvMsg(backend);
                String storageId = msg.getFirst().toString();
                String cmd = msg.getLast().toString();
                Command command = new Command(cmd);

                if (command.getType() == "NOTIFY") {
                    insertStorage(storageId, command.getFirstIndex(), command.getSecondIndex());
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
            if(isInsideInterval(key, storageInfo.firstIndex, storageInfo.lastIndex)) {
                sendToCache(storageInfo.getStorageID(), frame, backend);
                return true;
            }
        }
        return false;
    }

    private static boolean sendPut(int key, ZMsg frame, ZMQ.Socket backend) {
        boolean isKeyValid = false;
        for(StorageInfo storageInfo : activeStorages) {
            if(isInsideInterval(key, storageInfo.firstIndex, storageInfo.lastIndex)) {
                sendToCache(storageInfo.getStorageID(), frame, backend);
                isKeyValid = true;
            }
        }
        return isKeyValid;
    }

    private static void sendToCache(String  storageId, ZMsg frame, ZMQ.Socket backend) {
        ZMsg msg = new ZMsg();
        msg.add(storageId);
        msg.add(frame.getFirst()); // ClientId
        msg.add(frame.getLast().toString()); // command
      //  ZMsg msg1 = createMessage(storageId, frame.getFirst())
        System.out.println("message to cache has been sent");
        msg.send(backend);
    }

    private static void sendToClient(ZFrame clientId, String result, ZMQ.Socket frontend) {
        ZMsg msg = createMessage(clientId, (String) null, result);
        msg.send(frontend);
    }

    private static ZMsg createMessage(ZFrame param1, String param2, String param3) {
        ZMsg msg = new ZMsg();
        msg.add(param1);
        msg.add(param2);
        msg.add(param3);
        return msg;
    }

    private static void insertStorage(String storageId, int firstIndex, int lastIndex) {
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