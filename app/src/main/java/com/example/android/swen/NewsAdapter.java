package com.example.android.swen;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsViewHolder> {
    ArrayList<NewsItems> mNewsItems;
    Context ctx;

    public NewsAdapter(ArrayList<NewsItems> newsItems) {
        mNewsItems = newsItems;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ctx = parent.getContext();
        return new NewsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        holder.getDescriptionTv().setText(mNewsItems.get(position).mNewsDescription);
        holder.getSourceTv().setText(mNewsItems.get(position).mNewsSource);
        Glide.with(ctx).load(mNewsItems.get(position).mImageUrl).into(holder.getNewsIv());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = mNewsItems.get(position).mWebUrl;
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                CustomTabsIntent customTabsIntent = builder.build();
                customTabsIntent.launchUrl(ctx, Uri.parse(url));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mNewsItems.size();
    }

}

class NewsViewHolder extends RecyclerView.ViewHolder {

    private ImageView newsIv;
    private TextView descriptionTv;
    private TextView sourceTv;

    public NewsViewHolder(@NonNull View itemView) {
        super(itemView);
        newsIv = itemView.findViewById(R.id.newsimageView);
        descriptionTv = itemView.findViewById(R.id.newsDescriptionTv);
        sourceTv = itemView.findViewById(R.id.newsSourceTv);

    }

    public TextView getDescriptionTv() {
        return descriptionTv;
    }

    public ImageView getNewsIv() {
        return newsIv;
    }

    public TextView getSourceTv() {
        return sourceTv;
    }
}
