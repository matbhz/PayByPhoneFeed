package JsonToObjectMappings;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    @JsonProperty(value = "name")
    public String name;

    @JsonProperty(value = "id")
    public String id;

}