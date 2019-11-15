package bmstu.akka.labs;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SingleTest {
    @JsonProperty("testName") private String testName;
    @JsonProperty("expectedResults") private String expectedResults;
    @JsonProperty
}
