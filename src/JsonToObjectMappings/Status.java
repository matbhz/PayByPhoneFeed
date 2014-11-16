package JsonToObjectMappings;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Status {

    @JsonProperty(value = "user")
    public User user;

    @JsonProperty(value = "text")
    public String text;

    @JsonProperty(value = "created_at")
    public String createdAt;

}