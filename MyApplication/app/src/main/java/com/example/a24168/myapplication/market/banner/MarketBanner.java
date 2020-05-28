package com.example.a24168.myapplication.market.banner;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.a24168.myapplication.R;
import com.example.a24168.myapplication.kitchen.KitchenFragment;
import com.example.a24168.myapplication.kitchen.find.Find;
import com.example.a24168.myapplication.kitchen.like.Like;
import com.example.a24168.myapplication.kitchen.recommand.Recommand;
import com.example.a24168.myapplication.market.banner.cheap.Cheap;
import com.example.a24168.myapplication.market.banner.today.Today;
import com.example.a24168.myapplication.market.banner.tomorrow.Tomorrow;
import com.example.a24168.myapplication.market.shopping.Shopping;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MarketBanner  extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ImageView imageView3;
    private List<Fragment> list;
    private MyAdapter1 myAdapter1;
    private String[] titles = {" 4月29日 10：00 开售 ", " 4月30日 10：00 开售 ", "好货尝鲜"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.market_banner_xiangqing);
        ImageView imageView = findViewById(R.id.fanhui2);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.anim_int_right1,R.anim.anim_out_left1);
            }
        });
        imageView3 = findViewById(R.id.img44);
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), Shopping.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_in_right,R.anim.anim_out_left);
            }
        });


        viewPager = (ViewPager) findViewById(R.id.viewpager1);
        tabLayout = (TabLayout) findViewById(R.id.tablayout1);
        //页面，数据源
        list = new ArrayList<>();
        list.add(new Today());
        list.add(new Tomorrow());
        list.add(new Cheap());
        //ViewPager的适配器
        myAdapter1 = new MyAdapter1(getSupportFragmentManager());
        viewPager.setAdapter(myAdapter1);
        viewPager.setOffscreenPageLimit(3);
        //绑定
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    class MyAdapter1 extends FragmentPagerAdapter {
        public MyAdapter1(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        //重写这个方法，将设置每个Tab的标题
        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }

}
