package rkp.com.twitterdemorkp;

public class SearchOkEvent {
    public final TweetList tweetsList;

    public SearchOkEvent(TweetList tweets) {
        this.tweetsList = tweets;
    }
}