import JsonToObjectMappings.Feed;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PayByPhoneTweetFeed {

    private static final String SEARCH_ENDPOINT = "https://api.twitter.com/1.1/search/tweets.json?q={from} {since}";

    private Feed getLastTwoWeeksFeed(String query) {
        RestTemplate restTemplate = new TwitterOAuthRestTemplate(new TwitterOAuthCredentials());
        return restTemplate.getForObject(SEARCH_ENDPOINT, Feed.class, query, "since:" + twoWeeksAgo());
    }

    public Feed getPayByPhone_UkLastTwoWeeksFeed() {
        return this.getLastTwoWeeksFeed("from:PayByPhone_UK");
    }

    public Feed getPayByPhoneLastTwoWeeksFeed() {
        return this.getLastTwoWeeksFeed("from:PayByPhone");
    }

    public Feed getPay_By_PhoneLastTwoWeeksFeed() {
        return this.getLastTwoWeeksFeed("from:pay_by_phone");
    }

    // Helper method
    private String twoWeeksAgo() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -14);
        return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
    }

}
