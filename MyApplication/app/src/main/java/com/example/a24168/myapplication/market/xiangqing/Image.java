package com.example.a24168.myapplication.market.xiangqing;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.a24168.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import static com.example.a24168.myapplication.market.CommentXiangqing.cur;
import static com.example.a24168.myapplication.market.CommentXiangqing.strings;


public class Image extends AppCompatActivity {
    //定义图片数组
    private int images[];
    //定义一个View的数组
    private List<View> views=new ArrayList<>();
    private ViewPager vp_main_viewpager;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comment_image);

        vp_main_viewpager = (ViewPager) findViewById(R.id.view);

        //将images数组中的图片放入ImageView
        for(int i = 0; i < strings.length;i++) {
            ImageView imageView=new ImageView(this);
            Glide.with(this).load(getResources().getString(R.string.ip1)+"/upload/"+
                    strings[i]).into(imageView);
            views.add(imageView);
        }

        //为ViewPager设置适配器
        vp_main_viewpager.setAdapter(new MyAdapter());
        vp_main_viewpager.setCurrentItem(cur);
        imageView = findViewById(R.id.fanhui_c);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.anim_int_right1,R.anim.anim_out_left1);
            }
        });
    }

    class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return views.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View v=views.get(position);
            container.addView(v);

            return v;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View v=views.get(position);
            //前一张图片划过后删除该View
            container.removeView(v);
        }


    }
}
