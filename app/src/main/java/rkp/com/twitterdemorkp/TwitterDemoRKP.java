package rkp.com.twitterdemorkp;

import android.os.Bundle;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.squareup.otto.Bus;
import retrofit.RestAdapter;

public class TwitterDemoRKP extends AppCompatActivity {
    private static final String TAG = TwitterDemoRKP.class.getName();

    private ServiceProvider mService;
    private Bus bus =  Globals.getBusInstance();
    private EditText edBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twitter_demo_rkp);

        edBox = (EditText) findViewById(R.id.edit_search_tag);
        mService = new ServiceProvider(buildApi(), bus,this);
        bus.register(mService);
        bus.register(this);

        Button buttonSearch = (Button) findViewById(R.id.button_search);
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(edBox.getText())) {
                    SearchResultFragment fragment = new SearchResultFragment();
                    Bundle args = new Bundle();
                    args.putString(SearchResultFragment.ARG_SEARCH_REQUEST, edBox.getText().toString());
                    fragment.setArguments(args);

                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.fragment_place_holder, fragment);
                    ft.commit();

                } else {
                    //TODO: Display Toast about invalid Input.
                }
            }
        });
    }

    private ApiService buildApi() {
        return new RestAdapter.Builder()
                .setEndpoint(Globals.SEARCH_URL)
                .build()
                .create(ApiService.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_twitter_demo_rk, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
