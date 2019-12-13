package bmstu.akka.labs;

import akka.NotUsed;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.ConnectHttp;
import akka.http.javadsl.Http;
import akka.http.javadsl.ServerBinding;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.http.javadsl.model.Uri;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;
import bmstu.akka.labs.Actors.StoreActor;
import org.apache.zookeeper.KeeperException;

import java.io.IOException;
import java.util.concurrent.CompletionStage;

public class PseudoAnonymizationApp {
    public static void main (String[] args) throws InterruptedException, KeeperException, IOException {
        ActorSystem system = ActorSystem.create("anonymizer");
        ActorRef storeActor = system.actorOf(Props.create(StoreActor.class));
        final ActorMaterializer materializer = ActorMaterializer.create(system);
        final ServerRoutes serverRoutes = new ServerRoutes(system, storeActor);

        String zkAddress = args[0];
        String hostAddress = args[1];
        Uri hostUri = Uri.create("http://" + hostAddress);


        final ServersHandler serversHandler = new ServersHandler(zkAddress, storeActor, hostUri.getHost().toString() + ':' + hostUri.getPort());
        final Flow<HttpRequest, HttpResponse, NotUsed> routeFlow = serverRoutes
                .getRoutes()
                .flow(system, materializer);

        final CompletionStage<ServerBinding> binding = Http.get(system).bindAndHandle(
                routeFlow,
                ConnectHttp.toHost(hostUri.getHost().toString(), hostUri.getPort()),
                materializer
        );
        System.out.println("Server online at" + hostUri.getHost().toString() + ':' + hostUri.getPort() + "Press RETURN to stop...");
        System.in.read();
        binding
                .thenCompose(ServerBinding::unbind)
                .thenAccept(unbound -> system.terminate()); // and shutdown when done

    }
}
