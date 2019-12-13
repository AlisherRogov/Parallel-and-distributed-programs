package bmstu.akka.labs;

import akka.actor.ActorRef;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

public class ServersHandler {
    private final String zkAddress;
    private final ActorRef storeActor;
    private ZooKeeper zoo;

    public ServersHandler(String zkAddress, ActorRef storeActor, String address) throws IOException {
        this.zkAddress = zkAddress;
        this.storeActor = storeActor;
        zoo  = new ZooKeeper(zkAddress, 5000, this::watchConnections);
        
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
}
