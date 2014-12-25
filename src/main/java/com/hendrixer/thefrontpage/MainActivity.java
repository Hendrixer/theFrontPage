package com.hendrixer.thefrontpage;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {
    private final String TAG = getClass().getSimpleName();
    private RecyclerView mRecyclerView;
    private RedditPostAdapter mRedditPostAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        RedditClient.get(new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject data){
                Toast.makeText(getApplicationContext(), "Got them datas!", Toast.LENGTH_LONG).show();

                ArrayList<RedditPost> redditPosts = RedditPost.fromJson(data);

                RedditPost firstOne = redditPosts.get(3);
                Log.d(TAG, firstOne.getAuthor());

                mRedditPostAdapter = new RedditPostAdapter(redditPosts, R.layout.item_reddit_post_card, getBaseContext());
                mRecyclerView.setAdapter(mRedditPostAdapter);

            }

            public void onFailure(Throwable first){
               Toast.makeText(getBaseContext(), "FAILED", Toast.LENGTH_LONG);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
