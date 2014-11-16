import JsonToObjectMappings.Feed;
import org.springframework.security.oauth.consumer.client.OAuthRestTemplate;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PayByPhoneTweetFeed {

    private static final String SEARCH_ENDPOINT = "https://api.twitter.com/1.1/search/tweets.json?q={query}";

    private Feed getLastTwoWeeksFeed(String query) {
        RestTemplate restTemplate = new OAuthRestTemplate(new TwitterOAuthCredentials());
        return restTemplate.getForObject(SEARCH_ENDPOINT, Feed.class, query, "since:" + twoWeeksAgo());
    }

    public Feed getPayByPhoneUkLastTwoWeeksFeed() {
        return this.getLastTwoWeeksFeed("PayByPhone_UK");
    }

    public Feed getPayByPhoneLastTwoWeeksFeed() {
        return this.getLastTwoWeeksFeed("PayByPhone");
    }

    public Feed getPay_By_PhoneUkLastTwoWeeksFeed() {
        return this.getLastTwoWeeksFeed("pay_by_phone");
    }

    // Helper method
    private String twoWeeksAgo() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -14);
        return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
    }

}
