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
    }

    public void updateNotifyTime() {
        lastNotifyTime = 
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
