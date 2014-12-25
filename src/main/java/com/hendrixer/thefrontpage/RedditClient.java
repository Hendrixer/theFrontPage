package com.hendrixer.thefrontpage;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;


/**
 * Created by Hendrixer on 12/24/14.
 */
public class RedditClient {
    private static final String BASE_URL = "https://www.reddit.com/.json";
    private static AsyncHttpClient client = new AsyncHttpClient();
    public static void get(AsyncHttpResponseHandler callback){
        client.get(BASE_URL, null, callback);
    }
}
