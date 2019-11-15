package bmstu.akka.labs;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SingleTest {
    @JsonProperty("testName") private String testName;
    @JsonProperty("expectedResults") private String expectedResults;
    @JsonProperty("params") private List<Object>
}
