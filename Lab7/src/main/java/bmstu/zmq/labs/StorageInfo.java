package bmstu.zmq.labs;

import org.zeromq.ZFrame;
import org.zeromq.ZMQ;

public class StorageInfo {
    private static final int DURATION_MS = 50;
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

    public void updateNotifyTime() {
        lastNotifyTime = System.currentTimeMillis();
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
