package com.example.a24168.myapplication.main;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.a24168.myapplication.R;
import com.example.a24168.myapplication.course.CourseFragment;
import com.example.a24168.myapplication.kitchen.KitchenFragment;
import com.example.a24168.myapplication.market.MarketFragment;
import com.example.a24168.myapplication.setting.Personal;
import com.example.a24168.myapplication.setting.UserSetting;
import com.example.a24168.myapplication.sign.User_s;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static com.example.a24168.myapplication.sign.Sign.ww;
public  class MainActivity extends AppCompatActivity {

    public static String urll;
    public static ImageView imageView;
    public static String we;
    String ur="http://10.0.2.2:8080/shixun3/pic/";
    public static LinearLayout linearLayout;
    public static ImageView imageView1;
    private Map<String, ImageView> imageViewMap = new HashMap<>();
    private Map<String, TextView> textViewMap = new HashMap<>();
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    ImageView menu;
    public static  EditText findeditText;
    public Handler handler;
    private Context context;
    View headerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ur=getResources().getString(R.string.ip1)+"/pic/";
        findeditText=findViewById(R.id.findfood);
        linearLayout = findViewById(R.id.ll);
        imageView1 = findViewById(R.id.k);
        handler = new Handler(){
            @SuppressLint("HandlerLeak")
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                User_s user_s1 = (User_s) msg.obj;
                TextView res = headerView.findViewById(R.id.head_name);

                res.setText(user_s1.getUsername());
                TextView txt_sex=headerView.findViewById(R.id.txt_sex);
                if(user_s1.getSex()==null){
                    txt_sex.setText("性别：未填");
                }else{
                    txt_sex.setText("性别："+user_s1.getSex());
                }
                //txt_sex.setText("性别："+user_s1.getSex());
                TextView txt_bitrhday=headerView.findViewById(R.id.txt_birthday);
                if(user_s1.getBirthday()==null){
                    txt_bitrhday.setText("生日：未填");
                }else{
                    txt_bitrhday.setText("生日："+user_s1.getBirthday());
                }

                TextView txt_profession=headerView.findViewById(R.id.txt_profession);
                if (user_s1.getProfession()==null){
                    txt_profession.setText("职业：未填");
                }else {
                    txt_profession.setText("职业："+user_s1.getProfession());
                }

                TextView txt_tag=headerView.findViewById(R.id.txt_tag);
                if (user_s1.getLabel()==null){
                    txt_tag.setText("个性签名：未填");
                }else {
                    txt_tag.setText("个性签名："+user_s1.getLabel());
                }

                TextView txt_home=headerView.findViewById(R.id.txt_home);
                if (user_s1.getHome()==null){
                    txt_home.setText("家乡：未填");
                }else {
                    txt_home.setText("家乡："+user_s1.getHome());
                }

                imageView=headerView.findViewById(R.id.person);
                Glide.with(context).load(ur + user_s1.getPhoto()).into(imageView);

            }
        };
        //获取FragmentTabHost对象
        FragmentTabHost fragmentTabHost = findViewById(android.R.id.tabhost);
        context = this;
        //初始化FragmentTabHost
        fragmentTabHost.setup(this,
                getSupportFragmentManager(),//FragmentManager对象用来管理多个Fragment
                android.R.id.tabcontent);//真正显示内容页面的容器的id

        //创建tabspec对象
        TabHost.TabSpec tabSpec1 = fragmentTabHost.newTabSpec("tag1")
                //                .setIndicator("消息");
                .setIndicator(getTabSpecView("tag1",R.drawable.kitchen,"下厨房"));
        //自定义选项卡视图

        fragmentTabHost.addTab(tabSpec1,
                KitchenFragment.class,//内容页面对应的Fragment类的Class对象
                null);//传递数据时使用

        TabHost.TabSpec tabSpec2 = fragmentTabHost.newTabSpec("tag2")
                //                .setIndicator("好友");
                .setIndicator(getTabSpecView("tag2",R.drawable.chaoshi1,"市集"));

        fragmentTabHost.addTab(tabSpec2,
                MarketFragment.class,
                null);

        TabHost.TabSpec tabSpec3 = fragmentTabHost.newTabSpec("tag3")
                //                .setIndicator("设置");
                .setIndicator(getTabSpecView("tag3",R.drawable.course,"课堂"));

        fragmentTabHost.addTab(tabSpec3,
                CourseFragment.class,
                null);

        //设置默认选中哪一项
        fragmentTabHost.setCurrentTab(0);
        imageViewMap.get("tag1").setImageResource(R.drawable.ktichen1);
        textViewMap.get("tag1").setTextColor(Color.parseColor("#FF6633"));
        //切换选项卡的事件监听器
        fragmentTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                //当切换选项卡时调用
                switch (tabId){
                    case "tag1":
                        imageViewMap.get("tag1").setImageResource(R.drawable.ktichen1);
                        textViewMap.get("tag1").setTextColor(Color.parseColor("#FF6633"));
                        imageViewMap.get("tag2").setImageResource(R.drawable.chaoshi1);
                        textViewMap.get("tag2").setTextColor(Color.parseColor("#515151"));
                        imageViewMap.get("tag3").setImageResource(R.drawable.course);
                        textViewMap.get("tag3").setTextColor(Color.parseColor("#515151"));
                        break;
                    case "tag2":
                        imageViewMap.get("tag1").setImageResource(R.drawable.kitchen);
                        textViewMap.get("tag1").setTextColor(Color.parseColor("#515151"));
                        imageViewMap.get("tag2").setImageResource(R.drawable.chaoshi);
                        textViewMap.get("tag2").setTextColor(Color.parseColor("#FF6633"));
                        imageViewMap.get("tag3").setImageResource(R.drawable.course);
                        textViewMap.get("tag3").setTextColor(Color.parseColor("#515151"));
                        break;
                    case "tag3":
                        imageViewMap.get("tag1").setImageResource(R.drawable.kitchen);
                        textViewMap.get("tag1").setTextColor(Color.parseColor("#515151"));
                        imageViewMap.get("tag2").setImageResource(R.drawable.chaoshi1);
                        textViewMap.get("tag2").setTextColor(Color.parseColor("#515151"));
                        imageViewMap.get("tag3").setImageResource(R.drawable.course1);
                        textViewMap.get("tag3").setTextColor(Color.parseColor("#FF6633"));
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
        headerView = navigationView.getHeaderView(0);

        new Thread(){
            @Override
            public void run(){
                try {


                    Intent intent3=getIntent();
                    String stringg=intent3.getStringExtra("stringg");

                    Gson gson = new Gson();
                    User_s user_s  = gson.fromJson(stringg,User_s.class);
                    we=ww;

                    URL url = new URL(getResources().getString(R.string.ipuser)+"/user/secondquery?id="+ww);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    //3，建管道--InputStream
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setConnectTimeout(8000);
                    httpURLConnection.setReadTimeout(8000);
                    InputStream in = httpURLConnection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                    String string=reader.readLine();
                    Log.e("asasas",string);


                    User_s user_s1=gson.fromJson(string,User_s.class);


                    urll=ur+user_s1.getPhoto();
                    Message message = Message.obtain();
                    message.obj=user_s1;
                    handler.sendMessage(message);

                }catch (MalformedURLException e){
                    e.printStackTrace();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }.start();
     //   Log.e("kaishi","kaishi");
      //  Glide.with(this).load(urll).into(imageView);
       // Log.e("lujing",urll);
       // Log.e("jieshu","jieshu");
        menu.setOnClickListener(view -> {
            //点击菜单，跳出侧滑菜单

            if (drawerLayout.isDrawerOpen(navigationView)){
                drawerLayout.closeDrawer(navigationView);
            }else{
                drawerLayout.openDrawer(navigationView);
            }
        });

        navigationView.setNavigationItemSelectedListener(item -> {

            switch (item.getItemId()){
                case R.id.favorite:
                    Intent intent = new Intent(MainActivity.this, Personal.class);

                    startActivity(intent);
                    overridePendingTransition(R.anim.anim_in_right,R.anim.anim_out_left);
                    break;
                case R.id.wallet:
                    Intent intent2=new Intent(MainActivity.this, UserSetting.class);
                    startActivity(intent2);
                    overridePendingTransition(R.anim.anim_in_right,R.anim.anim_out_left);
                    break;
                case R.id.dress:

                    break;
                default:
                    break;
            }
            return true;

        });

    }
}