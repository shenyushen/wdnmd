package com.example.a24168.myapplication.sign;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a24168.myapplication.main.MainActivity;
import com.example.a24168.myapplication.register.Post;
import com.example.a24168.myapplication.R;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class Sign extends AppCompatActivity {
    private EditText ed_account;
    private EditText ed_pass;
    private Button bt_sign;
    private Button bt_post;
    private int q1=0;
    public static int user_id;
    private Handler handler;
    private void getViews(){
        ed_account=findViewById(R.id.ed_account);
        ed_pass=findViewById(R.id.ed_pass);
        bt_post=findViewById(R.id.bt_post);
        bt_sign=findViewById(R.id.bt_sign);
    }

    private void check(String a,String b){

        new Thread() {
            @Override
            public void run() {
                try {
                    URL url = new URL(getResources().getString(R.string.ipuser)+"/user/queryuser?id="+ed_account.getText().toString()+"&password="+ed_pass.getText().toString());
                    Log.e("url", url.toString());
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    //3，建管道--InputStream
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setConnectTimeout(8000);
                    httpURLConnection.setReadTimeout(8000);
                    InputStream in = httpURLConnection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    String line;
                    String str="";

                    String string1=reader.readLine();
                    Log.e("ssss",string1);
                    if(string1.equals("true")){
                        user_id = Integer.valueOf(ed_account.getText().toString());
                        Intent i=new Intent(Sign.this, MainActivity.class);
                        startActivity(i);
                    }else{
                        Looper.prepare();
                        Toast.makeText(Sign.this, "账号或密码不正确，请重新输入！", Toast.LENGTH_SHORT).show();
                        Looper.loop();
                    }



                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_layout);
        getViews();
        bt_sign.setOnClickListener(new View.OnClickListener() {
            //创建监听器
            @Override
            public void onClick(View view) {
                check(ed_account.getText().toString(),ed_pass.getText().toString());

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
