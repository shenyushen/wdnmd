package com.example.a24168.myapplication.kitchen.like;

import android.content.Intent;
import android.os.Bundle;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.a24168.myapplication.R;
import com.example.a24168.myapplication.kitchen.like.adapter.GridViewAdapter;
import com.example.a24168.myapplication.kitchen.like.custom.BannerGrid;
import com.example.a24168.myapplication.kitchen.like.entity.FindFriend;
import com.example.a24168.myapplication.kitchen.like.entity.Show;
import com.example.a24168.myapplication.kitchen.like.unimportant.ShowDetails;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import static com.example.a24168.myapplication.sign.Sign.user_id;


public class Like extends Fragment {
    View view;
    private BannerGrid gridView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<FindFriend> findFriends;
    public static List<Show> gridList;
    private Handler handler = new Handler();
    public static GridViewAdapter gridViewAdapter;
    public static int w;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.like, container, false);
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
        gridView = view.findViewById(R.id.gridView1);
        messageView();
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                w= position;
                Bundle bundle = new Bundle();
                FindFriend findFriend = findFriends.get(position);
                bundle.putSerializable("findFriend", findFriend);

                Intent intent = new Intent(getContext(), ShowDetails.class);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //模拟网络请求需要3000毫秒，请求完成，设置setRefreshing 为false
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        messageView();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 3000);
            }
        });

        return view;
    }


    public void messageView(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    gridList = new ArrayList<Show>();
                    URL url = new URL("http://10.0.2.2:8080/shixun3/like/list?id="+user_id);//放网站
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
                        Show show = new Show(myUrl,findFriends.get(i).getTheme(),myUrl1,findFriends.get(i).getUser().getUsername(),findFriends.get(i).getLikenum(),findFriends.get(i).getId());
                        Log.e("friends",findFriends.get(0).getFindLable().getLable());
                        gridList.add(show);

                    }
                    handler.post(new Runnable() {
                        @Override
                        public void run() {

                            gridViewAdapter = new GridViewAdapter(getContext(),gridList);
                            gridView.setAdapter(gridViewAdapter);

                        }
                    });
                    /*Message msg = Message.obtain();
                    msg.obj = gridList;
                    msg.what = 1;
                    handler.sendMessage(msg);*/

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }
}

