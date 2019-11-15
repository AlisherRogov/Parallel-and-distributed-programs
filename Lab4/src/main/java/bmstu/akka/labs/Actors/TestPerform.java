package bmstu.akka.labs.Actors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class TestPerform extends AbstractActor {
    private ActorRef storeActor;

    private static String performScript(String functionName, String script, Object... args) {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        engine.eval('js')
        Invocable invocable = (Invocable) engine;
        return invocable.invokeFunction(functionName, args).toString();
    }
}
