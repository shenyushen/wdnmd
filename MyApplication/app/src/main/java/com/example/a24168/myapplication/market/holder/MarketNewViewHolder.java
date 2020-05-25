package com.example.a24168.myapplication.market.holder;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.GridView;
import android.widget.HorizontalScrollView;

import com.example.a24168.myapplication.R;

public class MarketNewViewHolder extends RecyclerView.ViewHolder {

    public RecyclerView recyclerView;

    public MarketNewViewHolder(@NonNull View itemView) {
        super(itemView);
        recyclerView = itemView.findViewById(R.id.r1);

    }
}
