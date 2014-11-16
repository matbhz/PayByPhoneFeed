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
    public void shouldReturnAListWith3DifferentFeeds() {

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
