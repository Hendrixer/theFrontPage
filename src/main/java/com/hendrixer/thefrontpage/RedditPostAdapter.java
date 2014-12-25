package com.hendrixer.thefrontpage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.squareup.picasso.Picasso;

import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Hendrixer on 12/24/14.
 */
public class RedditPostAdapter extends RecyclerView.Adapter<RedditPostAdapter.ViewHolder> {
    private ArrayList<RedditPost> posts;
    private int rowLayout;
    private Context mContext;

    public RedditPostAdapter(ArrayList<RedditPost> redditPosts, int rowLayout, Context context){
        this.posts = redditPosts;
        this.rowLayout = rowLayout;
        this.mContext = context;
    }

//    @Override
//    public View getView(int position, View convertView, ViewGroup parent){
//        RedditPost post = getItem(position);
//
//        if (convertView == null) {
//            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_reddit_post, parent, false);
//        }
//
//        TextView author = (TextView) convertView.findViewById(R.id.author);
//        TextView subreddit = (TextView) convertView.findViewById(R.id.subreddit);
//        ImageView thumbnail = (ImageView) convertView.findViewById(R.id.thumbnail);
//
//        author.setText(post.getAuthor());
//        subreddit.setText(post.getSubreddit());
//
//        Picasso.with(getContext())
//                .load(post.getThumb())
//                .resize(140, 140)
//                .centerCrop()
//                .into(thumbnail);
//
//        return convertView;
//    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        RedditPost redditPost = posts.get(i);
//        viewHolder.countryName.setText(redditPost.author);
//        viewHolder.countryImage.setImageDrawable(mContext.getDrawable(country.getImageResourceId(mContext)));
        viewHolder.author.setText(redditPost.getAuthor());
        viewHolder.subreddit.setText(redditPost.getSubreddit());
        Picasso.with(mContext)
                .load(redditPost.getThumb())
                .resize(300, 140)
                .centerCrop()
                .into(viewHolder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return posts == null ? 0 : posts.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView author;
        public TextView subreddit;
        public ImageView thumbnail;

        public ViewHolder(View itemView) {
            super(itemView);
            author = (TextView) itemView.findViewById(R.id.author);
            subreddit = (TextView) itemView.findViewById(R.id.subreddit);
            thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);

        }

    }

}
