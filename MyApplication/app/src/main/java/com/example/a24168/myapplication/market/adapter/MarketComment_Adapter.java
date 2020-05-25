package com.example.a24168.myapplication.market.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.a24168.myapplication.R;
import com.example.a24168.myapplication.market.entity.MarketComment;

import java.util.List;

public class MarketComment_Adapter extends BaseAdapter {
    private List<MarketComment> list;
    private Context context;
    private int id;
    public MarketComment_Adapter(List<MarketComment> list, Context context, int id) {
        this.list = list;
        this.context = context;
        this.id = id;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(null == convertView){
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(id,null);
        }

        TextView textView = convertView.findViewById(R.id.market_comment_name);
        TextView textView2 = convertView.findViewById(R.id.market_comment_comment);
        ImageView img2 = convertView.findViewById(R.id.market_comment);
        textView.setText("@"+list.get(position).getMarket_name());
        textView2.setText(list.get(position).getMarket_comment());
        Glide.with(context).load(context.getResources().getString(R.string.ip1)+"/upload/"+list.get(position).getImg()).into(img2);

        return convertView;
    }
}
