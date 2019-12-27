package bmstu.zmq.labs;

public class StorageInfo {
    String storageID;
    int firstIndex;
    int lastIndex;
    long lastNotifyTime:

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
        return (currentTime - lastNotifyTime) > 2 * 
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
