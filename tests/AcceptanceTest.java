import JsonToObjectMappings.Feed;
import JsonToObjectMappings.Status;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertEquals;

public class AcceptanceTest {

    @Test
    public void UATTest() throws JsonProcessingException {

        // 1st - Fetch online the Feed of the 3 different users
        PayByPhoneTweetFeed feed = new PayByPhoneTweetFeed();
        Feed feedA = feed.getPayByPhoneLastTwoWeeksFeed();
        Feed feedB = feed.getPayByPhone_UkLastTwoWeeksFeed();
        Feed feedC = feed.getPay_By_PhoneLastTwoWeeksFeed();

        for (Status s : feedA.statuses)
            assertEquals(s.user.name, "PayByPhone");

        for (Status s : feedB.statuses)
            assertEquals(s.user.name, "PayByPhone_UK");

        for (Status s : feedC.statuses)
            assertEquals(s.user.name, "pay_by_phone");

        // 2nd - Aggregate relevant information in a better format and include Tweet Count and User mentions for each respective account
        PayByPhoneTweetAggregator aggregator = new PayByPhoneTweetAggregator();
        List<AggregatedTweet> aggregatedTweets = aggregator.mergeInformation(feedA, feedB, feedC);

        assertEquals(aggregatedTweets.get(0).getAccountName(), "PayByPhone");
        assertEquals(aggregatedTweets.get(0).getNumberOfTweets(), feedA.statuses.size());

        assertEquals(aggregatedTweets.get(1).getAccountName(), "PayByPhone_UK");
        assertEquals(aggregatedTweets.get(1).getNumberOfTweets(), feedB.statuses.size());

        assertEquals(aggregatedTweets.get(2).getAccountName(), "pay_by_phone");
        assertEquals(aggregatedTweets.get(2).getNumberOfTweets(), feedC.statuses.size());

        // 3rd - Outputs the aggregate object into JSON
        FeedController controller = new FeedController();

        List<AggregatedTweet> resultAggregatedTweets = controller.payByPhoneFeed();

        assertEquals(aggregatedTweets.size(), resultAggregatedTweets.size());

        // 4rd Spring outputs the result of the controller as JSON
        ObjectMapper mapper = new ObjectMapper();
        String expectedJsonResult = mapper.writeValueAsString(aggregatedTweets);
        String controllerJsonResult = mapper.writeValueAsString(resultAggregatedTweets);

        assertEquals(expectedJsonResult, controllerJsonResult);

    }
}
