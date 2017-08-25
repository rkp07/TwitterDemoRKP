package rkp.com.twitterdemorkp;

import android.content.Context;
import android.content.SharedPreferences;

import com.squareup.otto.Bus;

public class Globals {
    public final static String SEARCH_URL = "https://api.twitter.com";
    public final static String USER_KEY = "xH5eiKC6nQfJHBrxexyCMqSPq";
    public	final static String SECRET = "6feIH9Z3PqL9pRLdWOzxlNM753Q0AhavoeFW683jxYLUl6sdh6";
    public static final String BEARER_TOKEN = USER_KEY + ":" + SECRET;
    public final static String HASHTAG_SEARCH_CODE = "/1.1/search/tweets.json";

    private static final String GENERAL_PREFS_ID = "general_prefs";
    private static final String ACCESS_TOKEN = "access_token";
    private static final String ACCESS_TOKEN_TYPE = "token_status";

    private static Bus mBusInstance = null;

    private static SharedPreferences getPrefs(Context ctx) {
        return ctx.getSharedPreferences(GENERAL_PREFS_ID, Context.MODE_PRIVATE);
    }

    public static void setAccessToken(Context ctx, String token) {
        getPrefs(ctx).edit().putString(ACCESS_TOKEN, token).apply();
    }

    public static String getAccessToken(Context ctx) {
        return getPrefs(ctx).getString(ACCESS_TOKEN, "");
    }

    public static void setTokenType(Context ctx, String tokenType) {
        getPrefs(ctx).edit().putString(ACCESS_TOKEN_TYPE, tokenType).apply();
    }

    public static String getTokenType(Context ctx) {
        return getPrefs(ctx).getString(ACCESS_TOKEN_TYPE, "");
    }

    public static Bus getBusInstance() {
        if (mBusInstance == null) {
            mBusInstance = new Bus();
        }
        return mBusInstance;
    }
}
