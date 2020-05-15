package com.example.a24168.myapplication.market;


import android.content.Intent;
import android.os.Bundle;

import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a24168.myapplication.R;
import com.example.a24168.myapplication.market.adapter.MyAdapter;
import com.example.a24168.myapplication.market.adapter.XiangqingAdapter;
import com.example.a24168.myapplication.market.comment.Comment_s;
import com.example.a24168.myapplication.market.entity.Banner;
import com.example.a24168.myapplication.market.entity.Good;
import com.example.a24168.myapplication.market.entity.MarketComment;
import com.example.a24168.myapplication.market.entity.MarketLike;
import com.example.a24168.myapplication.market.entity.MarketNew;
import com.example.a24168.myapplication.market.entity.Type;
import com.example.a24168.myapplication.market.xiangqing.XiangQing;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.a24168.myapplication.main.MainActivity.findeditText;


public class MarketFragment extends Fragment {

    private View view;
    RecyclerView recyclerView;
    public static FragmentActivity fragment;
    private Handler handler;

    List<List> lists = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.market ,container, false);
        findeditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), Comment_s.class);
                startActivity(intent);
               getActivity().overridePendingTransition(R.anim.anim_in_right,R.anim.anim_out_left);
            }
        });
         fragment = getActivity();
        //获取控件得id
        get();

        //设置recycleview 样式
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        //获取分类数据
        lists.add(0,getTypeDate());
        //获取中间图数据
        lists.add(1,getBannerDate());

        lists.add(2,getBannerDate());

        lists.add(3,getBannerDate());

        //获取 猜你喜欢的 数据
       getGoodsDate();
       getCommentDate();
       getNewDate();
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case 0:

                    case 1:

                    case 2:{
                        String s = (String) msg.obj;
                        Gson gson = new Gson();
                        List<MarketNew> list = gson.fromJson(s, new TypeToken<List<MarketNew>>() {
                        }.getType());
                        lists.add(2,list);
                        lists.remove(3);

                        recyclerView.setAdapter(new MyAdapter(lists));
                        break;
                    }

                    case 3:{
                        String s = (String) msg.obj;
                        Log.e("gson",s);
                        Gson gson = new Gson();
                        List<MarketNew> list = gson.fromJson(s, new TypeToken<List<MarketComment>>() {
                        }.getType());
                        lists.add(3,list);
                        lists.remove(4);
                        recyclerView.setAdapter(new MyAdapter(lists));
                        break;
                    }

                    case 4:

                    case 5: {
                        String s = (String) msg.obj;
                        Gson gson = new Gson();
                        List<MarketLike> list = gson.fromJson(s, new TypeToken<List<MarketLike>>() {
                        }.getType());
                        lists.add(4,list);
                        recyclerView.setAdapter(new MyAdapter(lists));
                        break;

                    }

                }

            }
        };



        return view;
    }


    public void get(){
        recyclerView = view.findViewById(R.id.recy);
    }



    public List<Type> getTypeDate(){
        List<Type> types = new ArrayList<>();
        types.add(new Type(0,"烘培",R.drawable.hongpei));
        types.add(new Type(1,"果蔬生鲜",R.drawable.shucai));
        types.add(new Type(2,"器具",R.drawable.qiju));
        types.add(new Type(3,"领券",R.drawable.quan));
        types.add(new Type(4,"方便食品",R.drawable.fangbianshipin));
        types.add(new Type(5,"饮品茶酒",R.drawable.yinpin));
        types.add(new Type(6,"零食",R.drawable.lingshi));
        types.add(new Type(7,"全部分类",R.drawable.fenlei));
        return types;
    }

    public List<Banner>getBannerDate(){
        List<Banner> banners = new ArrayList<>();
        banners.add(new Banner(1,R.drawable.aichangxian));
        return banners;
    }
    public void getGoodsDate(){
        OkHttpClient okHttpClient = new OkHttpClient();
        FormBody.Builder builder = new FormBody.Builder();
        FormBody body = builder.build();
        Request request = new Request.Builder().post(body)
                .url(getResources().getString(R.string.ip1)+"/type/lei").build();
        final Call call = okHttpClient.newCall(request);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response response = call.execute();

                    // 交给handler处理主线程

                    Message message = Message.obtain();
                    message.obj = response.body().string();
                    message.what=2;
                    handler.sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void getNewDate(){
        OkHttpClient okHttpClient = new OkHttpClient();
        FormBody.Builder builder = new FormBody.Builder();
        FormBody body = builder.build();
        Request request = new Request.Builder().post(body)
                .url(getResources().getString(R.string.ip1)+"/type/lei").build();
        final Call call = okHttpClient.newCall(request);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response response = call.execute();

                    // 交给handler处理主线程

                    Message message = Message.obtain();
                    message.obj = response.body().string();
                    message.what=5;
                    handler.sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    };

    public void getCommentDate(){
        OkHttpClient okHttpClient = new OkHttpClient();
        FormBody.Builder builder = new FormBody.Builder();
        FormBody body = builder.build();
        Request request = new Request.Builder().post(body)
                .url(getResources().getString(R.string.ip1)+"/marketcomment/123").build();
        final Call call = okHttpClient.newCall(request);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response response = call.execute();

                    // 交给handler处理主线程

                    Message message = Message.obtain();
                    message.obj = response.body().string();
                    message.what=3;
                    handler.sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
