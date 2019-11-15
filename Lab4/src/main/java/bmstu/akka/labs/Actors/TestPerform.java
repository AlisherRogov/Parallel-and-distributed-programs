package bmstu.akka.labs.Actors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class TestPerform extends AbstractActor {
    private ActorRef storeActor;

    private static String performScript(String functionName, String script,
                                        Object... args) throws ScriptException,  {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        engine.eval("jscript");
        Invocable invocable = (Invocable) engine;
        return invocable.invokeFunction(functionName, args).toString();
    }
}
