package com.example.a24168.myapplication.market.banner.today;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.a24168.myapplication.R;
import com.example.a24168.myapplication.market.adapter.XiangqingAdapter;
import com.example.a24168.myapplication.market.entity.Good;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Today extends Fragment {
    View view;
    GridView gridView;
    private Handler handler;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (view == null) {
            view = inflater.inflate(R.layout.today, container, false);
        }
    gridView = view.findViewById(R.id.grid6);

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                String s = (String) msg.obj;
                Gson gson = new Gson();
                List<Good> list = gson.fromJson(s,new TypeToken<List<Good>>(){}.getType());
                XiangqingAdapter xiangqingAdapter = new XiangqingAdapter(list,getContext(),R.layout.market_sort_xiangqing_item);
                gridView.setAdapter(xiangqingAdapter);
            }
        };

        getGoodsDate();


        return view;
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
                    handler.sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
