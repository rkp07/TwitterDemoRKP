package rkp.com.twitterdemorkp;

import android.os.Bundle;
import android.app.ListFragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

public class SearchResultFragment extends ListFragment {
    private Bus mBus;
    private String request;
    private TweetAdapter brandAdapter;
    private TextView textResult;

    private static final String TAG = SearchResultFragment.class.getName();

    public static final String ARG_SEARCH_REQUEST = "request";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list_tweets, container, false);
        brandAdapter = new TweetAdapter(getActivity(), new TweetList());
        setListAdapter(brandAdapter);
        request = getArguments().getString(ARG_SEARCH_REQUEST);
        Log.d("TwitterDemo","SearchResultFragment:onCreateView");
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        getBus().register(this);
        if (TextUtils.isEmpty(Globals.getAccessToken(getActivity()))) {
            getBus().post(new GetTokenEvent());
        } else {
            String token = Globals.getAccessToken(getActivity());
            getBus().post(new SearchEvent(token, request));
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        getBus().unregister(this);
    }

    @Subscribe
    public void onTwitterGetTokenOk(GetTokenOkEvent event) {
        Log.d("TwitterDemo","onTwitterGetTokenOk called");
        getBus().post(new SearchEvent(Globals.getAccessToken(getActivity()), request));
    }

    @Subscribe
    public void onSearchTweetsEventOk(final SearchOkEvent event) {
        Log.d("TwitterDemo","onSearchTweetsEventOk called");
        brandAdapter.setTwList(event.tweetsList);
        brandAdapter.notifyDataSetChanged();
    }

    private Bus getBus() {
        if (mBus == null) {
            mBus = Globals.getBusInstance();
        }
        return mBus;
    }
    public void setBus(Bus bus) {
        mBus = bus;
    }
}
