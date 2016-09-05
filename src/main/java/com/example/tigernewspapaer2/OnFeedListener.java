package com.example.tigernewspapaer2;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by oliver on 8/27/16.
 */

public interface OnFeedListener{
    void onFeed(JSONArray array) throws JSONException;
}
