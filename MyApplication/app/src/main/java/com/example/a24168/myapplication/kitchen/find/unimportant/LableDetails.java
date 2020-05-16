package com.example.a24168.myapplication.kitchen.find.unimportant;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.a24168.myapplication.R;


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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_lable);


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

                        String myUrl = "http://10.0.2.2:8080/shixun3/pic/"+findFriends.get(i).getPhoto();

                        String myUrl1 = "http://10.0.2.2:8080/shixun3/pic/"+findFriends.get(i).getUser().getPhoto();
                        Show show = new Show(myUrl,findFriends.get(i).getTheme(),myUrl1,findFriends.get(i).getUser().getUsername(),findFriends.get(i).getLikenum());
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