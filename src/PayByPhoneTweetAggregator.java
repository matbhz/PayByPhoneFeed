import JsonToObjectMappings.Feed;
import JsonToObjectMappings.Status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PayByPhoneTweetAggregator {

    private static final List<String> payByPhoneUsers = Arrays.asList("@PayByPhone", "@PayByPhone_UK", "@pay_by_phone");

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

        for (Status status : statuses)
            tweets.add(new Tweet(status.text, status.createdAt));

        return tweets;

    }

    private int retrieveNumberOfUserMentions(List<Status> statuses) {

        int numberOfMentions = 0;
        for (Status status : statuses) {
            for (String word : status.text.split(" ")) {
                // If a word starts with '@' and it's not PayByPhone it means it's mentioned user. (Not an e-mail address)
                if (!word.equals("") && word.charAt(0) == '@' && !payByPhoneUsers.contains(word)) {
                    numberOfMentions++;
                }
            }
        }

        return numberOfMentions;
    }

}
