package com.example.a24168.myapplication.kitchen.find.adapter;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a24168.myapplication.R;
import com.example.a24168.myapplication.kitchen.find.unimportant.LableDetails;
import com.example.a24168.myapplication.kitchen.find.unimportant.ShowDetails;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    private List<String> mList;
    private Context context;
    public URL url;
    private Handler handler = new Handler();
    //内部类，继承自RecyclerView.ViewHolder
    //接收一个View ，通常就是RecyclerView子项的最外层布局，
    //所以就可以用findViewById 来获取控件
    static class  ViewHolder extends RecyclerView.ViewHolder{
        TextView TextName;
        public ViewHolder(View View) {
            super(View);
            TextName = (TextView) View.findViewById(R.id.Tv);
        }
    }
    //将要展示的数据传递进来，
    public RecyclerViewAdapter(List<String> list,Context context){
        this.mList = list ;
        this.context = context ;
    }

    public RecyclerViewAdapter() {
    }
    //将fruit_item 布局加载进来，然后创建一个ViewHolder实例，
    //将加载的布局传给ViewHolder的构造函数中。就可以获取布局中的控件
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.find_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    //这个方法会在屏幕滚动的时候执行
    // 通过position获取到Fruit 的实例，然后给布局上的控件进行赋值，
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        String tag = mList.get(position);
        holder.TextName.setText(tag);
        holder.TextName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread() {

                    public void run() {
                        try {
                            url = new URL("http://10.0.2.2:8080/shixun3/find/select?action="+mList.get(position) );
                            URLConnection conn = url.openConnection();
                            InputStream in = conn.getInputStream();

                            Intent intent = new Intent(context, LableDetails.class);
                            intent.putExtra("url",url+"");
                            context.startActivity(intent);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        });



    }
    //他用于告诉RecyclerView有多少子项，直接返回数据源的长度就行了
    @Override
    public int getItemCount() {
        return mList.size();
    }
}


