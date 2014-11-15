import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Map;

public class Feed {

    @JsonProperty(value = "statuses")
    private Object[] statuses;

    @JsonProperty(value = "search_metadata")
    private Map<String, String> searchMetadata;

    public Map<String, String> getSearchMetadata() {
        return searchMetadata;
    }
}
