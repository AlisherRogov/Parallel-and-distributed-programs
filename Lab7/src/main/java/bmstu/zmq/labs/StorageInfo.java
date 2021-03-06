package bmstu.zmq.labs;

import org.zeromq.ZFrame;
import org.zeromq.ZMQ;

public class StorageInfo {
    private static final int DURATION_MS = 500;
    ZFrame storageID;
    int firstIndex;
    int lastIndex;
    long lastNotifyTime;

    public StorageInfo(ZFrame storageID, int firstIndex, int lastIndex) {
        this.storageID = storageID;
        this.firstIndex = firstIndex;
        this.lastIndex = lastIndex;
        updateNotifyTime();
    }

    public long updateNotifyTime() {
        lastNotifyTime = System.currentTimeMillis();
        return  lastNotifyTime;
    }

    public boolean idDead() {
        long currentTime = System.currentTimeMillis();
        return (currentTime - lastNotifyTime) > 2 * DURATION_MS;
    }

    public ZFrame getStorageID() {
        return storageID;
    }

    public int getFirstIndex() {
        return firstIndex;
    }

    public int getLastIndex() {
        return lastIndex;
    }
}
