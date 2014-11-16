import java.util.List;

public class AggregatedTweet {

    private String accountName;
    private List<Tweet> tweets;
    private int numberOfTweets;
    private int numberOfUserMentions;

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public List<Tweet> getTweets() {
        return tweets;
    }

    public void setTweets(List<Tweet> tweets) {
        this.tweets = tweets;
    }

    public int getNumberOfTweets() {
        return numberOfTweets;
    }

    public void setNumberOfTweets(int numberOfTweets) {
        this.numberOfTweets = numberOfTweets;
    }

    public int getNumberOfUserMentions() {
        return numberOfUserMentions;
    }

    public void setNumberOfUserMentions(int numberOfUserMentions) {
        this.numberOfUserMentions = numberOfUserMentions;
    }
}
