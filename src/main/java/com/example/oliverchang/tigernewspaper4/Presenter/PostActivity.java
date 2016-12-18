package com.example.oliverchang.tigernewspaper4.Presenter;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.oliverchang.tigernewspaper4.R;
import com.squareup.picasso.Picasso;


import static android.R.id.content;

public class PostActivity extends AppCompatActivity {
    private WebView webView;
    private ImageView imageView;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        Intent i = getIntent();
        Bundle extras = i.getExtras();

        webView = (WebView) findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.setScrollContainer(false);
        String content = extras.getString("content");
        webView.loadData(content,"text/html; charset=UTF-8",null);

        imageView = (ImageView) findViewById(R.id.imageView2);
        String imgUrl = extras.getString("url");
        Picasso.with(this).load(imgUrl).into(imageView);

        textView = (TextView) findViewById(R.id.textView);
        String title = extras.getString("title");
        textView.setText(title);

    }
}
