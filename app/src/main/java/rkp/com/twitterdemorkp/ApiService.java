package rkp.com.twitterdemorkp;

import retrofit.Callback;
import retrofit.http.*;

public interface ApiService {
    @GET(Globals.HASHTAG_SEARCH_CODE )
    void getTweetList(
            @Header("Authorization") String authorization,
            @Query("q") String hashtag,
            Callback<TweetList> callback
    );

    @FormUrlEncoded
    @POST("/oauth2/token")
    void getToken(
            @Header("Authorization") String authorization,
            @Field("grant_type") String grantType,
            Callback<TokenType> response
    );
}
