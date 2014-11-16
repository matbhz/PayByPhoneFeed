import JsonToObjectMappings.Feed;
import org.springframework.security.oauth.consumer.client.OAuthRestTemplate;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PayByPhoneTweetFeed {

    // FIXME: Twitter API is behaving very oddly and not returning values most of the times
    private final String SEARCH_ENDPOINT = "https://api.twitter.com/1.1/search/tweets.json?q={query}";

    private Feed getLastTwoWeeksFeed(String query) {
        OAuthRestTemplate restTemplate = new OAuthRestTemplate(new TwitterOAuth());
        return restTemplate.getForObject(SEARCH_ENDPOINT, Feed.class, URLEncoder.encode(query) + " since:" + twoWeeksAgo());
    }

    public Feed getPayByPhoneUkLastTwoWeeksFeed() {
        return this.getLastTwoWeeksFeed("from:PayByPhone_UK");
    }

    public Feed getPayByPhoneLastTwoWeeksFeed() {
        return this.getLastTwoWeeksFeed("from:PayByPhone");
    }

    public Feed getPay_By_PhoneUkLastTwoWeeksFeed() {
        return this.getLastTwoWeeksFeed("from:pay_by_phone");
    }

    // Helper method
    private String twoWeeksAgo() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -14);
        System.out.println(cal.getTime());
        return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
    }

}
