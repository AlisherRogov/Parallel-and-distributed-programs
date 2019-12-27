package bmstu.zmq.labs.Cache;

import java.util.ArrayList;
import java.util.List;

public class CacheStorage {
    private List<Integer> list;
    private int firstInd;
    private int lastInd;

    public CacheStorage(int firstInd, int lastInd, int value) {
        this.firstInd = firstInd;
        this.lastInd = lastInd;
        list = new ArrayList<>()
    }
}
