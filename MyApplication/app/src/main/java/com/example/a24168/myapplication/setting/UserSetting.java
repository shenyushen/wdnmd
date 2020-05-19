package com.example.a24168.myapplication.setting;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.a24168.myapplication.R;
import com.example.a24168.myapplication.main.MainActivity;
import com.example.a24168.myapplication.sign.Sign;

public class UserSetting extends AppCompatActivity {

    TextView textView;
    TextView textView2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.usersetting);

        textView=findViewById(R.id.settingquit);
        textView2=findViewById(R.id.tuichu);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(UserSetting.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_int_right1,R.anim.anim_out_left1);
            }
        });

        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(UserSetting.this, Sign.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_int_right1,R.anim.anim_out_left1);
            }
        });
    }
}
