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

    public String getPackageID() {
        return packageID;
    }

    public List<Object> getParams() {
        return params;
    }

    public String getExpectedResult() {
        return expectedResult;
    }

    public String getFunctionName() {
        return functionName;
    }

    public String getScript() {
        return script;
    }
}
