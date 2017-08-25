package rkp.com.twitterdemorkp;

public class SearchEvent {

    public final String h_tag;
    public final String tw_token;

    public SearchEvent(String token, String hashtag) {
        this.h_tag = hashtag;
        this.tw_token = token;
    }
}