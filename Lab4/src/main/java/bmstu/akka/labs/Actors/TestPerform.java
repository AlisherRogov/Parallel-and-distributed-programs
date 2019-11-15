package bmstu.akka.labs.Actors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class TestPerform extends AbstractActor {
    private ActorRef storeActor;

    ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
    engine.(jscript);
    Invocable invocable = (Invocable) engine;
    return invocable.invokeFunction(functionName, params).toString();
}
