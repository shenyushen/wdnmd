package com.example.a24168.myapplication.kitchen.like.unimportant;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.a24168.myapplication.R;
import com.example.a24168.myapplication.kitchen.like.adapter.ListViewAdapter;
import com.example.a24168.myapplication.kitchen.like.custom.GlideImageLoader;
import com.example.a24168.myapplication.kitchen.like.entity.FindFriend;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.example.a24168.myapplication.kitchen.like.Like.w;
import static com.example.a24168.myapplication.kitchen.like.Like.gridList;
import static com.example.a24168.myapplication.kitchen.like.Like.gridViewAdapter;
import static com.example.a24168.myapplication.sign.Sign.user_id;

public class ShowDetails extends AppCompatActivity {
    private TextView textView;
    private TextView textViewdata;
    private TextView textViewmenu;
    private TextView textViewdate;
    private TextView textViewnicheng;
    private Bundle bundle;
    private List<String> images;
    private Banner banner;
    private ImageView imagetouxiang;
    private ListView listView;
    private EditText editText;
    private Button button;
    private FindFriend findFriend;
    private TextView pinglunrenshu;
    private ImageView guanzhu;


    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_showdetails);
        textView = findViewById(R.id.textdetail);
        textViewdata = findViewById(R.id.textdata);
        textViewmenu = findViewById(R.id.textmenu);
        textViewdate = findViewById(R.id.textdate);
        textViewnicheng = findViewById(R.id.nicheng);
        imagetouxiang = findViewById(R.id.touxiang);
        banner = findViewById(R.id.bannershow);
        listView = findViewById(R.id.findlistview);
        editText = findViewById(R.id.edit);
        button = findViewById(R.id.fasong);
        pinglunrenshu = findViewById(R.id.pinglunrenshu);
        guanzhu = findViewById(R.id.guanzhu);


        if(getIntent().getExtras()!=null) {
            bundle = getIntent().getExtras();
            findFriend = (FindFriend) bundle.getSerializable("findFriend");
            //Log.e("get",findFriend.getPhoto());
            mess();

            if(findFriend.getFindComments()!=null) {
                ListViewAdapter listViewAdapter = new ListViewAdapter(ShowDetails.this, findFriend.getFindComments());
                listView.setAdapter(listViewAdapter);
                setListViewHeightBasedOnChildren(listView);
            }
            images = new ArrayList<>();
            for(int i = 0;i<findFriend.getFind_Photos().size();i++) {
                images.add("http://10.0.2.2:8080/shixun3/pic/" + findFriend.getFind_Photos().get(i).getPhoto());
                Log.e("images",images.get(i));
            }

            panduanshifouguanzhu();

            banner.setBannerStyle(BannerConfig.NUM_INDICATOR);
            banner.setBannerAnimation(Transformer.Default);
                //设置标题集合（当banner样式有显示title时）
                //banner.setBannerTitles(titles);
                //设置图片加载器
            banner.setImageLoader(new GlideImageLoader());
                //设置图片集合
            banner.setImages(images);
                //设置轮播时间
            banner.setDelayTime(5000);
            banner.start();
            textViewnicheng.setText(findFriend.getUser().getUsername());
            textView.setText(findFriend.getTheme());
            Glide.with(this).load("http://10.0.2.2:8080/shixun3/pic/"+findFriend.getUser().getPhoto())
                    .apply( new RequestOptions().error(new ColorDrawable(Color.BLUE))).into(imagetouxiang);
            textViewdate.setText("#"+findFriend.getFindLable().getLable()+"."+findFriend.getDate()+"#");
            textViewdata.setText(findFriend.getData());
            textViewmenu.setText(findFriend.getMenuid()+"这是menu id");

            //提交评论
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String edit = editText.getText().toString().trim();
                    if(edit.equals("")){
                        Toast toast = Toast.makeText(ShowDetails.this,"请输入内容",Toast.LENGTH_SHORT);
                        toast.show();
                    }else {
                        new Thread(){
                            public void run(){
                                OkHttpClient client = new OkHttpClient.Builder()
                                        .writeTimeout(30, TimeUnit.SECONDS)
                                        .build();
                                MultipartBody.Builder requestBodyBuilder = new MultipartBody.Builder()
                                        .setType(MultipartBody.FORM);

                                requestBodyBuilder.addFormDataPart("authorid", user_id+"");
                                requestBodyBuilder.addFormDataPart("comment",edit);
                                requestBodyBuilder.addFormDataPart("findfriendid",findFriend.getId()+"");
                                RequestBody requestBody = requestBodyBuilder.build();
                                Request request = new Request.Builder()
                                        .url( "http://10.0.2.2:8080/shixun3/find/comment")
                                        .post(requestBody)
                                        .build();
                                try {
                                    Response response = client.newCall(request).execute();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }.start();
                    }
                }
            });
        }

        guanzhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (guanzhu.getDrawable().getCurrent().getConstantState().equals(ShowDetails.this.getResources().getDrawable(R.drawable.guanzhu2).getConstantState())){
                    guanzhu.setImageResource(R.drawable.guanzhu1);
                    guanzhule(findFriend.getId());
                    //gridList.add(findFriend);
                    gridViewAdapter.notifyDataSetChanged();
                }else{
                    guanzhu.setImageResource(R.drawable.guanzhu2);
                    quxiaoguanzhu(findFriend.getId());

                    gridList.remove(w);
                    gridViewAdapter.notifyDataSetChanged();
                }
            }
        });


    }

    private void quxiaoguanzhu(int position){
        new Thread(){
            @Override
            public void run() {
                URL url = null;
                try {
                    url = new URL("http://10.0.2.2:8080/shixun3/find/quxiaoguanzhu?userid="+user_id+"&findfriendid="+position);
                    URLConnection conn = url.openConnection();
                    InputStream in = conn.getInputStream();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }.start();
    }

    private void panduanshifouguanzhu(){

        new Thread(){
            @Override
            public void run() {
                URL url = null;
                try {
                    url = new URL("http://10.0.2.2:8080/shixun3/find/panduanshifouguanzhu?userid="+user_id+"&findfriendid="+findFriend.getId());
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//设置请求头
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(5000);
                    conn.setReadTimeout(5000);
                    int panduan;
                    int code = conn.getResponseCode();
                    if (code == 200) {
                        InputStream in = conn.getInputStream();
                        String text = streamToText(in);
                        panduan = Integer.parseInt(text);
                        if(panduan == 0){
                            guanzhu.setImageResource(R.drawable.guanzhu2);
                        }
                        else{

                            guanzhu.setImageResource(R.drawable.guanzhu1);
                        }
                    } else {
                        panduan = 0;
                    }
                    Log.e("panduan",panduan+"");

                } catch (MalformedURLException | ProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }.start();

    }
    private void guanzhule(int position){
        new Thread(){
            @Override
            public void run() {
                URL url = null;
                try {
                    url = new URL("http://10.0.2.2:8080/shixun3/find/guanzhu?userid="+user_id+"&findfriendid="+position);
                    URLConnection conn = url.openConnection();
                    InputStream in = conn.getInputStream();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }.start();

    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        if(listView == null) return;
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    private void mess(){
        new Thread(){
            @Override
            public void run() {
                URL url = null;
                try {
                    url = new URL("http://10.0.2.2:8080/shixun3/find/count?id="+findFriend.getId());
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//设置请求头
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(5000);
                    conn.setReadTimeout(5000);
//获得状态码
                    int code = conn.getResponseCode();
                    if (code == 200) {
                        InputStream in = conn.getInputStream();
                        String text = streamToText(in);
                        count = Integer.parseInt(text);
                    } else {
                        count = 0;
                    }
                    pinglunrenshu.setText(count+"");
                } catch (MalformedURLException | ProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }.start();
    }

    public static String streamToText(InputStream in) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int len = 0;
        byte[] buf = new byte[1024];
        try {
            while ((len = in.read(buf)) != -1) {
                baos.write(buf, 0, len);
            }
            byte[] byteArray = baos.toByteArray();
            return new String(byteArray);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
