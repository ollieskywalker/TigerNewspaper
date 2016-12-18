package com.example.oliverchang.tigernewspaper4.Model;

import org.json.JSONObject;

/**
 * Created by Oliver Chang on 10/22/2016.
 */

public class FeedItem {
    private String title;
    private String thumbnail;
    private String content;
    private String date;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getContent(){return content;}

    public void setContent(String content){this.content = content;}

    public String getDate(){return date;}

    public void setDate(String date){this.date = date;}

}
