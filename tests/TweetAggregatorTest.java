import JsonToObjectMappings.Feed;
import JsonToObjectMappings.Status;
import JsonToObjectMappings.User;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TweetAggregatorTest {

    @Before
    public void setup() {

    }

    @Test
    public void shouldReturnAListWith3DifferentFeeds_when3ValidFeedsAreProvided() {

        Feed a = buildFeed("User A", "A1 tweet");
        Feed b = buildFeed("User B", "B1 tweet", "B2 tweet", "B3 tweet");
        Feed c = buildFeed("User C", "C1 tweet", "C2 tweet");

        PayByPhoneTweetAggregator aggregator = new PayByPhoneTweetAggregator();
        List<AggregatedTweet> output = aggregator.mergeInformation(a, b, c);

        assertEquals(output.size(), 3);

        assertEquals(output.get(0).getAccountName(), a.statuses.get(0).user.name);
        assertEquals(output.get(1).getAccountName(), b.statuses.get(0).user.name);
        assertEquals(output.get(2).getAccountName(), c.statuses.get(0).user.name);

    }

    @Test
    public void shouldReturnAListWith2DifferentFeeds_whenAFeedHasNoStatus() {

        Feed a = new Feed();
        Feed b = buildFeed("User B", "B1 tweet", "B2 tweet", "B3 tweet");
        Feed c = buildFeed("User C", "C1 tweet", "C2 tweet");

        PayByPhoneTweetAggregator aggregator = new PayByPhoneTweetAggregator();
        List<AggregatedTweet> output = aggregator.mergeInformation(a, b, c);

        assertEquals(output.size(), 2);

        assertEquals(output.get(0).getAccountName(), b.statuses.get(0).user.name);
        assertEquals(output.get(1).getAccountName(), c.statuses.get(0).user.name);

    }

    @Test
    public void shouldReturnAListWithASingleValue_whenOnlyOneValidFeedIsProvide() {

        Feed a = buildFeed("User A", "A1 tweet");

        PayByPhoneTweetAggregator aggregator = new PayByPhoneTweetAggregator();
        List<AggregatedTweet> output = aggregator.mergeInformation(a);

        assertEquals(output.size(), 1);

        assertEquals(output.get(0).getAccountName(), a.statuses.get(0).user.name);
    }

    @Test
    public void shouldReturnAnEmptyList_whenNoFeedIsProvided() {
        PayByPhoneTweetAggregator aggregator = new PayByPhoneTweetAggregator();
        List<AggregatedTweet> output = aggregator.mergeInformation();

        assertEquals(output.size(), 0);
    }

    @Test
    public void shouldContainTheNumberOfMessages_whenFeedIsAggregated() {

        Feed a = buildFeed("User A", "A1 tweet");
        Feed b = buildFeed("User B", "B1 tweet", "B2 tweet", "B3 tweet");
        Feed c = buildFeed("User C", "C1 tweet", "C2 tweet");

        PayByPhoneTweetAggregator aggregator = new PayByPhoneTweetAggregator();
        List<AggregatedTweet> output = aggregator.mergeInformation(a, b, c);

        assertEquals(output.get(0).getTweets().size(), a.statuses.size());
        assertEquals(output.get(1).getTweets().size(), b.statuses.size());
        assertEquals(output.get(2).getTweets().size(), c.statuses.size());

    }

    @Test
    public void shouldContain3UserMentions(){

        Feed a = buildFeed("User A", "I am @PayByPhone and I mentioning @matbhx! Hello!");
        Feed b = buildFeed("User B", "I'm not mentioning anyone, but here's my e-mail lol@cats.com");
        Feed c = buildFeed("User C", "Greetings to new @PayByPhone_UK followers: @parkMyCar and @parkMySpaceship!");

        PayByPhoneTweetAggregator aggregator = new PayByPhoneTweetAggregator();
        List<AggregatedTweet> output = aggregator.mergeInformation(a, b, c);

        int mentions = 0;

        mentions += output.get(0).getNumberOfUserMentions();
        mentions += output.get(1).getNumberOfUserMentions();
        mentions += output.get(2).getNumberOfUserMentions();

        assertEquals(mentions, 3);

    }

    // Builds a mock Feed (JSON) object
    private Feed buildFeed(String name, String... texts) {

        User user = new User();
        user.name = name;

        Status status = new Status();
        for (String text : texts) {
            status.user = user;
            status.text = text;
        }

        Feed feed = new Feed();
        feed.statuses = new ArrayList<Status>();
        feed.statuses.add(status);

        return feed;

    }

}
