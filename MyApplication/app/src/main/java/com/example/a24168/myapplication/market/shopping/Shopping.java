package com.example.a24168.myapplication.market.shopping;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.a24168.myapplication.R;
import com.example.a24168.myapplication.main.MainActivity;
import com.example.a24168.myapplication.market.entity.Good;
import com.example.a24168.myapplication.market.shopping.entity.Order;
import com.example.a24168.myapplication.market.shopping.adpter.ShoppingAdapter;
import com.example.a24168.myapplication.market.xiangqing.entity.Comments;
import com.example.a24168.myapplication.sign.Sign;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import static com.example.a24168.myapplication.market.sort.Goods.s_id;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.a24168.myapplication.sign.Sign.user_id;

public class Shopping extends AppCompatActivity implements ShoppingAdapter.AOnClickListener {
    private List<Order> list = new ArrayList<>();
    private ListView listView ;
    private Handler handler;
    private Context context = this;
    private TextView bianji;
    private TextView wancheng ;
    private TextView settlement;
    private TextView del;
    private CheckBox AllcheckBox;
    private CheckBox ck_chose;
    private ShoppingAdapter Adapter;
    private ShoppingAdapter.AOnClickListener sw;
    private TextView allprice;
    private TextView delAll;
    private Button btn_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        handler = new Handler(){

            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case 1:{
                        String s = (String) msg.obj;
                        Log.e("lzy",s);
                        Gson gson = new Gson();
                        list = gson.fromJson(s,new TypeToken<List<Order>>(){}.getType());
                        for(int i = 0; i < list.size(); i++) {
                            Log.e("lzy1", list.get(i).getGoods().getImg());
                            list.get(i).setChecked(true);
                        }
                        getprice();

                        Adapter = new ShoppingAdapter(list, context,R.layout.market_shoppingitem);
                        listView.setAdapter(Adapter);

                        break;
                    }
                    case 2: {

                        break;
                    }

                }


            }
        };

        setContentView(R.layout.market_shopping);
        listView =  findViewById(R.id.market_shopping_listview);
        //list.add(new Order("苹果",1,12,12,20,"mianbao.png"));
        getDate();
        fangfa();

    }
    public void fangfa(){
        //隐藏和展示
        bianji = findViewById(R.id.bt_header_right);
        wancheng = findViewById(R.id.bt_header_right2);
        settlement = findViewById(R.id.tv_settlement);
        del = findViewById(R.id.tv_del);
        bianji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bianji.setVisibility(View.GONE);
                wancheng.setVisibility(View.VISIBLE);
                settlement.setVisibility(View.GONE);
                del.setVisibility(View.VISIBLE);
            }
        });
        wancheng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bianji.setVisibility(View.VISIBLE);
                wancheng.setVisibility(View.GONE);
                settlement.setVisibility(View.VISIBLE);
                del.setVisibility(View.GONE);
            }
        });

        delAll = findViewById(R.id.tv_del);
        delAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OkHttpClient okHttpClient = new OkHttpClient();
                FormBody.Builder builder = new FormBody.Builder();
                builder.add("user_id",""+user_id);
                FormBody body = builder.build();

                Request request = new Request.Builder().post(body)
                        .url(getResources().getString(R.string.ip1)+"/marketcomment/1234567").build();
                final Call call = okHttpClient.newCall(request);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Response response = call.execute();
                            Message message = Message.obtain();
                            message.obj = response.body().string();
                            message.what = 2;
                            handler.sendMessage(message);
                            getDate();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

            }
        });


        btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Shopping.this, MainActivity.class);
                //启动
                startActivity(intent);
            }
        });
    }
    //计算价格
    public void getprice(){
        allprice = findViewById(R.id.tv_show_price);
        AllcheckBox=findViewById(R.id.ck_all);
        float num1=0;
        for(int i =0; i<list.size();i++){
            float a = Float.parseFloat(list.get(i).getGoodsPrice().substring(1));
            num1 += a * Integer.parseInt(list.get(i).getGoodsCount());
        }
        allprice.setText("合计："+num1);
        AllcheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                     float num=0;
                    for(int i =0; i<list.size();i++){
                            float a = Float.parseFloat(list.get(i).getGoodsPrice().substring(1));
                            num += a * Integer.parseInt(list.get(i).getGoodsCount());
                            list.get(i).setChecked(true);

                    }
                    Adapter.notifyDataSetChanged();
                    allprice.setText("合计："+num);
                    //ck_chose.setChecked(true);
                }
                else{
                    allprice.setText("合计："+0);
                    //ck_chose.setChecked(false);
                    for(int i =0; i<list.size();i++){
                        list.get(i).setChecked(false);
                    }
                    Adapter.notifyDataSetChanged();
                }
            }
        });



    }

    public void getDate(){

        OkHttpClient okHttpClient = new OkHttpClient();
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("id",""+user_id);
        FormBody body = builder.build();

        Request request = new Request.Builder().post(body)
                .url(getResources().getString(R.string.ip1)+"/marketcomment/1234").build();
        final Call call = okHttpClient.newCall(request);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response response = call.execute();
                    Message message = Message.obtain();
                    message.obj = response.body().string();
                    message.what = 1;
                    handler.sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }



    @Override
    public void jianNum(float num) {
        allprice = findViewById(R.id.tv_show_price);
        Log.e("lzys",allprice.getText().toString());
        float a = Float.parseFloat(allprice.getText().toString().substring(3))-num;
        allprice.setText("合计："+a);
    }

    @Override
    public void jiaNum(float num) {
        allprice = findViewById(R.id.tv_show_price);
        Log.e("lzys",allprice.getText().toString());
        float a = Float.parseFloat(allprice.getText().toString().substring(3))+num;
        allprice.setText("合计："+a);
    }

    @Override
    public void chang(String count,String goods_id) {
        OkHttpClient okHttpClient = new OkHttpClient();
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("user_id",""+user_id);
        builder.add("goods_id",""+goods_id);
        int countnow=Integer.parseInt(count)+1;
        builder.add("goods_count",""+countnow);
        FormBody body = builder.build();

        Request request = new Request.Builder().post(body)
                .url(getResources().getString(R.string.ip1)+"/marketcomment/12345").build();
        final Call call = okHttpClient.newCall(request);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response response = call.execute();
                    Message message = Message.obtain();
                    message.obj = response.body().string();
                    message.what = 2;
                    handler.sendMessage(message);
                    getDate();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }

    @Override
    public void chang2(String count,String goods_id) {
        OkHttpClient okHttpClient = new OkHttpClient();
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("user_id",""+user_id);
        builder.add("goods_id",""+goods_id);
        int countnow=Integer.parseInt(count)-1;
        builder.add("goods_count",""+countnow);
        FormBody body = builder.build();

        Request request = new Request.Builder().post(body)
                .url(getResources().getString(R.string.ip1)+"/marketcomment/12345").build();
        final Call call = okHttpClient.newCall(request);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response response = call.execute();
                    Message message = Message.obtain();
                    message.obj = response.body().string();
                    message.what = 2;
                    handler.sendMessage(message);
                    getDate();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }

    @Override
    public void del(String goods_id) {
        OkHttpClient okHttpClient = new OkHttpClient();
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("user_id",""+user_id);
        builder.add("goods_id",""+goods_id);
        FormBody body = builder.build();
        Request request = new Request.Builder().post(body)
                .url(getResources().getString(R.string.ip1)+"/marketcomment/123456").build();
        final Call call = okHttpClient.newCall(request);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response response = call.execute();
                    Message message = Message.obtain();
                    message.obj = response.body().string();
                    message.what = 2;
                    handler.sendMessage(message);
                    getDate();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
