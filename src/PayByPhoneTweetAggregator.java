import JsonToObjectMappings.Feed;
import JsonToObjectMappings.Status;

import java.util.ArrayList;
import java.util.List;

public class PayByPhoneTweetAggregator {

    public List<AggregatedTweet> mergeInformation(Feed... feeds) {

        List<AggregatedTweet> aggregatedTweets = new ArrayList<AggregatedTweet>();

        for (Feed feed : feeds) {

            if (feed.statuses == null)
                continue;

            AggregatedTweet aggregatedTweet = new AggregatedTweet();
            aggregatedTweet.setAccountName(feed.statuses.get(0).user.name);
            aggregatedTweet.setNumberOfTweets(feed.statuses.size());
            aggregatedTweet.setNumberOfUserMentions(retrieveNumberOfUserMentions(feed.statuses));
            aggregatedTweet.setTweets(readTweets(feed.statuses));

            aggregatedTweets.add(aggregatedTweet);

        }

        return aggregatedTweets;

    }

    private List<Tweet> readTweets(List<Status> statuses) {

        List<Tweet> tweets = new ArrayList<Tweet>();

        for(Status status : statuses)
            tweets.add(new Tweet(status.text, status.createdAt));

        return tweets;

    }

    private int retrieveNumberOfUserMentions(List<Status> statuses) {
        return 0;
    }

}
