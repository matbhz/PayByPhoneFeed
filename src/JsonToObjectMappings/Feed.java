package JsonToObjectMappings;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;
import java.util.Map;

public class Feed {

    @JsonProperty(value = "statuses")
    public List<Status> statuses;

    @JsonProperty(value = "search_metadata")
    public Map<String, String> searchMetadata;

}

