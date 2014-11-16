import JsonToObjectMappings.Feed;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class FeedController {

    @RequestMapping("/feed")
    @ResponseBody
    public String payByPhoneFeed() throws JsonProcessingException {

        PayByPhoneTweetFeed feed = new PayByPhoneTweetFeed();
        Feed payByPhone = feed.getPayByPhoneLastTwoWeeksFeed();
        Feed payByPhone_Uk = feed.getPayByPhone_UkLastTwoWeeksFeed();
        Feed pay_by_phone = feed.getPay_By_PhoneLastTwoWeeksFeed();

        PayByPhoneTweetAggregator aggregator = new PayByPhoneTweetAggregator();
        List<AggregatedTweet> aggregatedTweets = aggregator.mergeInformation(payByPhone, payByPhone_Uk, pay_by_phone);

        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(aggregatedTweets);

    }

    public static void main(String args[]) {
        SpringApplication.run(FeedController.class, args);
    }

}
