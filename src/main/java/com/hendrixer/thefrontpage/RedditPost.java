package com.hendrixer.thefrontpage;

import android.util.Log;
import android.webkit.URLUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Hendrixer on 12/23/14.
 */
public class RedditPost {
    private final String TAG = getClass().getSimpleName();
    private String subreddit;
    private String id;
    private long score;
    private String link;
    private String thumb;
    private String author;

    public static RedditPost formatObject(JSONObject jsonObject){
        RedditPost post = new RedditPost();
        try {
//            Log.d("POST", jsonObject.toString(2));
            jsonObject = jsonObject.getJSONObject("data");
            post.subreddit = jsonObject.getString("subreddit");
            post.id = jsonObject.getString("id");
            post.score = jsonObject.getInt("score");
            post.link = jsonObject.getString("url");
            post.thumb = jsonObject.getString("thumbnail");
            post.author = jsonObject.getString("author");
        } catch (JSONException e){
            e.printStackTrace();
            return null;
        }
        return post;
    }

    public static ArrayList<RedditPost> fromJson(JSONObject jsonObject) {
        JSONArray jsonPosts;
        try{
            JSONObject data = jsonObject.getJSONObject("data");
            jsonPosts = data.getJSONArray("children");

        } catch (JSONException e){
            e.printStackTrace();
            return null;
        }
        Log.d("ARRAY COUNT!!", jsonPosts.length() + "");
        ArrayList<RedditPost> redditPosts = new ArrayList<>(jsonPosts.length());

        for (int i = 0; i < jsonPosts.length(); i++) {
            JSONObject redditJson = null;
            try {
                redditJson = jsonPosts.getJSONObject(i);

            } catch (Exception e){
                e.printStackTrace();
                continue;
            }

            RedditPost redditPost = RedditPost.formatObject(redditJson);
            if (redditPost != null){
                redditPosts.add(redditPost);
            }
        }

        return redditPosts;
    }

    public String getAuthor(){
        return this.author;
    }
    public String getSubreddit() { return this.subreddit; }
    public String getThumb(){
        String img;

        if (URLUtil.isValidUrl(this.thumb) != true) {
            img = "https://cdn-assets.wanelo.com/assets/default_avatar_x200-51f6f7c0a923ac2a2ce1cfe7f99e2108.jpg";
        } else {
            img = this.thumb;
        }

        return img;
    }

}
