package com.example.a24168.myapplication.kitchen.recommand;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.a24168.myapplication.R;
import com.example.a24168.myapplication.entity.menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import android.os.Handler;

import static com.example.a24168.myapplication.kitchen.recommand.Recommand.context;

//此文件作用为菜单详情页的启动页，其中包含
//1,从网站上获取步骤的数据，还有从其他页面用intent获取的menu数据并添加到了相应的位置
//2使用menudetailsAdapter构建，其中具有判断是否为空的
public class menudetails extends AppCompatActivity {
    private Handler handler =new Handler();
    private List<String[]> steps =new ArrayList<String[]>();
    private UnScrollListView unScrollListView;
    private MyScrollView myScrollView;
    public static LinearLayout linearLayout;
    private Intent menudetalisintent;
    private TextView textView;
    private TextView textView1;
    private ImageView imageView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menudetalis);
        textView=findViewById(R.id.menudetails);
        textView1=findViewById(R.id.menutext);
        linearLayout=findViewById(R.id.schildren);
        imageView=findViewById(R.id.menudetails_photo);
//        myScrollView=findViewById(R.id.MyScrollView);
//       自定义的listview实现上部空间同时滑动
        unScrollListView=findViewById(R.id.menudetailslistview);
        menudetalisintent=getIntent();
        String[] menu=menudetalisintent.getStringExtra("menu").trim().split(",");
        textView.setText(menu[3]);
        textView1.setText("  "+menu[4].trim());
        Glide.with(this)
                .load(context.getResources().getString(R.string.photoip)+menu[2])
                .into(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getBaseContext(),miaoshudatu.class);
                intent.putExtra("url",menu[2]);
                context.startActivity(intent);
            }
        });
        getsteps(Integer.valueOf(menu[0]));
        unScrollListView.setFocusable(false);
    }
    public void getsteps(int menuid){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //1,找水源--创建URL
                    steps.clear();
                    URL url = new URL(getResources().getString(R.string.ip)+"details?menuid="+menuid);//放网站
                    //2,开水闸--openConnection
                    Log.e("url", url.toString());
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    //3，建管道--InputStream
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setConnectTimeout(8000);
                    httpURLConnection.setReadTimeout(8000);
                    InputStream in = httpURLConnection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    String line;
                    String str="";
                    while ((line = reader.readLine()) != null) {
                        str+=line;
                    }
//                    获取的是HTML样式的，此步骤是进行更改
                    int s = str.indexOf("<body>");
                    int e = str.indexOf("</body>");
                    String ss = str.substring(s+"<body>".length(), e);
                    if(!ss.trim().equals("")){
                        String[] steps=ss.trim().split("<br/>");
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                menudetailsAdapter menudetailsAdapter=new menudetailsAdapter(getBaseContext(),steps);
                                unScrollListView.setAdapter(menudetailsAdapter);
                                Utility.setListViewHeightBasedOnChildren(unScrollListView);
                                for(String a:steps){
                                    Log.e("neibu",a);
                                }
                            }
                        });
                    }
                    else{
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getBaseContext(), "教程正在路上", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }


                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }).start();
        }
}
