package com.example.a24168.myapplication.kitchen.recommand;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a24168.myapplication.R;
import com.example.a24168.myapplication.entity.label;
import com.example.a24168.myapplication.entity.menu;
import com.example.a24168.myapplication.kitchen.recommand.addmenu.addmenu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/*此文件为推荐的主文件，其中inittext（）方法可以重用来获取数据
* 1，oncreate中添加了筛选条件的数组，通过设置点击事件来进行初始化及出现消失*/
public class Recommand extends Fragment {
//    页面跳转
    private Intent menudetalisintent=new Intent();

//    筛选需要的变量

    private List<menu> menus=new ArrayList<menu>();
// 声明PopupWindow对象的引用
    private Handler handler=new Handler();;
    private List<Classify> classifyList;
    private List<Classify> producingList;
    private PopupWindow popupWindow;
    //分类的gridview
    private GridView gridView1;
    private GridView gridViewzmenulist;
    //产地的gridview
    private GridView gridView2;
    private TextView shaixuan;
    //popupWindow的布局view
    private View popupWindow_view;
    private static View view;
    public static Context context;
    private GridviewAdapter mydapter;
    private GridviewAdapter mydapter2;
    private RecyclerView recyclerView;
    private ImageView addimage;
    private StaggeredGridLayoutManager layoutManager;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.recommand, container, false);
        context=getContext();
        layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        shaixuan=view.findViewById(R.id.shaixuan);
        addimage=view.findViewById(R.id.addz_menu);
//    筛选功能的代码
        //向type集合中 添加数据
        classifyList = new ArrayList<>();
        classifyList.add(new Classify("米"));
        classifyList.add(new Classify("面"));
        classifyList.add(new Classify("菜"));
//        classifyList.add(new Classify("待定"));

        //向口味集合中 添加数据
        producingList = new ArrayList<>();
        producingList.add(new Classify("全部"));
        producingList.add(new Classify("酸"));
        producingList.add(new Classify("甜"));
        producingList.add(new Classify("苦"));
        producingList.add(new Classify("辣"));
        producingList.add(new Classify("咸"));
