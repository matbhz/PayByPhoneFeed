package JsonToObjectMappings;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    @JsonProperty(value = "screen_name")
    public String name;

    @JsonProperty(value = "id")
    public String id;

}
