package com.example.a24168.myapplication.kitchen.recommand;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.a24168.myapplication.R;

public class miaoshudatu extends AppCompatActivity {
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.miaoshudatu);
        imageView=findViewById(R.id.maioshudatu);
        Intent intent =getIntent();
        String url=intent.getStringExtra("url");
        Glide.with(this)
                .load(this.getResources().getString(R.string.photoip)+url)
                .into(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
