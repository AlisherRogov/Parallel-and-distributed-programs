package bmstu.akka.labs.Actors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.japi.pf.ReceiveBuilder;
import bmstu.akka.labs.Messages.JsFunction;

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
        return ReceiveBuilder.create()
                .match(JsFunction.class)
    }

    private String runTest(JsFunction test) {
        String desctiption;
        try {
            String actualResult = performScript(test.getFunctionName(), test.getScript(),test.getParams());
            description = actualResult
        } catch (ScriptException e) {
            return "SctiptError :" + e.getLocalizedMessage();
        } catch (NoSuchMethodException e) {
            return "No such method :" + e.getLocalizedMessage();
        }

    }

}
