package com.example.a24168.myapplication.sign;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.a24168.myapplication.main.MainActivity;
import com.example.a24168.myapplication.register.Post;
import com.example.a24168.myapplication.R;


public class Sign extends AppCompatActivity {
    private EditText ed_account;
    private EditText ed_pass;
    private Button bt_sign;
    private Button bt_post;
    private int q1=0;
    private Handler handler;
    private void getViews(){
        ed_account=findViewById(R.id.ed_account);
        ed_pass=findViewById(R.id.ed_pass);
        bt_post=findViewById(R.id.bt_post);
        bt_sign=findViewById(R.id.bt_sign);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_layout);
        getViews();
        bt_sign.setOnClickListener(new View.OnClickListener() {
            //创建监听器
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Sign.this, MainActivity.class);
                startActivity(intent);
            }
        });
        bt_post.setOnClickListener(new View.OnClickListener() {
            //创建监听器
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Sign.this, Post.class);
                startActivity(i);
            }
        });
    }
}
