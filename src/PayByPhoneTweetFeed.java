import JsonToObjectMappings.Feed;
import org.springframework.security.oauth.consumer.client.OAuthRestTemplate;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PayByPhoneTweetFeed {

    private final String SEARCH_ENDPOINT = "https://api.twitter.com/1.1/search/tweets.json?q={query}";
    // FIXME: Twitter API is behaving very oddly and not returning values most of the times
    private final String SEARCH_ALL_CRITERIA = "from:pay_by_phone OR from:PayByPhone OR from:PayByPhone_UK";// since:" + twoWeeksAgo();

    public Feed getLastTwoWeeksFeed() {

        OAuthRestTemplate restTemplate = new OAuthRestTemplate(new TwitterOAuth());
        Feed feed = restTemplate.getForObject(SEARCH_ENDPOINT, Feed.class, URLEncoder.encode(SEARCH_ALL_CRITERIA));

        return feed;
    }



    private String twoWeeksAgo() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -14);
        System.out.println(cal.getTime());
        return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
    }

}
