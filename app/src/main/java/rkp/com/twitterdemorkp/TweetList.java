package rkp.com.twitterdemorkp;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TweetList  {
    @SerializedName("statuses")
    public ArrayList<Tweet> tweets;
}