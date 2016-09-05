package com.example.tigernewspapaer2;

import android.text.Html;
import android.text.Spanned;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Oliver Chang on 9/3/2016.
 */

public class Post{
    public String title;
    public String description;
    public String thumbnail;
    public String content;

    public static Post parse(JSONObject object) throws JSONException {
        Post post = new Post();
        JSONObject object1 = new JSONObject(object.optString("content"));
        JSONObject object2 = new JSONObject(object.optString("better_featured_image"));
        JSONObject object3 = new JSONObject(object.optString("title"));
        JSONObject object4 = new JSONObject(object.optString("excerpt"));
        String x = object3.optString("rendered");

        x = x.replace("&#8217;", "'");

        post.title = x;
        System.out.println(x.toLowerCase());
        post.description = Html.fromHtml(object4.optString("rendered")).toString();
        post.thumbnail = object2.optString("source_url");
        post.content = object1.optString("rendered");

        return post;
    }

    public static ArrayList<Post> parse(JSONArray array) throws JSONException {
        ArrayList<Post> posts = new ArrayList<>();

        int length = array.length();
        for(int i = 0; i < length; ++i){
            JSONObject object = array.optJSONObject(i);
            Post post = Post.parse(object);
            posts.add(post);
        }
        return posts;
    }
}
