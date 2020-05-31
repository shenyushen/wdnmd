package com.example.a24168.myapplication.kitchen.find.unimportant;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.a24168.myapplication.R;
import com.example.a24168.myapplication.kitchen.find.adapter.GridViewAdapter;
import com.example.a24168.myapplication.kitchen.find.adapter.ListViewAdapter;
import com.example.a24168.myapplication.kitchen.find.custom.BannerGrid;
import com.example.a24168.myapplication.kitchen.find.entity.FindFriend;
import com.example.a24168.myapplication.kitchen.find.entity.Show;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class LableDetails extends AppCompatActivity {
    private List<Show> gridList;
    private List<FindFriend> findFriends;
    private Handler handler = new Handler();
    private BannerGrid gridView;
    private ImageView fanhui;
    private TextView labletitle;
    private TextView describe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_lable);

        fanhui = findViewById(R.id.fanhui);
        labletitle = findViewById(R.id.labletitle);
        describe = findViewById(R.id.describe);

        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LableDetails.this.finish();
            }
        });

        gridView = findViewById(R.id.lablegridView);
        String result = getIntent().getStringExtra("url");
        String title = getIntent().getStringExtra("title");
        String des = getIntent().getStringExtra("des");
        labletitle.setText(title);
        describe.setText(des);
        messageView(result);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                FindFriend findFriend = findFriends.get(position);
                bundle.putSerializable("findFriend", findFriend);
                Intent intent = new Intent(getApplicationContext(), ShowDetails.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
    public void messageView(String result){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    gridList = new ArrayList<Show>();

                    URL url = new URL(result);//放网站
                    URLConnection conn = url.openConnection();
                    InputStream in = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in, "utf-8"));

                    String info = reader.readLine();
                    //JSONArray jsonArray = new JSONArray(info);
                    findFriends = new ArrayList<FindFriend>();
                    Gson gson = new Gson();
                    findFriends = gson.fromJson(info, TypeToken.getParameterized(List.class, FindFriend.class).getType());

                    for(int i = 0; i < findFriends.size();i++) {
                        String myUrl = getResources().getString(R.string.ip1)+"/pic/"+findFriends.get(i).getPhoto();
                        String myUrl1 = getResources().getString(R.string.ip1)+"/pic/"+findFriends.get(i).getUser().getPhoto();
                        Show show = new Show(myUrl,findFriends.get(i).getTheme(),myUrl1,findFriends.get(i).getUser().getUsername(),findFriends.get(i).getLikenum(),findFriends.get(i).getId());
                        gridList.add(show);

                    }
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            GridViewAdapter gridViewAdapter = new GridViewAdapter(LableDetails.this,gridList);
                            gridView.setAdapter(gridViewAdapter);

                        }
                    });


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }
}