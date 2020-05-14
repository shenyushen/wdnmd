package com.example.a24168.myapplication.market;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.a24168.myapplication.R;
import com.example.a24168.myapplication.market.xiangqing.Image;
import com.example.a24168.myapplication.market.xiangqing.adpter.ImageAdapter;
import com.example.a24168.myapplication.market.xiangqing.entity.Comments;

import org.w3c.dom.Text;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.a24168.myapplication.market.sort.Goods.s_id;

public class CommentXiangqing extends AppCompatActivity {
    private ImageView imageView;
    private TextView textView;
    private TextView textView1;
    GridView gridView;
    private ImageView imageView2;
    private TextView textView2;
    public static Comments commentss;
    public static String[] strings;
    public static  int cur;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.market_comment_xiangqing);

        get();
        setDate();

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.anim_int_right1,R.anim.anim_out_left1);
            }
        });
    }
    public void get(){
         imageView = findViewById(R.id.icon11);
        textView = findViewById(R.id.name);
        textView1 = findViewById(R.id.x_content);
        gridView = findViewById(R.id.sss);
        imageView2 = findViewById(R.id.qq);
        textView2 = findViewById(R.id.time_a);
    }
    public void setDate(){
        textView.setText(commentss.getUser().getUsername());
        textView1.setText(commentss.getContent());
        textView2.setText(commentss.getTime());
        Glide.with(this).load(getResources().getString(R.string.ip1)+"/pic/"+
                commentss.getUser().getPhoto()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(imageView);
        ImageAdapter imageAdapter = new ImageAdapter(commentss.getImg().split(","),this,R.layout.image_item);
        gridView.setAdapter(imageAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cur = position;
                strings = commentss.getImg().split(",");
                Intent intent = new Intent();
                intent.setClass(CommentXiangqing.this,Image.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_in_right,R.anim.anim_out_left);
            }
        });
    }
}
