import org.springframework.security.oauth.consumer.client.OAuthRestTemplate;

import java.util.ArrayList;

public class PayByPhoneTweetFeed {

    private static final String SEARCH_ENDPOINT = "https://api.twitter.com/1.1/search/tweets.json";

    public ArrayList<Object> getFeed() {
        OAuthRestTemplate restTemplate = new OAuthRestTemplate(new TwitterOAuth());
        Feed feed = restTemplate.getForObject(SEARCH_ENDPOINT + "?q=%40PayByPhone", Feed.class);

        return new ArrayList<Object>();
    }

}
