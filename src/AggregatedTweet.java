import java.util.List;

public class AggregatedTweet {

    private String account;
    private List<Tweet> tweets;
    private int numberOfTweets;
    private int numberOfUserMentions;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
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
