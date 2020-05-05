package com.example.a24168.myapplication.main;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.StaticLayout;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a24168.myapplication.R;
import com.example.a24168.myapplication.kitchen.KitchenFragment;
import com.example.a24168.myapplication.market.MarketFragment;
import com.example.a24168.myapplication.course.CourseFragment;

import java.util.HashMap;
import java.util.Map;

public  class MainActivity extends AppCompatActivity {
    private Map<String, ImageView> imageViewMap = new HashMap<>();
    private Map<String, TextView> textViewMap = new HashMap<>();
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    ImageView menu;
     public static  EditText findeditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findeditText=findViewById(R.id.findfood);
        //获取FragmentTabHost对象
        FragmentTabHost fragmentTabHost = findViewById(android.R.id.tabhost);

        //初始化FragmentTabHost
        fragmentTabHost.setup(this,
                getSupportFragmentManager(),//FragmentManager对象用来管理多个Fragment
                android.R.id.tabcontent);//真正显示内容页面的容器的id

        //创建tabspec对象
        TabHost.TabSpec tabSpec1 = fragmentTabHost.newTabSpec("tag1")
                //                .setIndicator("消息");
                .setIndicator(getTabSpecView("tag1",R.drawable.message,"下厨房"));
        //自定义选项卡视图

        fragmentTabHost.addTab(tabSpec1,
               KitchenFragment.class,//内容页面对应的Fragment类的Class对象
                null);//传递数据时使用

        TabHost.TabSpec tabSpec2 = fragmentTabHost.newTabSpec("tag2")
                //                .setIndicator("好友");
                .setIndicator(getTabSpecView("tag2",R.drawable.home,"市集"));

        fragmentTabHost.addTab(tabSpec2,
                MarketFragment.class,
                null);

        TabHost.TabSpec tabSpec3 = fragmentTabHost.newTabSpec("tag3")
                //                .setIndicator("设置");
                .setIndicator(getTabSpecView("tag3",R.drawable.contact,"课堂"));

        fragmentTabHost.addTab(tabSpec3,
                CourseFragment.class,
                null);

        //设置默认选中哪一项
        fragmentTabHost.setCurrentTab(1);
        imageViewMap.get("tag2").setImageResource(R.drawable.home1);
        textViewMap.get("tag2").setTextColor(Color.parseColor("#1296db"));
        //切换选项卡的事件监听器
        fragmentTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                //当切换选项卡时调用
                switch (tabId){
                    case "tag1":
                        imageViewMap.get("tag1").setImageResource(R.drawable.message1);
                        textViewMap.get("tag1").setTextColor(Color.parseColor("#1296db"));
                        imageViewMap.get("tag2").setImageResource(R.drawable.home);
                        textViewMap.get("tag2").setTextColor(Color.parseColor("#515151"));
                        imageViewMap.get("tag3").setImageResource(R.drawable.contact);
                        textViewMap.get("tag3").setTextColor(Color.parseColor("#515151"));
                        break;
                    case "tag2":
                        imageViewMap.get("tag1").setImageResource(R.drawable.message);
                        textViewMap.get("tag1").setTextColor(Color.parseColor("#515151"));
                        imageViewMap.get("tag2").setImageResource(R.drawable.home1);
                        textViewMap.get("tag2").setTextColor(Color.parseColor("#1296db"));
                        imageViewMap.get("tag3").setImageResource(R.drawable.contact);
                        textViewMap.get("tag3").setTextColor(Color.parseColor("#515151"));
                        break;
                    case "tag3":
                        imageViewMap.get("tag1").setImageResource(R.drawable.message);
                        textViewMap.get("tag1").setTextColor(Color.parseColor("#515151"));
                        imageViewMap.get("tag2").setImageResource(R.drawable.home);
                        textViewMap.get("tag2").setTextColor(Color.parseColor("#515151"));
                        imageViewMap.get("tag3").setImageResource(R.drawable.contact1);
                        textViewMap.get("tag3").setTextColor(Color.parseColor("#1296db"));
                        break;
                }
            }
        });
    }

    public View getTabSpecView(String tag, int imageResId, String title){
        //加载布局文件
        //        LayoutInflater layoutInflater = LayoutInflater.from(this);
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.tabspec_layout,null);

        //获取控件对象
        ImageView imageView = view.findViewById(R.id.iv_icon);
        imageView.setImageResource(imageResId);

        TextView textView = view.findViewById(R.id.tv_title);
        textView.setText(title);

        imageViewMap.put(tag,imageView);
        textViewMap.put(tag,textView);
        initView();
        return view;
    }
    private void initView(){
        drawerLayout = findViewById(R.id.activity_na);
        navigationView = findViewById(R.id.nav);
        menu = findViewById(R.id.main_menu);
        View headerView = navigationView.getHeaderView(0);//获取头布局
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //点击菜单，跳出侧滑菜单
                if (drawerLayout.isDrawerOpen(navigationView)){
                    drawerLayout.closeDrawer(navigationView);
                }else{
                    drawerLayout.openDrawer(navigationView);
                }
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Toast.makeText(MainActivity.this,item.getTitle().toString()+"55",Toast.LENGTH_SHORT).show();
//                drawerLayout.closeDrawer(navigationView);
                return true;
            }
        });
    }
}