package bmstu.akka.labs;


import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PackageTests {
    @JsonProperty("packageId") private String packageID;
    @JsonProperty("jsScript") private String jsScript;
    @JsonProperty("functionName") private String functionName;
    @JsonProperty("tests") private List<SingleTest> tests = null;
    @JsonIgnore private Map<String, Object> unexpectedProperties = new HashMap<>();

    public String getFunctionName() {
        return functionName;
    }

    public String getPackageID() {
        return packageID;
    }

    public String getJsScript() {
        return jsScript;
    }

    public List<SingleTest> getTests() {
        return tests;
    }

    @JsonAnyGetter
    public Map<String, Object> getUnexpectedProperties() {
        return unexpectedProperties;
    }

    
}
