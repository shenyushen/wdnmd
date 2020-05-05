package com.example.a24168.myapplication.register;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a24168.myapplication.R;
import com.example.a24168.myapplication.sign.Sign;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Post extends AppCompatActivity {
    private EditText edt_account;
    private EditText edt_password;
    private EditText edt_name;
    private Button btn_post;
    private Handler handler;
    private void getView(){
        edt_account=findViewById(R.id.edt_account);
        edt_password=findViewById(R.id.edt_password);
        edt_name=findViewById(R.id.edt_name);
        btn_post=findViewById(R.id.btn_post);
    }
    int temp=0;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_layout);
        getView();
        btn_post.setOnClickListener(new View.OnClickListener() {
            //创建监听器
            @Override
            public void onClick(View view) {
                add();
                handler=new Handler(){
                    public void handleMessage(Message msg) {
                        if(temp==1){
                            Toast.makeText(Post.this, "账号已被注册", Toast.LENGTH_SHORT).show();
                        }
                    }
                };
            }
        });
    }

    private void add() {
        new Thread() {
            @Override
            public void run() {
                try {
                    URL url = new URL(getResources().getString(R.string.ip)+"/viewuser");
                    URLConnection conn = url.openConnection();
                    InputStream in = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in, "utf-8"));
                    String info = reader.readLine();
                    JSONArray jsonArray = new JSONArray(info);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        if (jsonArray.getJSONObject(i).getString("account").equals(edt_account.getText().toString())) {
                            temp = 1;
                            Message msg = new Message();
                            msg.obj=temp;//传对象，还有arg1、arg2……
                            handler.sendMessage(msg);
                            break;
                        }
                    }
                    if (temp != 1) {
                        try {
                            URL url1 = new URL(getResources().getString(R.string.ip)+"/addueser?account=" + edt_account.getText().toString() + "&password=" + edt_password.getText().toString() + "&name=" + edt_name.getText().toString());
                            URLConnection conn1 = url1.openConnection();
                            InputStream in1 = conn1.getInputStream();
                            BufferedReader reader1 = new BufferedReader(new InputStreamReader(in1, "utf-8"));
                            Intent post=new Intent(Post.this, Sign.class);
                            startActivity(post);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        temp = 0;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
