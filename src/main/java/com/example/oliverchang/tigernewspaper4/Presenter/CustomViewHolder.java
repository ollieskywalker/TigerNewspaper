package com.example.oliverchang.tigernewspaper4.Presenter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oliverchang.tigernewspaper4.R;

/**
 * Created by Oliver Chang on 10/22/2016.
 */

public class CustomViewHolder extends RecyclerView.ViewHolder {
    protected ImageView imageView;
    protected TextView textView;
    protected TextView dateText;
    protected ImageView mainImageView;

    public CustomViewHolder(View view) {
        super(view);
        this.imageView = (ImageView) view.findViewById(R.id.thumbnail);
        this.textView = (TextView) view.findViewById(R.id.title);
        this.textView = (TextView) view.findViewById(R.id.date_text);
    }
}
