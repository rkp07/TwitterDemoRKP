package rkp.com.twitterdemorkp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

public class TweetAdapter extends BaseAdapter {
    private Context mCtx;
    private TweetList tw_lists;

    public TweetAdapter(Context mContext, TweetList twList) {
        this.mCtx = mContext;
        this.tw_lists = twList;
    }

    public void setTwList(TweetList lists) {
        this.tw_lists = lists;
    }

    @Override
    public int getCount() {
        if (tw_lists.tweets != null) {
            return tw_lists.tweets.size();
        } else {
            return 0;
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        final ViewHolder holder;

        if (row == null) {
            LayoutInflater inflater = ((Activity) mCtx).getLayoutInflater();
            row = inflater.inflate(R.layout.row_tweet, parent, false);
            holder = new ViewHolder();
            holder.textTweet = (TextView) row.findViewById(R.id.text_tweet);
            holder.textUser = (TextView) row.findViewById(R.id.text_user);
            holder.imageLogo = (ImageView) row.findViewById(R.id.image_user_logo);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }
        holder.textTweet.setText(tw_lists.tweets.get(position).text);
        holder.textUser.setText(tw_lists.tweets.get(position).user.name);
        Picasso.with(mCtx).load(tw_lists.tweets.get(position).user.profileImageUrl).into(holder.imageLogo);
        return row;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    static class ViewHolder {
        TextView textTweet;
        TextView textUser;
        ImageView imageLogo;
    }
}