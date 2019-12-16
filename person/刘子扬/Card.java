package com.example.a24168.myapplication.fragment.tabhost;

import android.app.Dialog;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.format.Time;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;


import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectChangeListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.example.a24168.myapplication.CollectorListAdapter;
import com.example.a24168.myapplication.CollectorListAdapter;
import com.example.a24168.myapplication.commomDialog;
import com.example.a24168.myapplication.commomDialog2;
import com.example.a24168.myapplication.R;
import com.example.a24168.myapplication.cardbean;
import com.example.a24168.myapplication.cardbean;
import com.example.a24168.myapplication.commomDialog;
import com.example.a24168.myapplication.commomDialog2;


import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.example.a24168.myapplication.fragment.MessageFragment.account;

public class Card extends Fragment {
    private ArrayList<Integer> options1Items = new ArrayList<>();
    List<cardbean> list = new ArrayList<>();
    List<cardbean> getList = new ArrayList<>();
    ListView listView;
    Button cancel;
    View missview;
    Button finshi;
    Button look;
    private Handler handler;
    FloatingActionButton buttonadd;
    cardbean newone = new cardbean();

    private OptionsPickerView pvOptions, pvCustomOptions, pvNoLinkOptions;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.card, container, false);
        /*
         每日打卡
         */
    listView = view.findViewById(R.id.collector_listview);
    buttonadd = view.findViewById(R.id.buttonadd);
    init();
    initOptionPicker();
    for(int i = 1;i<=7;i++){
        options1Items.add(i);
    }



    buttonadd.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final EditText et = new EditText(getContext());
            new AlertDialog.Builder(getContext()).setTitle("请输入打卡内容")
                    .setIcon(R.drawable.bixin)
                    .setView(et)
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //按下确定键后的事件
                            newone.setText(et.getText().toString());
                            pvOptions.show();
                        }
                    }).setNegativeButton("取消",null).show();

            //pvOptions.show();
        }
    });


    //线程
        handler = new Handler(){
            public void handleMessage(Message msg){
                super.handleMessage(msg);
                if(msg.what == 10){
                    List<cardbean> cardbeans = (List<cardbean>) msg.obj;
                    CollectorListAdapter adapter = new CollectorListAdapter(getContext(), cardbeans,R.layout.card_item);
                    listView.setAdapter(adapter);
//                    List<cake> datamy1 = (List<cake>) msg.obj;
//                    Mylistview_seller1 mylistview_seller1 = new Mylistview_seller1(seller.this,datamy1,R.layout.seller_1item);
//
//                    listView.setAdapter(mylistview);
                }else{

//                    List<order> datamy2 = (List<order>) msg.obj;
//                    Mylistview_seller2 mylistview_seller2 = new Mylistview_seller2(seller.this,datamy2,R.layout.seller_1item);
//                    listView2.setAdapter(mylistview2);
                }
            }
        };

        return view;

    }

    private void initOptionPicker() {
        pvOptions = new OptionsPickerBuilder(getContext(), new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {

                for(int i=0;i<=options1;i++){

                    final int finalI = i;
                    new Thread() {
                        @Override
                        public void run() {
                            try {

                                String time = getOldDate(finalI);
                                String Text = newone.getText();
                                URL url = new URL(getResources().getString(R.string.ip)+"/AddCard?a="+account+"&b="+time+"&c="+Text);
                                URLConnection conn = url.openConnection();
                                InputStream in = conn.getInputStream();
                                BufferedReader reader = new BufferedReader(new InputStreamReader(in, "utf-8"));
//                                Message msg = Message.obtain();
//                                msg.what=10;
//                                msg.obj = "ok";
//                                handler.sendMessage(msg);
                                Log.e("lzy",url.toString());

                                //刷新布局
                                getallCard();
                                Message msg = Message.obtain();
                                msg.what=10;
                                msg.obj = list;
                                handler.sendMessage(msg);

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();



                    Log.e("lzy",""+options1);
                    Log.e("lzy",""+newone.getText());
                }

                //返回数据

            }
        })
                .setTitleText("宁选择打卡几天？")
                .setContentTextSize(25)//设置滚轮文字大小
                .setDividerColor(Color.BLACK)//设置分割线的颜色
                .setSelectOptions(0, 1)//默认选中项
                .setBgColor(Color.WHITE)
                .setTitleBgColor(Color.WHITE)
                .setTitleColor(Color.BLACK)
                .setCancelColor(Color.GRAY)
                .setSubmitColor(Color.GRAY)
                .setTextColorCenter(Color.rgb(51,181,229))
                .isRestoreItem(true)//切换时是否还原，设置默认选中第一项。
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setLabels("天","1","2")

                .setOptionsSelectChangeListener(new OnOptionsSelectChangeListener() {
                    @Override
                    public void onOptionsSelectChanged(int options1, int options2, int options3) {
                        String str = "options1: " + options1 + "\noptions2: " + options2 + "\noptions3: " + options3;
                        //Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT).show();
                    }
                })

                .build();

        pvOptions.setPicker(options1Items);
    }

    private void init() {
        list.clear();
        getallCard();


        CollectorListAdapter adapter = new CollectorListAdapter(getContext(), list,R.layout.card_item);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                View view1 = LayoutInflater.from(getContext()).inflate(R.layout.popupwindouw_new,null);
                final PopupWindow popupWindow = new PopupWindow(getContext());
                popupWindow.setContentView(view1);
                //设置动画
                popupWindow.setAnimationStyle(R.style.card_popup_style);
                //设置PopupWindow宽
                popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                //设置PopupWindow高
                popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                //在父布局的弹入/出位置
                View rootView = LayoutInflater.from(getContext()).inflate(R.layout.popupwindouw_new, null);
                popupWindow.showAtLocation(rootView, Gravity.CENTER,0,0);

                popupWindow.setFocusable(false);

                missview = view1.findViewById(R.id.misspopupwindow);
                cancel = view1.findViewById(R.id.tv_cancel);
                finshi = view1.findViewById(R.id.tv_set);
                look = view1.findViewById(R.id.tv_look);
                look.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        commomDialog commomDialog = new commomDialog(getContext(), R.style.dialog, "打卡内容："+list.get(position).getText());
                        commomDialog.setTitle("提示").show();
                    }
                });

                finshi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //完成打卡操作
                        new Thread() {
                            @Override
                            public void run() {
                                try {
                                    String time = getOldDate(0);
                                    String Text = list.get(position).getText();
                                    URL url = new URL(getResources().getString(R.string.ip)+"/FinishCard?a="+account+"&b="+time+"&c="+Text);
                                    URLConnection conn = url.openConnection();
                                    InputStream in = conn.getInputStream();
                                    BufferedReader reader = new BufferedReader(new InputStreamReader(in, "utf-8"));
//                                Message msg = Message.obtain();
//                                msg.what=10;
//                                msg.obj = "ok";
//                                handler.sendMessage(msg);
                                   // Log.e("lzy",url.toString());

                                    //刷新布局
                                    getallCard();
                                    Message msg = Message.obtain();
                                    msg.what=10;
                                    msg.obj = list;
                                    handler.sendMessage(msg);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }.start();

                        popupWindow.dismiss();
                        commomDialog2 commomDialog2 = new commomDialog2(getContext(), R.style.dialog, "恭喜您完成今日打卡");
                        commomDialog2.setTitle("提示").show();
                    }
                });

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();

                    }
                });
                missview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
            }