//        通过绑定点击，进行筛选框的初始化及相应使用
        shaixuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //创建popupwindow
                getPopupWindow();
                // 这里是位置显示方式,在屏幕的右侧
                popupWindow.showAtLocation(view, Gravity.RIGHT, 0, 0);
            }
        });
        addimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), addmenu.class);
                startActivity(intent);

            }
        });
        inittext("list");

        return view;
}

    /**
     * 点击筛选弹出菜单
     *
     * @param
     */

    //在这获取popupwindow的实例
    public void inittext(String urlstring){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
                    recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
                    menus.clear();
                    //1,找水源--创建URL
                    URL url = new URL(context.getResources().getString(R.string.ip)+urlstring);//放网站
                    //2,开水闸--openConnection
                    Log.e("url",url.toString());
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
//                    获取的是HTML样式的，此步骤是进行更改
                    int s = str.indexOf("<body>");
                    int e = str.indexOf("</body>");
                    String ss = str.substring(s+"<body>".length(), e);
//                    判断是否为空
                    if(ss.trim().equals("")){
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getContext(), "没有相应的食品,为你推荐其他食品", Toast.LENGTH_SHORT).show();
                                inittext("list");
                            }
                        });
                    }
                    else{
                        //                    进行自己的分割方式获取单个数据
                        String [] m=ss.split("''");
                        for(String me:m) {
                            if (!me.equals("")) {
                                String[] men = me.split(",");
                                menu meuna = new menu();
                                meuna.setMenu_id(Integer.valueOf(men[0].trim()));
                                meuna.setDate(men[1]);
                                meuna.setMenu_photo(men[2]);
                                meuna.setMenu_name(men[3]);
                                String[] labels = men[4].trim().split("'");
//                        分割菜单的类型部分，由于是list因此要循环
                                for (String label : labels) {
                                    if (!label.equals("")) {
                                        label.trim();
                                        com.example.a24168.myapplication.entity.label label1 = new label();
                                        label1.setLabel_id(Integer.valueOf(label.split("-")[0].trim()));
                                        label1.setLabel_name(label.split("-")[1]);
                                        meuna.getLabels().add(label1);
                                    }
                                }
                                menus.add(meuna);
                            }
                        }
//                    将获取的数据传递给graview，使其显示
                        handler.post(new Runnable() {
                            @Override
                            public void run() {

                                recyclerView.setLayoutManager(layoutManager);
                                pubuceshiadpater a=new pubuceshiadpater(menus);
                                recyclerView.setAdapter(a);
                            }
                        });
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    private void getPopupWindow() {
        if (null != popupWindow) {
            popupWindow.dismiss();
            return;
        } else {
            initPopuptWindow();
        }
    }

    /**
     * 创建 PopuptWindow的实例
     */
    public void initPopuptWindow() {
        popupWindow_view = View.inflate(getContext(), R.layout.popupwindow_right, null);
        // 创建PopupWindow实例,LayoutParams.WRAP_CONTENT,LayoutParams.MATCH_PARENT分别是宽度和高度
        popupWindow = new PopupWindow(popupWindow_view, ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.MATCH_PARENT, true);
        // 设置动画效果
        popupWindow.setAnimationStyle(R.style.AnimationFade);
        gridView1 = (GridView) popupWindow_view.findViewById(R.id.gridview1);
        gridView2 = (GridView) popupWindow_view.findViewById(R.id.gridview2);
        Button btnOk = (Button) popupWindow_view.findViewById(R.id.btn_ok);
        Button btnCancal = (Button) popupWindow_view.findViewById(R.id.btn_cancal);
//        确定按钮点击事件
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = "";
                String str2 = "";
                //取出选中的属性
                for (int i = 0; i < classifyList.size(); i++) {
                    if (classifyList.get(i).isChecked()) {
                        str = classifyList.get(i).getStr();
                    }
                }
                //取出选中的属性
                for (int i = 0; i < producingList.size(); i++) {
                    if (producingList.get(i).isChecked()) {
                        str2 += producingList.get(i).getStr()+",";
                    }
                }
                //关闭popupWindow
                if (popupWindow != null && popupWindow.isShowing()) {
                    popupWindow.dismiss();
                    popupWindow = null;
                }
                if(!str2.equals("")){str2 = str2.substring(0, str2.length() -1);}
                else{
                    str2+="*";
                }
                if(str.equals("")){str = str+"*";}
                String u="findmenusbylabels?shaixuan="+str+"'"+str2;
                inittext(u);

            }
        });

        btnCancal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < classifyList.size(); i++) {
                    classifyList.get(i).setChecked(false);
                }
                for (int i = 0; i < producingList.size(); i++) {
                    producingList.get(i).setChecked(false);
                }
                mydapter.getSaveList().clear();
                mydapter.notifyDataSetChanged();
                mydapter2.notifyDataSetChanged();
            }
        });

        mydapter = new GridviewAdapter(classifyList, getContext());
        gridView1.setAdapter(mydapter);
        gridView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //如果选中就设置非选中。非选中就设置选中
                classifyList.get(position).setChecked(!classifyList.get(position).isChecked());
                //只有一条目选中
                for (int i = 0; i < classifyList.size(); i++) {
                    if (i == position) {
                        continue;
                    }
                    classifyList.get(i).setChecked(false);
                }
                mydapter.notifyDataSetChanged();
            }
        });


        mydapter2 = new GridviewAdapter(producingList, getContext());
        gridView2.setAdapter(mydapter2);
        gridView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                如果为0就是全选
                if(position==0){
                    for (int i = 0; i < producingList.size(); i++) {
                    producingList.get(i).setChecked(true);
                    }
                }
                else{
                    //如果选中就设置非选中。非选中就设置选中
                    producingList.get(position).setChecked(!producingList.get(position).isChecked());
                    //只有一条目选中
//                for (int i = 0; i < producingList.size(); i++) {
//                    if (i == position) {
//                        continue;
//                    }
//                    producingList.get(i).setChecked(false);
//                }
                }

                mydapter2.notifyDataSetChanged();
            }
        });


        //对popupWindows进行触摸监听
        popupWindow_view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                boolean b = inRangeOfView(popupWindow_view, event);
                if (b) {
                    return true;
                } else
                    return false;
            }
        });
    }

    /**
     * 用于判断点击范围是否在弹出框内，点击外面就收起popupWindows
     */
    private boolean inRangeOfView(View view, MotionEvent ev) {
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        int x = location[0];
        int y = location[1];
        if (ev.getX() < x || ev.getX() > (x + view.getWidth()) || ev.getY() < y || ev.getY() > (y + view.getHeight())) {
            if (popupWindow != null && popupWindow.isShowing()) {
                popupWindow.dismiss();
                popupWindow = null;
            }
            return false;
        }
        return true;
    }

//    @Override
//    public void onBackPressed() {
//        //关闭popupWindow
//        if (popupWindow != null && popupWindow.isShowing()) {
//            popupWindow.dismiss();
//            popupWindow = null;
//        }
////        super.onBackPressed();
//        getActivity().finish();
//    }
}

