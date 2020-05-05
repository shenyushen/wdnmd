package com.example.a24168.myapplication.market.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.GridView;
import android.widget.ListView;

import com.example.a24168.myapplication.R;

public class MarketCommentViewHolder extends RecyclerView.ViewHolder {
    public ListView View;

    public MarketCommentViewHolder(@NonNull View itemView) {
        super(itemView);
        View = itemView.findViewById(R.id.market_comment_listview);
    }
}
