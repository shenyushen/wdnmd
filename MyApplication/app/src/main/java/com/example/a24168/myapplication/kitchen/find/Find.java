package com.example.a24168.myapplication.kitchen.find;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.a24168.myapplication.R;
import com.example.a24168.myapplication.kitchen.find.entity.FindFriend;
import com.example.a24168.myapplication.kitchen.find.entity.FindLable;
import com.example.a24168.myapplication.kitchen.find.adapter.GridViewAdapter;
import com.example.a24168.myapplication.kitchen.find.adapter.RecyclerViewAdapter;
import com.example.a24168.myapplication.kitchen.find.custom.BannerGrid;
import com.example.a24168.myapplication.kitchen.find.custom.GlideImageLoader;
import com.example.a24168.myapplication.kitchen.find.custom.MyScrollView;
import com.example.a24168.myapplication.kitchen.find.entity.Show;
import com.example.a24168.myapplication.kitchen.find.unimportant.Create;
import com.example.a24168.myapplication.kitchen.find.unimportant.ShowDetails;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

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

public class Find extends Fragment {

    private ViewPager viewPager;
    private View view;
    private BannerGrid gridView;
    private MyScrollView mScrollView;
    private Intent intent;
    private List<FindFriend> findFriends;
    private Handler handler = new Handler();
    private RecyclerView recyclerView;
    private FloatingActionButton fab;

    private List<String> itemList;
    private List<Show> gridList;
    private List<FindLable> findLables;
    private Banner banner;
    private List<String> images;
    private List<String> titles;
    private Bitmap bitmap;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.find, container, false);
        }
        initView();

        //设置viewpager最少缓存的页数，防止自动销毁
        //viewPager.setOffscreenPageLimit(6);
        //顶部banner
        messageView();


        //标签

        itemList = new ArrayList<>();
        init();


        //设置添加监听
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(getActivity(), Create.class);
                startActivity(intent);
            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Bundle bundle = new Bundle();
                FindFriend findFriend = findFriends.get(position);
                bundle.putSerializable("findFriend", findFriend);

                Intent intent = new Intent(getContext(), ShowDetails.class);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Bundle bundle = new Bundle();

                FindFriend findFriend = findFriends.get(position);
                bundle.putSerializable("findFriend", findFriend);

                Intent intent = new Intent(getContext(), ShowDetails.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        return view;
    }

    private void bannershow(){
        images = new ArrayList<>();
        images.add("http://10.0.2.2:8080/shixun3/pic/"+findFriends.get(0).getPhoto());
        images.add("http://10.0.2.2:8080/shixun3/pic/"+findFriends.get(1).getPhoto());
        images.add("http://10.0.2.2:8080/shixun3/pic/"+findFriends.get(2).getPhoto());

        titles = new ArrayList<>();
        titles.add(findFriends.get(0).getTheme());
        titles.add(findFriends.get(1).getTheme());
        titles.add(findFriends.get(2).getTheme());

        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        banner.setBannerAnimation(Transformer.Accordion);
        //设置标题集合（当banner样式有显示title时）
        banner.setBannerTitles(titles);

        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(images);
        //设置轮播时间
        banner.setDelayTime(2000);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }



    private void init() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    findLables = new ArrayList<>();
                    Gson gson = new Gson();
                    URL url = new URL("http://10.0.2.2:8080/shixun3/find/lable");
                    URLConnection conn = url.openConnection();
                    InputStream in = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in, "utf-8"));
                    String info = reader.readLine();
                    findLables = gson.fromJson(info, TypeToken.getParameterized(List.class, FindLable.class).getType());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 0; i < findLables.size(); i++) {
                                itemList.add(findLables.get(i).getLable());
                            }
                            LinearLayoutManager layout = new LinearLayoutManager(getContext());
                            layout.setOrientation(LinearLayoutManager.HORIZONTAL);//设置为横向排列
                            recyclerView.setLayoutManager(layout);
                            RecyclerViewAdapter adapter = new RecyclerViewAdapter(itemList,getContext());
                            recyclerView.setAdapter(adapter);

                        }
                    });
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    //展示所有
    public void messageView(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    gridList = new ArrayList<Show>();
                    URL url = new URL("http://10.0.2.2:8080/shixun3/find/list");//放网站
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

                            bannershow();
                            GridViewAdapter gridViewAdapter = new GridViewAdapter(getContext(),gridList);
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

    private void initView() {
        banner = view.findViewById(R.id.banner);
        mScrollView = (MyScrollView) view.findViewById(R.id.scrollView);
        gridView = view.findViewById(R.id.gridView);
        //myGridView.setFocusable(false);
        viewPager = view.findViewById(R.id.viewpager);

        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        gridView = view.findViewById(R.id.gridView);
        fab = view.findViewById(R.id.fab);

    }

}
