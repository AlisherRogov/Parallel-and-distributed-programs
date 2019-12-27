package bmstu.zmq.labs.Proxy;

public class StorageInfo {
    private static final int DURATION_MS = 500;
    String storageID;
    int firstIndex;
    int lastIndex;
    long lastNotifyTime;

    public StorageInfo(String storageID, int firstIndex, int lastIndex) {
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

    public String getStorageID() {
        return storageID;
    }

    public int getFirstIndex() {
        return firstIndex;
    }

    public int getLastIndex() {
        return lastIndex;
    }
}
