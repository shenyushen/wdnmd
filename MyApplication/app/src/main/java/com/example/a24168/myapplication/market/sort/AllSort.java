package com.example.a24168.myapplication.market.sort;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.a24168.myapplication.R;
import com.example.a24168.myapplication.market.adapter.AllSortAdapter;
import com.example.a24168.myapplication.market.entity.Type;

import java.util.ArrayList;
import java.util.List;

public class AllSort extends AppCompatActivity {
    private GridView gridView;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.market_sortall);
        //获取id控件
        get();
        // 加载布局
        load();

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.anim_int_right1,R.anim.anim_out_left1);
            }
        });
    }
    public void get(){
        gridView = findViewById(R.id.rec1);
        imageView = findViewById(R.id.fanhui);
    }
    public void load(){
        List<Type> list = getTypeDate();
        AllSortAdapter allSortAdapter = new AllSortAdapter(list,this,R.layout.market_sortall_item);
        gridView.setAdapter(allSortAdapter);
    }

    public List<Type> getTypeDate(){
            List<Type> types = new ArrayList<>();
            types.add(new Type(0,"烘培",R.drawable.hongpei));
            types.add(new Type(1,"果蔬生鲜",R.drawable.shucai));
            types.add(new Type(2,"器具",R.drawable.qiju));
            types.add(new Type(3,"领券",R.drawable.quan));
            types.add(new Type(4,"方便食品",R.drawable.fangbianshipin));
            types.add(new Type(5,"饮品茶酒",R.drawable.yinpin));
            types.add(new Type(6,"零食",R.drawable.lingshi));
            types.add(new Type(7,"腌制腊肉",R.drawable.yanzhilapin));
            types.add(new Type(8,"南北干货",R.drawable.nanbeiganhuo));
            types.add(new Type(9,"进口食品",R.drawable.jinkoushipin));
            types.add(new Type(10,"米面粮油",R.drawable.you));
            types.add(new Type(11,"厨房电器",R.drawable.dianqi));
            types.add(new Type(12,"礼盒",R.drawable.lihe));
            types.add(new Type(13,"调味品",R.drawable.tiaoweipin));
            return types;
    }

}
