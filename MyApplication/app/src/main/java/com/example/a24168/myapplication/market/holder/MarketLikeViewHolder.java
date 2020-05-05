package com.example.a24168.myapplication.market.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.GridView;

import com.example.a24168.myapplication.R;

public class MarketLikeViewHolder extends RecyclerView.ViewHolder {
    public GridView gridView;

    public MarketLikeViewHolder(@NonNull View itemView) {
        super(itemView);
        gridView = itemView.findViewById(R.id.grid3);
    }
}
