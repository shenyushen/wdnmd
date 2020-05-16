package com.example.a24168.myapplication.market.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.GridView;
import android.widget.ListView;

import com.example.a24168.myapplication.R;

public class TypeViewHolder extends RecyclerView.ViewHolder {
    public GridView recyclerView;

    public TypeViewHolder(@NonNull View itemView) {
        super(itemView);
        recyclerView = itemView.findViewById(R.id.rec);
    }
}
