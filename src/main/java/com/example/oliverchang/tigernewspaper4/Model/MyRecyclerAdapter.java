package com.example.oliverchang.tigernewspaper4.Model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.oliverchang.tigernewspaper4.R;
import com.squareup.picasso.Picasso;
import java.util.List;


/**
 * Created by Oliver Chang on 10/22/2016.
 */

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.CustomViewHolder>{
    private List<FeedItem> feedItemList;
    private Context mContext;
    private OnItemClickListener onItemClickListener;

    public MyRecyclerAdapter(Context context, List<FeedItem> feedItemList) {
        this.feedItemList = feedItemList;
        this.mContext = context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_row2, null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(CustomViewHolder customViewHolder, int i) {
        final FeedItem feedItem = feedItemList.get(i);

        //Download image using picasso library
        if (!TextUtils.isEmpty(feedItem.getThumbnail())) {
            Picasso.with(mContext).load(feedItem.getThumbnail())
                    .error(R.drawable.placeholder)
                    .placeholder(R.drawable.placeholder)
                    .into(customViewHolder.imageView);
        }

        //Setting text view title
        customViewHolder.textView.setText(Html.fromHtml(feedItem.getTitle()));
        //Setting date
        customViewHolder.date.setText(Html.fromHtml(feedItem.getDate()));

        View.OnClickListener listener = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(feedItem);
                //Toast.makeText(mContext, "YAY! :D", Toast.LENGTH_SHORT).show();
            }
        };
        customViewHolder.imageView.setOnClickListener(listener);
        customViewHolder.textView.setOnClickListener(listener);
        //customViewHolder.relativeLayout.setOnClickListener(listener);
        customViewHolder.readButton.setOnClickListener(listener);

    }

    @Override
    public int getItemCount() {
        return (null != feedItemList ? feedItemList.size() : 0);
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        protected ImageView imageView;
        protected TextView textView;
        //protected RelativeLayout relativeLayout;
        protected Button readButton;
        protected TextView date;

        public CustomViewHolder(View view) {
            super(view);
            this.imageView = (ImageView) view.findViewById(R.id.thumbnail);
            this.textView = (TextView) view.findViewById(R.id.title);
            //this.relativeLayout = (RelativeLayout) view.findViewById(R.id.card_relative_layout);
            this.readButton = (Button) view.findViewById(R.id.read_button);
            this.date = (TextView) view.findViewById(R.id.date_text);
        }
    }

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
}
