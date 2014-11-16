import JsonToObjectMappings.Feed;
import org.junit.Test;

import java.util.List;

public class AcceptanceTest {

    @Test
    public void UATTest() {

        // 1st - Fetch online the Feed of the 3 different users
        PayByPhoneTweetFeed feed = new PayByPhoneTweetFeed();
        Feed a = feed.getPayByPhoneLastTwoWeeksFeed();
        Feed b = feed.getPayByPhone_UkLastTwoWeeksFeed();
        Feed c = feed.getPay_By_PhoneLastTwoWeeksFeed();

        // 2nd - Aggregate relevant information in a better format and include Tweet Count and User mentions for each respective account
        PayByPhoneTweetAggregator aggregator = new PayByPhoneTweetAggregator();
        List<AggregatedTweet> aggregatedTweets = aggregator.mergeInformation(a, b, c);

        // 3rd - Outputs the aggregate object into JSON
        // TODO

    }
}
