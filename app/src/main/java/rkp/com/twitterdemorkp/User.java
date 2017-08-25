package rkp.com.twitterdemorkp;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("screen_name")
    public String screenName;

    @SerializedName("name")
    public String name;

    @SerializedName("profile_image_url")
    public String profileImageUrl;

}