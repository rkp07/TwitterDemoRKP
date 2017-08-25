package rkp.com.twitterdemorkp;

import android.content.Context;
import android.util.Base64;
import android.util.Log;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import java.io.UnsupportedEncodingException;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ServiceProvider {
    private static final String TAG = ServiceProvider.class.getName();

    private ApiService mApi;
    private Bus mBus;
    private Context mContext;

    public ServiceProvider(ApiService api, Bus bus, Context mCtx) {
        this.mApi = api;
        this.mBus = bus;
        this.mContext = mCtx;
    }

    @Subscribe
    public void onLoadTweets(final SearchEvent event) {
        Log.d(TAG,"onLoadTweets called");
        mApi.getTweetList("Bearer " + event.tw_token, event.h_tag, new Callback<TweetList>() {
            @Override
            public void success(TweetList response, Response rawResponse) {
                mBus.post(new SearchOkEvent(response));
                Log.d(TAG,"onLoadTweets success");
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, error.toString(), error);
            }
        });
    }

    @Subscribe
    public void onGetToken(GetTokenEvent event) {
        Log.d(TAG,"onGetToken called");

        try {
            mApi.getToken("Basic " + getBase64String(Globals.BEARER_TOKEN), "client_credentials", new Callback<TokenType>() {
                @Override
                public void success(TokenType token, Response response) {
                    Globals.setAccessToken(mContext, token.accessToken);
                    Globals.setTokenType(mContext, token.tokenType);
                    mBus.post(new GetTokenOkEvent());
                }

                @Override
                public void failure(RetrofitError error) {
                    Log.e(TAG, error.toString(), error);
                }
            });
        } catch (UnsupportedEncodingException e) {
            Log.e(TAG, e.toString(), e);
        }
    }

    public static String getBase64String(String value) throws UnsupportedEncodingException {
        return Base64.encodeToString(value.getBytes("UTF-8"), Base64.NO_WRAP);
    }
}
