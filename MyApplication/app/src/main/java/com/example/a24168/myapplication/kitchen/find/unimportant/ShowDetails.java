package com.example.a24168.myapplication.kitchen.find.unimportant;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.a24168.myapplication.R;
import com.example.a24168.myapplication.kitchen.find.adapter.ListViewAdapter;
import com.example.a24168.myapplication.kitchen.find.entity.FindFriend;
import com.example.a24168.myapplication.kitchen.find.custom.GlideImageLoader;
import com.example.a24168.myapplication.kitchen.find.entity.Show;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

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

        if(getIntent().getExtras()!=null) {
            bundle = getIntent().getExtras();
            FindFriend findFriend = (FindFriend) bundle.getSerializable("findFriend");
            //Log.e("get",findFriend.getPhoto());

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

        }

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
}
