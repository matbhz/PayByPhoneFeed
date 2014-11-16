import JsonToObjectMappings.Feed;
import JsonToObjectMappings.Status;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PayByPhoneTweetFeedTest {

    @Before
    public void setup() {

    }

    @Test
    public void shouldRetrieveAFeedForAccountPayByPhone() {
        PayByPhoneTweetFeed payByPhoneTweetFeed = new PayByPhoneTweetFeed();
        Feed f = payByPhoneTweetFeed.getPayByPhoneLastTwoWeeksFeed();

        for (Status status : f.statuses)
            assertEquals(status.user.name, "PayByPhone");
    }

    @Test
    public void shouldRetrieveAFeedForAccountPayByPhone_UK() {
        PayByPhoneTweetFeed payByPhoneTweetFeed = new PayByPhoneTweetFeed();
        Feed f = payByPhoneTweetFeed.getPayByPhone_UkLastTwoWeeksFeed();

        for (Status status : f.statuses)
            assertEquals(status.user.name, "PayByPhone_UK");
    }

    @Test
    public void shouldRetrieveAFeedForAccountPay_By_Phone() {
        PayByPhoneTweetFeed payByPhoneTweetFeed = new PayByPhoneTweetFeed();
        Feed f = payByPhoneTweetFeed.getPay_By_PhoneLastTwoWeeksFeed();

        for (Status status : f.statuses)
            assertEquals(status.user.name, "pay_by_phone");
    }
}
