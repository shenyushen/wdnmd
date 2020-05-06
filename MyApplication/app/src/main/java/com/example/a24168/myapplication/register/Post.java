package com.example.a24168.myapplication.register;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
import java.net.HttpURLConnection;
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

            }
        });
    }

    private void add() {
        new Thread() {
            @Override
            public void run() {
                try {

                    URL url = new URL(getResources().getString(R.string.ipuser)+"/user/insertusers?id=" + edt_account.getText().toString() + "&password=" + edt_password.getText().toString() + "&username=" + edt_name.getText().toString());//放网站
                    //2,开水闸--openConnection
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
                    while ((line = reader.readLine()) != null) {
                        str+=line;
                    }
                    Log.e("ssss",str);
                    Intent post=new Intent(Post.this, Sign.class);
                    startActivity(post);



                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
