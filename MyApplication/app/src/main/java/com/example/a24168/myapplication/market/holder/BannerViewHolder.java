package com.example.a24168.myapplication.market.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.a24168.myapplication.R;
import com.example.a24168.myapplication.market.entity.Banner;

/**
 *
 */
public class BannerViewHolder extends RecyclerView.ViewHolder {
    public ImageView imageView;
    public BannerViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView =itemView.findViewById(R.id.img);
    }
}
