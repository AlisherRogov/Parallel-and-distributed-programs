package bmstu.akka.labs.Messages;

import java.util.List;

public class JsFunction {
    private String packageID;
    private String functionName;
    private String script;
    private List<Object> params;
    private String expectedResult;

    public JsFunction(String packageID, String functionName,
                      String script, List<Object> params, String expectedResult) {
        this.expectedResult = expectedResult;
        this.functionName = functionName;
        this.packageID = packageID;
        this.params = params;
        this.script = script;
    }

    
}
