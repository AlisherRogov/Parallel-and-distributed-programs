package bmstu.akka.labs;


import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PackageTests {
    @JsonProperty("packageId") private String packageID;
    @JsonProperty("jsScript") private String jsScript;
    @JsonProperty("functionName") private String functionName;
    @JsonProperty("tests") private List<SingleTest> tests = null;
    @JsonIgnore 
}
