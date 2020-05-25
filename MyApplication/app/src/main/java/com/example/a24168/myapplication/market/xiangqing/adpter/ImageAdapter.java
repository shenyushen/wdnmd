package com.example.a24168.myapplication.market.xiangqing.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.a24168.myapplication.R;

public class ImageAdapter extends BaseAdapter {
    private String[] s11;
    private Context context;
    private int id;

    public ImageAdapter(String[] s11, Context context, int id) {
        this.s11 = s11;
        this.context = context;
        this.id = id;
    }

    @Override
    public int getCount() {
        return s11.length;
    }

    @Override
    public Object getItem(int position) {
        return s11[position];
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
        ImageView imageView = convertView.findViewById(R.id.iii);

        Glide.with(convertView.getContext()).load(convertView.getContext().getResources().getString(R.string.ip1)+"/upload/"+
                s11[position]).into(imageView);


        return convertView;
    }
}
