import JsonToObjectMappings.Feed;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class FeedController {

    @RequestMapping("/feed")
    @ResponseBody
    public List<AggregatedTweet> payByPhoneFeed() throws JsonProcessingException {

        PayByPhoneTweetFeed feed = new PayByPhoneTweetFeed();
        Feed payByPhone = feed.getPayByPhoneLastTwoWeeksFeed();
        Feed payByPhone_Uk = feed.getPayByPhone_UkLastTwoWeeksFeed();
        Feed pay_by_phone = feed.getPay_By_PhoneLastTwoWeeksFeed();

        PayByPhoneTweetAggregator aggregator = new PayByPhoneTweetAggregator();
        return aggregator.mergeInformation(payByPhone, payByPhone_Uk, pay_by_phone);

    }

}
