package bmstu.akka.labs.Actors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class TestPerform extends AbstractActor {
    private ActorRef storeActor;

    public TestPerform(ActorRef storeActor) {
        this.storeActor = storeActor;
    }

    private static String performScript(String functionName, String script,
                                        Object... args) throws ScriptException, NoSuchMethodException {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        engine.eval(script);
        Invocable invocable = (Invocable) engine;
        return invocable.invokeFunction(functionName, args).toString();
    }

    @Override
    public Receive createReceive() {
        return  ReceiveBu
    }
}
