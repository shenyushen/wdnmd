package com.example.a24168.myapplication.market.sort;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a24168.myapplication.R;
import com.example.a24168.myapplication.market.adapter.XiangqingAdapter;
import com.example.a24168.myapplication.market.entity.Good;
import com.example.a24168.myapplication.market.shopping.Shopping;
import com.example.a24168.myapplication.market.xiangqing.XiangQing;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Goods extends AppCompatActivity {
    private Handler handler;
    private TextView textView;
    private GridView gridView;
    private ImageView imageView;
    private ImageView imageView1;
    private Context context = this;
    public static String s_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.market_sort_xiangqing);
        handler = new Handler(){
            @SuppressLint("HandlerLeak")
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                String s = (String) msg.obj;
                Gson gson = new Gson();
                List<Good> list = gson.fromJson(s,new TypeToken<List<Good>>(){}.getType());
                XiangqingAdapter xiangqingAdapter = new XiangqingAdapter(list,context,R.layout.market_sort_xiangqing_item);
                gridView.setAdapter(xiangqingAdapter);

                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        s_id = list.get(position).getGoodsId()+"";
                        Intent  intent = new Intent();
                        intent.setClass(Goods.this, XiangQing.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.anim_in_right,R.anim.anim_out_left);
                    }
                });
            }
        };

       //获取控件id
        get();
        // 处理头部
       int id = handleTile();
       //获取数据库数据
        getDate(id);
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(context, Shopping.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_in_right,R.anim.anim_out_left);
            }
        });
    }

    public void getDate(int id){
        OkHttpClient okHttpClient = new OkHttpClient();
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("id",id+"");
        FormBody body = builder.build();
        Request request = new Request.Builder().post(body)
                .url(getResources().getString(R.string.ip1)+"/type/lei1").build();
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


    public void get(){
        textView = findViewById(R.id.title);
        gridView = findViewById(R.id.rec2);
        imageView = findViewById(R.id.fanhui1);
        imageView1 = findViewById(R.id.img3);
    }
    public int handleTile(){

        Intent intent = getIntent();
        imageView1.setImageResource(R.drawable.court);
        textView.setText(intent.getStringExtra("name"));
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.anim_int_right1,R.anim.anim_out_left1);
            }
        });

        //Log.e("123",intent.getStringExtra("id"));
        return Integer.valueOf(intent.getStringExtra("id"));

    }
}