//            @Override
//            public boolean onLongClick(View v) {
//                View view1 = LayoutInflater.from(getContext()).inflate(R.layout.popupwindouw_new,null);
//                PopupWindow popupWindow = new PopupWindow(getContext());
//                popupWindow.setContentView(view1);
//                //设置动画
//                popupWindow.setAnimationStyle(R.style.card_popup_style);
//                //设置PopupWindow宽
//                popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
//                //设置PopupWindow高
//                popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
//                //在父布局的弹入/出位置
//
//
//
//                popupWindow.setFocusable(false);
//                return false;
//            }

        });


        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                commomDialog commomDialog = new commomDialog(getContext(), R.style.dialog, "打卡内容："+list.get(position).getText());
                commomDialog.setTitle("提示").show();
                return true;
            }
        });



    }
    //获取数据库信息
    public void getallCard(){
        new Thread(){
            public void run() {
                URL url = null;
                try {
                    url = new URL(getResources().getString(R.string.ip)+"/ShowtodayCard?a="+account);
                    URLConnection conn = url.openConnection();
                    InputStream in = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in, "utf-8"));
                    String info = reader.readLine();

                    Log.e("lzy", info);

                    JSONArray res1 = new JSONArray(info);
                    list.clear();
                    for(int i=0;i<res1.length();i++){
                        String account = res1.getJSONObject(i).getString("account");
                        String Time = res1.getJSONObject(i).getString("Time");
                        String text = res1.getJSONObject(i).getString("text");
                        cardbean cardbean = new cardbean(account,text,Time);
                        list.add(cardbean);
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }}.start();
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }

//时间计算
    public static String getOldDate(int distanceDay) {
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate = new Date();
        Calendar date = Calendar.getInstance();
        date.setTime(beginDate);
        date.set(Calendar.DATE, date.get(Calendar.DATE) + distanceDay);
        Date endDate = null;
        try {
            endDate = dft.parse(dft.format(date.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dft.format(endDate);
    }



}