package com.example.tigernewspapaer2;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements OnFeedListener {
    static final String URL = "http://www.tigernewspaper.com/wordpress/wp-json/wp/v2/posts";
    ListView listView;
    ArrayList<Post>posts;
    FeedAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = (ListView)findViewById(R.id.listView);

        adapter = new FeedAdapter(getApplicationContext(), R.layout.layout_feed_item);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Post post = posts.get(position);

                Intent intent = new Intent(getApplicationContext(), PostActivity.class);
                intent.putExtra("content", post.content);

                startActivity(intent);
            }
        });

        FeedTask task = new FeedTask(this);
        task.execute(URL);

//      NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//      navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onFeed(JSONArray array) throws JSONException {
        posts = Post.parse(array);

        adapter.addAll(posts);
        adapter.notifyDataSetChanged();
    }

    public class FeedTask extends AsyncTask<String, Void, JSONArray>{
        private OnFeedListener listener;

        public FeedTask(OnFeedListener listener){
            this.listener = listener;
        }

        @Override
        protected JSONArray doInBackground(String... params) {

            String url = params[0];

            OkHttpClient client = new OkHttpClient();
            Request.Builder builder = new Request.Builder();

            Request request = builder.url(url).build();

            try
            {
                Response response = client.newCall(request).execute();

                String json = response.body().string();

                try
                {
                    JSONArray array = new JSONArray(json);
                    return array;
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                    System.out.println("ERROR IS HERE");
                }

            }
            catch (IOException e) {
                e.printStackTrace();
                System.out.println("ERROR IS HERE TOOO");
            }
            return null;
        }

        @Override
        protected void onPostExecute(JSONArray array) {
            super.onPostExecute(array);
            if(null == array){
                return;
            }
            if(null != listener){
                try {
                    listener.onFeed(array);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public class FeedAdapter extends ArrayAdapter<Post>{
        private int resource;
        public FeedAdapter(Context context, int resource) {
            super(context, resource);
            this.resource = resource;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            if(null == convertView){
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(resource , null);
            }

            Post post = getItem(position);
            TextView title = (TextView) convertView.findViewById(R.id.title);
            TextView description = (TextView) convertView.findViewById(R.id.description);
            ImageView thumbnail = (ImageView)convertView.findViewById(R.id.thumnail);

            title.setText(post.title);
            description.setText(post.description);

            String url = post.thumbnail;

            Glide.with(getContext()).load(url).diskCacheStrategy(DiskCacheStrategy.ALL).dontAnimate().into(thumbnail);

            return convertView;
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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
