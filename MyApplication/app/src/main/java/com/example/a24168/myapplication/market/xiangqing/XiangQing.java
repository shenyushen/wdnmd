package com.example.a24168.myapplication.market.xiangqing;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.a24168.myapplication.R;
import com.example.a24168.myapplication.market.adapter.XiangqingAdapter;
import com.example.a24168.myapplication.market.entity.Good;
import com.example.a24168.myapplication.market.entity.Type;
import com.example.a24168.myapplication.market.sort.Goods;
import com.example.a24168.myapplication.market.xiangqing.adpter.CommentsAdapter;
import com.example.a24168.myapplication.market.xiangqing.adpter.Type1Adapter;
import com.example.a24168.myapplication.market.xiangqing.entity.Comments;
import com.example.a24168.myapplication.market.xiangqing.entity.Type1;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.example.a24168.myapplication.market.sort.Goods.s_id;
import static com.example.a24168.myapplication.sign.Sign.user_id;

public class XiangQing extends AppCompatActivity implements OnBannerListener {
    public static TextView textView10;
    private ImageView imageView; //返回
    private ImageView imageView1; // 购物车
    private TextView textView; //评分
    private TextView textView2; //月销
    private TextView textView3; //总销
    private TextView textView4; //little
    private TextView textView5; //Big
    private TextView textView6;// 价格
    private TextView textView7; // 邮费
    private TextView textView8; //规格
    private TextView textView9; // 支持退换货
    private LinearLayout linearLayout; //选择规格
    private LinearLayout linearLayout2; //退换货点击
    private RecyclerView recyclerView;
    private Button button1;
    private Button button2;
    private List<Comments>commentsList;
    ImageView imageView3;
    private Banner banner;
    private Handler handler;
    private ArrayList<String> list_path;
    private ArrayList<String> list_title;
    private ArrayList<Good>list;
    private String[] strings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.market_goods_xiangqing);
        handler = new Handler(){
            @SuppressLint("HandlerLeak")
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case 1:{
                        String s = (String) msg.obj;
                        Log.e("text",s);
                        Gson gson = new Gson();
                        list = gson.fromJson(s,new TypeToken<List<Good>>(){}.getType());
                        for(int i = 0; i < list.size(); i++)
                            Log.e("text",list.get(i).toString());
                        //添加图片路径
                        String [] a = list.get(0).getGood().getGoods_img().split(",");
                        strings = list.get(0).getGood().getGoods_type().split(",");
                        for(int i = 0; i < a.length; i++){
                            list_path.add(getResources().getString(R.string.ip1)+"/upload/"+a[i]);
                        }
                        list_title.add(" ");list_title.add("  ");list_title.add("    ");list_title.add("     ");list_title.add("        ");
                        // 设置轮播图
                        initBanner();
                        //设置数据
                        initDate();
                        break;
                    }
                    case 2:{
                        imageView3.setVisibility(View.VISIBLE);
                        WindowManager wm = getWindowManager();
                        int width = wm.getDefaultDisplay().getWidth();
                        int height = wm.getDefaultDisplay().getHeight();
                        float x = imageView3.getRight();
                        float y = imageView3.getBottom();
                        Log.e("123",width+"  "+height+"  "+x+" "+y);
                        ObjectAnimator animator = ObjectAnimator.ofFloat(imageView3,"TranslationX",0,width);
                        ObjectAnimator animator1 = ObjectAnimator.ofFloat(imageView3,"TranslationY",0,height*-1);
                        AnimatorSet animSet = new AnimatorSet();
                        animSet.playTogether(animator, animator1);
                        animSet.setDuration(1000);
                        animSet.start();
                        break;
                    }
                    case 3:{
                        String s = (String) msg.obj;
                        Gson gson = new Gson();
                        commentsList = gson.fromJson(s,new TypeToken<List<Comments>>(){}.getType());
                        getComment();
                    }

                }

            }
        };
        //获取控件id
        get();
        Log.e("text",s_id);
        getDate(s_id);


        //获取评论
        getCommentDate();




        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.anim_int_right1,R.anim.anim_out_left1);
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog2(list);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog2(list);
            }
        });
    }

    public void get(){
        imageView = findViewById(R.id.fanhui10);
        imageView1 = findViewById(R.id.court_xiangqing);
        banner = findViewById(R.id.banner_xiangqing);
        textView = findViewById(R.id.score);
        textView2 = findViewById(R.id.month_count);
        textView3 = findViewById(R.id.all_count);
        textView4 = findViewById(R.id.little_content);
        textView5 = findViewById(R.id.big_content);
        textView6 = findViewById(R.id.x_price);
        textView7 = findViewById(R.id.shiping);
        textView8 = findViewById(R.id.x_type);
        textView9 = findViewById(R.id.return_f);
        textView10 = findViewById(R.id.sss);
        linearLayout = findViewById(R.id.liner);
        linearLayout2 = findViewById(R.id.liner1);
        recyclerView = findViewById(R.id.recyle);
        button1 = findViewById(R.id.add_court1);
        button2 = findViewById(R.id.buy1);
        list_path = new ArrayList<>();
        list_title = new ArrayList<>();
        commentsList = new ArrayList<>();
    }
    public void getCommentDate(){
        OkHttpClient okHttpClient = new OkHttpClient();
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("id",s_id);
        FormBody body = builder.build();

        Request request = new Request.Builder().post(body)
                .url(getResources().getString(R.string.ip1)+"/type/lei3").build();
        final Call call = okHttpClient.newCall(request);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response response = call.execute();

                    // 交给handler处理主线程
                    Message message = Message.obtain();
                    message.obj = response.body().string();
                    message.what = 3;
                    handler.sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
    public void getComment(){


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        recyclerView.setLayoutManager(linearLayoutManager);
        CommentsAdapter commentsAdapter = new CommentsAdapter(commentsList);
        recyclerView.setAdapter(commentsAdapter);
    }
    public void getDate(String id){
        Log.e("test",id);
        OkHttpClient okHttpClient = new OkHttpClient();
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("id",id+"");
        FormBody body = builder.build();

        Request request = new Request.Builder().post(body)
                .url(getResources().getString(R.string.ip1)+"/type/lei2").build();
        final Call call = okHttpClient.newCall(request);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response response = call.execute();

                    // 交给handler处理主线程
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
    public void initBanner(){
        //设置内置样式，共有六种可以点入方法内逐一体验使用。
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //设置图片加载器，图片加载器在下方
        banner.setImageLoader(new MyLoader());
        //设置图片网址或地址的集合
        banner.setImages(list_path);
        //设置轮播的动画效果，内含多种特效，可点入方法内查找后内逐一体验
        banner.setBannerAnimation(Transformer.Default);
        //设置轮播图的标题集合
        banner.setBannerTitles(list_title);
        //设置轮播间隔时间
        banner.setDelayTime(3000);
        //设置是否为自动轮播，默认是“是”。
        banner.isAutoPlay(true);
        //设置指示器的位置，小点点，左中右。
        banner.setIndicatorGravity(BannerConfig.CENTER)
                //以上内容都可写成链式布局，这是轮播图的监听。比较重要。方法在下面。
                .setOnBannerListener(this)
                //必须最后调用的方法，启动轮播图。
                .start();

    }
    public void initDate(){
        textView.setText(list.get(0).getGood().getGoods_score());

        Integer integer = Integer.valueOf(list.get(0).getSaleVolume());
        if(integer/30 > 1){
            textView2.setText(integer/30 + "件");
        }else{
            textView2.setText(1+"件");
        }

        textView3.setText(list.get(0).getSaleVolume()+"件");
        textView4.setText(list.get(0).getLittleContent());
        textView5.setText(list.get(0).getTitle());
        textView6.setText("￥ "+list.get(0).getPrice());

        if(list.get(0).getGood().getIf_freeshiiping().equals("yes")) textView7.setText(" 包邮");
        else textView7.setText("不包邮");

        if(strings.length == 1) textView8.setText(strings[0]);
        else {
            textView8.setText("请选择规格");
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showDialog2(list);
                }
            });
        }


        if (list.get(0).getGood().getReturn_goods().equals("yes")) textView9.setText("·七天无理由退换货");
        else textView9.setText("·不支持七天无理由退换货");
        linearLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog1(list.get(0).getGood().getReturn_goods());
            }
        });
    }
    public void showDialog2(List<Good>list){
        Dialog dialog = new Dialog(this,R.style.DialogTheme);
        View view = View.inflate(this,R.layout.market_goods_dialog1,null);
        dialog.setContentView(view);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.main_menu_animStyle);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, 1400);
        imageView3 = dialog.findViewById(R.id.roud);


        // 头部
        ImageView  imageView = dialog.findViewById(R.id.s_img);
        TextView  t = dialog.findViewById(R.id.s_content);
        TextView t1=dialog.findViewById(R.id.s_price);
        Glide.with(this).load(getResources().getString(R.string.ip1)+"/upload/"+list.get(0).getImg()).into(imageView);
        t.setText(list.get(0).getTitle());
        t1.setText("￥"+list.get(0).getPrice());

        //种类
        GridView gridView = dialog.findViewById(R.id.s_grid);
        String[] strings1 = list.get(0).getGood().getGoods_type().split(",");
        ArrayList<Type1> type1s = new ArrayList<>();
        for(int i = 0; i < strings1.length; i++){
            String[] s2 = strings1[i].split(";");
            Type1 type1 = new Type1(s2[0],s2[1]);
            type1s.add(type1);
        }
        Type1Adapter type1Adapter = new Type1Adapter(type1s,this,R.layout.market_goods_dialog1_item);
        gridView.setAdapter(type1Adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               t1.setText("￥"+type1s.get(position).getPrice());
               textView8.setText(type1s.get(position).getType());

            }
        });

        //数量
        EditText text = dialog.findViewById(R.id.text);
        Button del = dialog.findViewById(R.id.del);
        Button add = dialog.findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setText(Integer.valueOf(text.getText().toString())+1+"");
            }
        });
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setText(Integer.valueOf(text.getText().toString())-1+"");

            }
        });
        text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Integer.valueOf(s.toString()) <= 1){
                    del.setEnabled(false);
                    del.setBackgroundResource(R.drawable.jianshao_1);
                }else{
                    del.setEnabled(true);
                    del.setBackgroundResource(R.drawable.jianshao);
                }
            }
        });

        // 加入购物车和购买功能
        Button button = dialog.findViewById(R.id.add_court);
        Button button1 = dialog.findViewById(R.id.buy_court);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpClient okHttpClient = new OkHttpClient();
                String params = "user_id="+user_id+"&goods_content="+t.getText().toString()+"&goods_type="+
                        textView8.getText().toString()+"&goods_price="+t1.getText().toString()+"&goods_count="+
                        text.getText().toString()+"&goods_id="+list.get(0).getGoodsId();
                RequestBody body = RequestBody.create(MediaType.parse("application/x-www-form-urlencoded;charset=utf-8"),
                        params);
                /*
                FormBody.Builder builder = new FormBody.Builder();
                builder.add("user_id",11+"");
                builder.add("goods_content",t.getText().toString());
                builder.add("goods_type",textView8.getText().toString());
                builder.add("goods_price",t1.getText().toString());
                builder.add("goods_count",text.getText().toString());
                builder.add("goods_id",list.get(0).getGoodsId()+"");
                FormBody body = builder.build();*/
                Request request = new Request.Builder().post(body)
                        .url(getResources().getString(R.string.ip1)+"/type/insert").build();
                final Call call = okHttpClient.newCall(request);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Response response = call.execute();

                            // 交给handler处理主线程
                            Message message = Message.obtain();
                            message.what=2;
                            message.obj = response.body().string();
                            handler.sendMessage(message);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });


        dialog.show();
    }



    public void showDialog1(String s){
        Dialog dialog = new Dialog(this,R.style.DialogTheme);
        View view = View.inflate(this,R.layout.market_goods_dialog,null);
        dialog.setContentView(view);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.main_menu_animStyle);
       window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, 1300);
        TextView textView = dialog.findViewById(R.id.return_m);
        TextView textView1 = dialog.findViewById(R.id.content);
        if (s.equals("yes")) {
            textView.setText("·七天无理由退换货");
            textView1.setText("本商品支持7天无理由退换货，需要保证退回包装完好，商品及其标配配件，赠品，说明书，保修凭证均齐全");
        }
        else{
            textView.setText("·不支持七天无理由退换货");
            textView1.setText("不支持七天无理由退换货");
        }



        dialog.show();
    }


    @Override
    public void OnBannerClick(int position) {

    }
    private class MyLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load((String) path).into(imageView);
        }
    }

}
