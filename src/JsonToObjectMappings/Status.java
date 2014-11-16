package JsonToObjectMappings;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Status {

    @JsonProperty(value = "user")
    public User user;

    @JsonProperty(value = "text")
    public String text;

    @JsonProperty(value = "created_at")
    public String createdAt;

}