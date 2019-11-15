package bmstu.akka.labs.Actors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.japi.pf.ReceiveBuilder;
import bmstu.akka.labs.Messages.JsFunction;
import bmstu.akka.labs.Messages.SingleTestResult;

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
                .match(JsFunction.class, this::runTest)
    }

    private void runTest(JsFunction test) {
        String description;
        try {
            String actualResult = performScript(test.getFunctionName(), test.getScript(),test.getParams());
            description = actualResult.equals(test.getExpectedResult()) ? "Right answer" : "Wrong";
        } catch (ScriptException e) {
            description =  "SctiptError :" + e.getLocalizedMessage();
        } catch (NoSuchMethodException e) {
            description =  "No such method :" + e.getLocalizedMessage();
        }
        storeActor.tell(new SingleTestResult(test.getPackageID(), description), ActorRef.noSender());
    }

}
