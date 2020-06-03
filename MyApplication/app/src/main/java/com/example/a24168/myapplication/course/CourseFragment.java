package com.example.a24168.myapplication.course;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.a24168.myapplication.R;
import com.example.a24168.myapplication.course.entity.comment;
import com.example.a24168.myapplication.course.entity.course;
import com.example.a24168.myapplication.course.until.CustomAdapter;
import com.example.a24168.myapplication.course.until.model.ModelHomeEntrance;
import com.example.a24168.myapplication.course.until.utils.ScreenUtil;
import com.example.a24168.myapplication.course.until.widget.IndicatorView;
import com.example.a24168.myapplication.course.videoControl.videoPlayActivity;
import com.stx.xhb.pagemenulibrary.PageMenuLayout;
import com.stx.xhb.pagemenulibrary.holder.AbstractHolder;
import com.stx.xhb.pagemenulibrary.holder.PageMenuViewHolderCreator;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class CourseFragment extends Fragment  {
    private Handler handler;
    View view;
    Button course_label_btn_text_1;
    private List<ModelHomeEntrance> homeEntrances;
    private IndicatorView entranceIndicatorView;
    private PageMenuLayout<ModelHomeEntrance> mPageMenuLayout;
    //获取控件
    private static Context context;
    ArrayList<comment> comments=new ArrayList<>();
    ArrayList<comment> commentsList=new ArrayList<>();
    private void getViews() {
        //course_label_btn_text_1=view.findViewById(R.id.course_label_btn_text_1);
    }
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null){
            view = inflater.inflate(R.layout.course, container, false);
        }
        context=this.getActivity();
        getViews();
        initData();
        initView();
        init();
        check();
        check10();
        view_lists();
        return view;
    }
    //传递
    private void getui(Object info){
        Message msg = Message.obtain();
        msg.what = 30;
        msg.obj = info;
        handler.sendMessage(msg);}
    private void getui1(Object info){
        Message msg = Message.obtain();
        msg.what = 20;
        msg.obj = info;
        handler.sendMessage(msg);}
    private void getui2(Object info){
        Message msg = Message.obtain();
        msg.what = 10;
        msg.obj = info;
        handler.sendMessage(msg);}

    private void check() {
        new Thread() {
            @Override
            public void run() {
                try {
                    URL url = new URL(getResources().getString(R.string.ip1)+"/shipin");
                    URLConnection conn = url.openConnection();
                    InputStream in = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in, "utf-8"));
                    String info = reader.readLine();
                    ListView listView = view.findViewById(R.id.lv_persons1);
                    ArrayList<course> lists=new ArrayList<>();
                    try {
                        JSONArray jsonArray = new JSONArray(info);
                        for(int i = 0; i < jsonArray.length();i++){
                            lists.add(new course(Integer.parseInt(jsonArray.getJSONObject(i).getString("mv_pic")),jsonArray.getJSONObject(i).getString("time"),jsonArray.getJSONObject(i).getString("mv_name"),jsonArray.getJSONObject(i).getString("mv_account"),jsonArray.getJSONObject(i).getString("mv_path"),jsonArray.getJSONObject(i).getString("author")));
                        }
                        for(com.example.a24168.myapplication.course.entity.course course:lists){
                            Log.e("iii",""+course.toString());
                        }
                        getui(lists);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    // wrapperMessage(list);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private void check10() {
        new Thread() {
            @Override
            public void run() {
                try {
                    URL url = new URL(getResources().getString(R.string.ip1)+"/pinglun");
                    URLConnection conn = url.openConnection();
                    InputStream in = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in, "utf-8"));
                    String info = reader.readLine();
                    ArrayList<comment> lists=new ArrayList<>();
                    try {
                        JSONArray jsonArray = new JSONArray(info);
                        for(int i = 0; i < jsonArray.length();i++){
                            lists.add(new comment(jsonArray.getJSONObject(i).getString("author"),jsonArray.getJSONObject(i).getString("content"),jsonArray.getJSONObject(i).getString("mv_id"),jsonArray.getJSONObject(i).getString("mvcomment_id")));
                        }
                        getui2(lists);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private void view_lists(){
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                ListView listView = view.findViewById(R.id.lv_persons1);
                if (msg.what == 10) { comments = (ArrayList<comment>) msg.obj;
                for(comment comment:comments){
                    Log.e("test-------------******",comment.toString());
                }
                }
                if (msg.what == 30) {
                    ArrayList<course> info = (ArrayList<course>) msg.obj;
                    CustomAdapter custtomAdapter10 = new CustomAdapter(context, info, R.layout.course_list_item_view_layout);
                    listView.setAdapter(custtomAdapter10);
                    listView.setOnItemClickListener(
                            new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    Log.e(position+"","test");
                                    ArrayList<String> infoList= new ArrayList<>();
                                   infoList.add(info.get(position).getMv_path());
                                    Intent i=new Intent(getActivity(), videoPlayActivity.class);
                                    for(comment comment:comments){
                                        if(info.get(position).getMv_name().equals(comment.getMv_id())){
                                            commentsList.add(comment);
                                        }
                                    }
                                    i.putExtra("listobj", (Serializable) commentsList);
                                    i.putStringArrayListExtra("infoList",infoList );
                                    startActivity(i);
                                    infoList.clear();
                                    commentsList.clear();
                                }
                            }
                    );
                }
            }
        };
    }


    private void initView() {
        entranceIndicatorView = view.findViewById(R.id.main_home_entrance_indicator);
        mPageMenuLayout = view.findViewById(R.id.pagemenu);
    }


    private void initData() {
        homeEntrances = new ArrayList<>();
        homeEntrances.add(new ModelHomeEntrance("米饭", R.drawable.mi));
        homeEntrances.add(new ModelHomeEntrance("甜点", R.drawable.tian));
        homeEntrances.add(new ModelHomeEntrance("蛋糕", R.drawable.dangao));
        homeEntrances.add(new ModelHomeEntrance("中式料理", R.drawable.zhong));
        homeEntrances.add(new ModelHomeEntrance("面条", R.drawable.miantiao));
        homeEntrances.add(new ModelHomeEntrance("蔬菜", R.drawable.shucai));
        homeEntrances.add(new ModelHomeEntrance("饮品", R.drawable.yinpin));
        homeEntrances.add(new ModelHomeEntrance("水果沙拉", R.drawable.shala));
        homeEntrances.add(new ModelHomeEntrance("酸", R.drawable.suan));
        homeEntrances.add(new ModelHomeEntrance("甜", R.drawable.tang));
        homeEntrances.add(new ModelHomeEntrance("咸", R.drawable.xian));
        homeEntrances.add(new ModelHomeEntrance("辣", R.drawable.la));
    }

    private void check1(String a) {
        new Thread() {
            @Override
            public void run() {
                try {
                    URL url = new URL(getResources().getString(R.string.ip1)+"/findbiao?label="+a);
                    URLConnection conn = url.openConnection();
                    InputStream ini = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(ini, "utf-8"));
                    String info = reader.readLine();
                    ArrayList<course> list1=new ArrayList<>();
                    try {
                        JSONArray jsonArray = new JSONArray(info);
                        for(int i = 0; i < jsonArray.length();i++){
                            list1.add(new course(Integer.parseInt(jsonArray.getJSONObject(i).getString("mv_pic")),jsonArray.getJSONObject(i).getString("time"),jsonArray.getJSONObject(i).getString("mv_name"),jsonArray.getJSONObject(i).getString("mv_account"),jsonArray.getJSONObject(i).getString("mv_path"),jsonArray.getJSONObject(i).getString("author")));
                        }
                        Log.e("tetstttttt","\"http://10.0.2.2:8080/fanqian/findbiao?label=\"+a");
                        getui1(list1);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }


    private void init() {
        mPageMenuLayout.setPageDatas(homeEntrances, new PageMenuViewHolderCreator() {
            @Override
            public AbstractHolder createHolder(View itemView) {
                return new AbstractHolder<ModelHomeEntrance>(itemView) {
                    private TextView entranceNameTextView;
                    private ImageView entranceIconImageView;

                    @Override
                    protected void initView(View itemView) {
                        entranceIconImageView = itemView.findViewById(R.id.entrance_image);
                        entranceNameTextView = itemView.findViewById(R.id.entrance_name);
                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (int) ((float) ScreenUtil.getScreenWidth() / 4.0f));
                        itemView.setLayoutParams(layoutParams);
                    }

                    @Override
                    public void bindView(RecyclerView.ViewHolder holder, final ModelHomeEntrance data, int pos) {
                        entranceNameTextView.setText(data.getName());
                        entranceIconImageView.setImageResource(data.getImage());
                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                check1(data.getName());
                                handler = new Handler() {
                                    @Override
                                    public void handleMessage(Message msg) {
                                        super.handleMessage(msg);
                                        ListView listView = view.findViewById(R.id.lv_persons1);
                                        if (msg.what == 20) {
                                            ArrayList<course> info = (ArrayList<course>) msg.obj;
                                            CustomAdapter custtomAdapter10 = new CustomAdapter(context, info, R.layout.course_list_item_view_layout);
                                            listView.setAdapter(custtomAdapter10);
                                            custtomAdapter10.notifyDataSetChanged();
                                            listView.setOnItemClickListener(
                                                    new AdapterView.OnItemClickListener() {
                                                        @Override
                                                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                            Log.e(position+"","test");
                                                            Log.e("test***************", "sadasda1");
                                                            ArrayList<String> infoList= new ArrayList<>();
                                                            Log.e("test***************", "sadasda");
                                                            Log.e("test***************", info.get(0).getMv_path());
                                                          /*  for(com.example.a24168.myapplication.course.entity.course course:info){
                                                                infoList.add(course.getMv_path());
                                                                infoList.add(info.get(position).getMv_path());
                                                                Log.e("test***************",course.getMv_path());
                                                            }*/
                                                            infoList.add(info.get(position).getMv_path());
                                                            for(comment comment:comments){
                                                                if(info.get(position).getMv_name().equals(comment.getMv_id())){
                                                                    commentsList.add(comment);
                                                                }
                                                            }
                                                            Intent i=new Intent(getActivity(), videoPlayActivity.class);
                                                            i.putExtra("listobj", (Serializable) commentsList);
                                                            i.putStringArrayListExtra("infoList",infoList );
                                                            startActivity(i);
                                                            infoList.clear();
                                                            commentsList.clear();
                                                        }
                                                    }
                                            );
                                        }
                                    }
                                };
                            }
                        });
                    }
                };
            }

            @Override
            public int getLayoutId() {
                return R.layout.course_item_home_entrance;
            }
        });
        entranceIndicatorView.setIndicatorCount(mPageMenuLayout.getPageCount());
        mPageMenuLayout.setOnPageListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                entranceIndicatorView.setCurrentIndicator(position);
            }
        });
    }








}
