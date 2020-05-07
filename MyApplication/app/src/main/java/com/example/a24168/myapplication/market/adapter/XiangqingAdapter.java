package com.example.a24168.myapplication.market.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.a24168.myapplication.R;
import com.example.a24168.myapplication.market.entity.Good;

import java.util.List;

public class XiangqingAdapter extends BaseAdapter {
    private List<Good> list;
    private Context context;
    private int id;

    public XiangqingAdapter(List<Good> list, Context context, int id) {
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

        ImageView imageView = convertView.findViewById(R.id.img2);
        TextView textView = convertView.findViewById(R.id.text2);
        TextView textView1 = convertView.findViewById(R.id.text3);
        TextView textView2 = convertView.findViewById(R.id.text4);
        TextView textView3 = convertView.findViewById(R.id.text5);
        Glide.with(context).load(context.getResources().getString(R.string.ip1)+"/upload/"+list.get(position).getImg()).into(imageView);
        textView.setText(list.get(position).getTitle());
        textView1.setText(list.get(position).getLittleContent());
        textView2.setText("￥"+list.get(position).getPrice()+"");
        textView3.setText("已销"+list.get(position).getSaleVolume());
        return convertView;
    }
    
}
