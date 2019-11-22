package bmstu.akka.labs;


import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "packageId",
        "jsScript",
        "functionName",
        "tests"
})

public class PackageTests {
    @JsonProperty("packageId") private String packageID;
    @JsonProperty("jsScript") private String jsScript;
    @JsonProperty("functionName") private String functionName;
    @JsonProperty("tests") private List<SingleTest> tests = null;
    @JsonIgnore private Map<String, Object> unexpectedProperties = new HashMap<>();

    @JsonProperty("functionName")
    public String getFunctionName() {
        return functionName;
    }

    @JsonProperty("packageId")
    public String getPackageID() {
        return packageID;
    }

    @JsonProperty("jsScript")
    public String getJsScript() {
        return jsScript;
    }

    @JsonProperty("tests")
    public List<SingleTest> getTests() {
        return tests;
    }

    @JsonAnyGetter
    public Map<String, Object> getUnexpectedProperties() {
        return unexpectedProperties;
    }

    @JsonProperty("functionName")
    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    @JsonProperty("jsScript")
    public void setJsScript(String jsScript) {
        this.jsScript = jsScript;
    }

    @JsonProperty("packageId")
    public void setPackageID(String packageID) {
        this.packageID = packageID;
    }

    @JsonProperty("tests")
    public void setTests(List<SingleTest> tests) {
        this.tests = tests;
    }

    @JsonAnySetter
    public void setUnexpectedProperties(String key, Object value) {
        unexpectedProperties.put(key, value);
    }
}
