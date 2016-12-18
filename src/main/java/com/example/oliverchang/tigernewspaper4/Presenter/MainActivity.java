package com.example.oliverchang.tigernewspaper4.Presenter;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.oliverchang.tigernewspaper4.Model.FeedItem;
import com.example.oliverchang.tigernewspaper4.Model.MyRecyclerAdapter;
import com.example.oliverchang.tigernewspaper4.Model.OnItemClickListener;
import com.example.oliverchang.tigernewspaper4.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private List<FeedItem> feedsList;
    private RecyclerView recentRecyclerView;
    private MyRecyclerAdapter adapter;
    private DrawerLayout drawerLayout;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout=(DrawerLayout)findViewById(R.id.activity_main);
        //listView=(ListView)findViewById(R.id.list);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        //Initialize recycler view
        recentRecyclerView = (RecyclerView)findViewById(R.id.content_recent);
        recentRecyclerView.setLayoutManager(layoutManager);

        //Download data from url
        final String url = "http://www.tigernewspaper.com/wordpress/wp-json/wp/v2/posts/?per_page=15";
        new AsyncHttpTask().execute(url);
    }

    public class AsyncHttpTask extends AsyncTask<String, Void, Integer> {

        @Override
        protected Integer doInBackground(String... params) {
            Integer result = 0;
            HttpURLConnection urlConnection;
            try{
                URL url = new URL(params[0]);
                urlConnection = (HttpURLConnection)url.openConnection();
                int statusCode = urlConnection.getResponseCode();
                //200 represents HTTP OK
                if(statusCode == 200){
                    BufferedReader r = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while((line = r.readLine())!=null){
                        response.append(line);
                    }
                    parseResult(response.toString());
                    result = 1; // Successful
                } else {
                    result = 0; // failed to fetch data
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(Integer result){
            // Download complete. Update UI
            if(result == 1){
                adapter = new MyRecyclerAdapter(MainActivity.this, feedsList);
                recentRecyclerView.setAdapter(adapter);
                adapter.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(FeedItem item) {
                        //Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(MainActivity.this, PostActivity.class);

                        Bundle extras = new Bundle();
                        extras.putString("content", item.getContent());
                        extras.putString("url", item.getThumbnail());
                        extras.putString("title", item.getTitle());
                        i.putExtras(extras);

                        startActivity(i);
                    }
                });
            } else {
                Toast.makeText(MainActivity.this, "FAILED to get data", Toast.LENGTH_SHORT).show();
            }
        }

        private void parseResult(String result){
            try{
//            JSONObject response = new JSONObject(result);
                JSONArray posts = new JSONArray(result);
                feedsList = new ArrayList<>();

                for (int i = 0; i < posts.length(); i++){
                    JSONObject post = posts.optJSONObject(i);
                    FeedItem item = new FeedItem();

                    JSONObject renderedTitle = new JSONObject(post.optString("title"));

                    JSONObject content = new JSONObject(post.optString("content"));

                    JSONObject betterImage = new JSONObject(post.optString("better_featured_image"));
                    JSONObject mediaDetails = new JSONObject(betterImage.optString("media_details"));
                    JSONObject sizes = new JSONObject(mediaDetails.optString("sizes"));
                    //JSONObject thumbnail = new JSONObject(sizes.optString("medium_large"));
                    String x = renderedTitle.optString("rendered");
                    x = x.replace("&#8217;", "'");

                    item.setTitle(x);
                    item.setThumbnail(betterImage.optString("source_url"));
                    item.setContent(content.optString("rendered"));
                    item.setDate(post.optString("date"));
                    //item.setMainImage(thumbnail.optString("source_url"));
                    feedsList.add(item);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}
