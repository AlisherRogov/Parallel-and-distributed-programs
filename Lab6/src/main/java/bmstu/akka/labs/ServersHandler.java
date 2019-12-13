package bmstu.akka.labs;

import akka.actor.ActorRef;
import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ServersHandler {
    private final String ROOT_PATH = "/servers";
    private final String NODE_PATH = ROOT_PATH + "/s";

    private final String zkAddress;
    private final ActorRef storeActor;
    private ZooKeeper zoo;

    public ServersHandler(String zkAddress, ActorRef storeActor, String address) throws IOException, InterruptedException, KeeperException {
        this.zkAddress = zkAddress;
        this.storeActor = storeActor;
        zoo  = new ZooKeeper(zkAddress, 5000, this::watchConnections);
        zoo.create(NODE_PATH, address.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        watchNodes();
    }

    private void watchConnections(WatchedEvent event) {
        if(event.getState() == Watcher.Event.KeeperState.Expired ||
                event.getState() == Watcher.Event.KeeperState.Disconnected) {
            try {
                zoo  = new ZooKeeper(zkAddress, 5000, this::watchConnections);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void watchNodes() {
        String[] addresses = Objects.requireNonNull(getChildren())
    }

    private List<String> getChildren() {
        try {
            return  zoo.getChildren(ROOT_PATH, this::watchConnections);
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
