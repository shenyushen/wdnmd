package com.example.a24168.myapplication.market.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.GridView;
import android.widget.HorizontalScrollView;

import com.example.a24168.myapplication.R;

public class MarketNewViewHolder extends RecyclerView.ViewHolder {

    public HorizontalScrollView horizontalScrollView;

    public MarketNewViewHolder(@NonNull View itemView) {
        super(itemView);
        horizontalScrollView = itemView.findViewById(R.id.hs_menu);

    }
}
