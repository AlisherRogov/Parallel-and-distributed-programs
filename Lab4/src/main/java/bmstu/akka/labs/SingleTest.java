package bmstu.akka.labs;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SingleTest {
    @JsonProperty("testName") private String testName;
    @JsonProperty("expectedResults") private String expectedResults;
    @JsonProperty("params") private List<Object> params = null;
    @JsonIgnore private Map<String, Object> unexpectedProperties = new HashMap<>();

    @JsonAnyGetter
    public Map<String, Object> getUnexpectedProperties() {
        return unexpectedProperties;
    }

    public List<Object> getParams() {
        return params;
    }

    public String getExpectedResults() {
        return expectedResults;
    }

    public String getTestName() {
        return testName;
    }

    public void setUnexpectedProperties(Map<String, Object> unexpectedProperties) {
        this.unexpectedProperties = unexpectedProperties;
    }

    @JsonAnySetter
    public void setExpectedResults(String expectedResults) {
        this.expectedResults = expectedResults;
    }

    public void setParams(List<Object> params) {
        this.params = params;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }
}
