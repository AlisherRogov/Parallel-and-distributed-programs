package bmstu.akka.labs;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "testName",
        "expectedResult",
        "params"
})

public class SingleTest {
    @JsonProperty("testName") private String testName;
    @JsonProperty("expectedResults") private String expectedResults;
    @JsonProperty("params") private List<Object> params = null;
    @JsonIgnore private Map<String, Object> unexpectedProperties = new HashMap<>();

    @JsonAnyGetter
    public Map<String, Object> getUnexpectedProperties() {
        return unexpectedProperties;
    }

    @JsonProperty("params")
    public List<Object> getParams() {
        return params;
    }

    @JsonProperty("expectedResults")
    public String getExpectedResults() {
        return expectedResults;
    }

    @JsonProperty("testName")
    public String getTestName() {
        return testName;
    }

    @JsonAnySetter
    public void setUnexpectedProperties(String key, Object value) {
        unexpectedProperties.put(key, value);
    }

    @JsonProperty("expectedResults")
    public void setExpectedResults(String expectedResults) {
        this.expectedResults = expectedResults;
    }

    @JsonProperty("params")
    public void setParams(List<Object> params) {
        this.params = params;
    }

    @JsonProperty("testName")
    public void setTestName(String testName) {
        this.testName = testName;
    }
}
