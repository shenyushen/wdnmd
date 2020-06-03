package com.example.a24168.myapplication.kitchen.recommand.addmenu;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.LongDef;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.a24168.myapplication.R;
import com.example.a24168.myapplication.kitchen.recommand.Recommand;
import com.example.a24168.myapplication.main.MainActivity;
import com.nostra13.universalimageloader.utils.L;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class addmenu extends AppCompatActivity implements View.OnClickListener  {
    private String TAG = this.getClass().getSimpleName();
    private Handler handler=new Handler();;
//    单选框设置菜谱类型
    private RadioGroup menutype;
//    多选设置菜普口味
    private LinearLayout menukouwei;
    //装在所有动态添加的Item的LinearLayout容器
    private LinearLayout addHotelNameView;
    private int testi=-1;
    String urlstring;
    private List<String> menuzlistphoto=new ArrayList<String>();
    private String menuyulanphoto="";
    private String menubaiotistring="";
    private String menumiaoshustring="";
    static ImageView addmenuimage;
    private ImageView menu_yulan;
    private EditText menubaioti;
    private EditText menumiaoshu;
    private static int RESULT_LOAD_IMAGE = 1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //此处为获取权限目前没用
        int REQUEST_EXTERNAL_STORAGE=1;
        String[] PERMISSIONS_STORAGE={
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };
        if (PackageManager.PERMISSION_GRANTED!=
                ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_CONTACTS))
        {
            ActivityCompat.requestPermissions(this,PERMISSIONS_STORAGE,REQUEST_EXTERNAL_STORAGE);
        }


        super.onCreate(savedInstanceState);
        setContentView(R.layout.addmenumain);
        addHotelNameView = (LinearLayout) findViewById(R.id.ll_addView);
        menu_yulan=findViewById(R.id.menuz_yulan);
        menubaioti=findViewById(R.id.menuz_biaoti);
        menumiaoshu=findViewById(R.id.menuz_miaoshu);
        menutype=findViewById(R.id.menutype);
        menukouwei=findViewById(R.id.menukouwei);
        menu_yulan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testi=-1;
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });
        findViewById(R.id.btn_getData).setOnClickListener(this);

        //默认添加一个Item
        addViewItem(null);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_addHotel://点击添加按钮就动态添加Item
                addViewItem(v);
                break;
            case R.id.btn_getData://打印数据
                printData();
                break;
        }
    }

    /**
     * Item排序
     */
    private void sortHotelViewItem() {
        int i=0;
        //获取LinearLayout里面所有的view
        for ( i = 0; i < addHotelNameView.getChildCount(); i++) {
            final View childAt = addHotelNameView.getChildAt(i);
            final Button btn_remove = (Button) childAt.findViewById(R.id.btn_addHotel);
            btn_remove.setText("删除此步骤");
            btn_remove.setTag("remove");//设置删除标记
            int finalI = i;
            btn_remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //从LinearLayout容器中删除当前点击到的ViewItem
                    Log.e("是否运行",childAt+"");
                    if(menuzlistphoto.size()>=1){
                        if(menuzlistphoto.size()>finalI){
                            menuzlistphoto.remove(finalI);
                        }

                    }
                    addHotelNameView.removeView(childAt);
                }
            });
            //如果是最后一个ViewItem，就设置为添加
            if (i == (addHotelNameView.getChildCount() - 1)) {
                Button btn_add = (Button) childAt.findViewById(R.id.btn_addHotel);
                btn_add.setText("+新增步骤");
                btn_add.setTag("add");
                btn_add.setOnClickListener(this);
            }

            addmenuimage=childAt.findViewById(R.id.menu_list);
//            添加图片
            if(menuzlistphoto.size()>i){
                File file = new File(menuzlistphoto.get(i));
                if(file.exists()){
                    Bitmap bm = BitmapFactory.decodeFile(menuzlistphoto.get(i));
                    addmenuimage.setImageBitmap(bm);
                }
            }


            addmenuimage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                     testi = finalI;
                    Intent i = new Intent(
                            Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                    i.putExtra("i",i);
                    startActivityForResult(i, RESULT_LOAD_IMAGE);
                }
            });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            Log.e("图片名字及路径", picturePath +" "+ menuzlistphoto.size()+" testi " + testi);
            if(testi==-1){
                menuyulanphoto=picturePath;
                File file = new File(menuyulanphoto);
                if(file.exists()){
                    Bitmap bm = BitmapFactory.decodeFile(menuyulanphoto);
                    menu_yulan.setImageBitmap(bm);
                }
            }
            else{
                if(menuzlistphoto.size()==0){
                    menuzlistphoto.add(picturePath);
                }
                else if(menuzlistphoto.size()>testi){
                    menuzlistphoto.set(testi,picturePath);
                }
                else{
                    menuzlistphoto.add(picturePath);
                }
            }
            sortHotelViewItem();
//            File file = new File(picturePath);
//            记载图片至imageview，从本地
//            if(file.exists()){
//                Bitmap bm = BitmapFactory.decodeFile(picturePath);
//                addmenuimage.setImageBitmap(bm);
//            }
        }
    }



    //添加ViewItem
    private void addViewItem(View view) {
        if (addHotelNameView.getChildCount() == 0) {//如果一个都没有，就添加一个
            View hotelEvaluateView = View.inflate(this, R.layout.addmenuitem, null);
            Button btn_add = (Button) hotelEvaluateView.findViewById(R.id.btn_addHotel);
            btn_add.setText("+新增步骤");
            btn_add.setTag("add");
            btn_add.setOnClickListener(this);
            addHotelNameView.addView(hotelEvaluateView);
            sortHotelViewItem();
        } else if (((String) view.getTag()).equals("add")) {//如果有一个以上的Item,点击为添加的Item则添加
            View hotelEvaluateView = View.inflate(this, R.layout.addmenuitem, null);
            addHotelNameView.addView(hotelEvaluateView);
            sortHotelViewItem();
        }
    }

    //获取所有动态添加的Item，找到控件的id，获取数据
    private void printData() {
//        url后缀的参数
        int Tag=1;
        urlstring="addmenulist?";
//        拼接描述
        int miaoshunum=0;
        for (int i = 0; i < addHotelNameView.getChildCount(); i++) {
            View childAt = addHotelNameView.getChildAt(i);
            EditText hotelEvaluate = (EditText) childAt.findViewById(R.id.ed_hotelEvaluate);
            if(!hotelEvaluate.getText().toString().equals("")){
                miaoshunum++;
                if(i==0){
                    urlstring+="buzhou=";
                }
                urlstring+=hotelEvaluate.getText().toString()+"'";
            }
            else{
                Tag=0;
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getBaseContext(), "描述有问题请重新尝试.", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
        Log.e("描述之后",Tag+" urlstring "+urlstring);
        if(Tag==1){
            urlstring=urlstring.substring(0,urlstring.length() - 1);
//        拼接描述的图片
            int ai=0;
            upload(menuyulanphoto);
            if(menuzlistphoto.size()==0 || miaoshunum!=menuzlistphoto.size()){
                Tag=0;
            }
            for(String a :menuzlistphoto){
                if(!a.equals("")){
                    if(ai==0){
                        urlstring+="&buzhouphoto=";
                    }
                    urlstring+=a.split("/")[a.split("/").length-1]+"'";
                }
                else{
                    Tag=0;
                }
                upload(a);
                ai++;
            }
            urlstring=urlstring.substring(0,urlstring.length() - 1);
        }
        Log.e("描述图片之后",Tag+" urlstring "+urlstring);
        menubaiotistring=menubaioti.getText().toString();
        Log.e("menubiaoti",menubaiotistring);
//        拼接标题
        if(!menubaiotistring.equals("") && Tag==1){
            urlstring+="&biaoti=";
            urlstring+=menubaiotistring;
        }
        else{
            Tag=0;
        }
        menumiaoshustring=menumiaoshu.getText().toString();
        if(!menumiaoshustring.equals("") && Tag==1){
            urlstring+="&miaoshu=";
            urlstring+=menumiaoshustring;
        }
        else{
            Tag=0;
        }
        Log.e("拼接标题",Tag+" urlstring "+urlstring);
//        拼接标题的图片
        if(!menuyulanphoto.equals("") && Tag==1){
            urlstring+="&photo=";
            urlstring+=menuyulanphoto.split("/")[menuyulanphoto.split("/").length-1].trim();
        }
        else{
            Tag=0;
        }
        Log.e("拼接标题的图片",Tag+" urlstring "+urlstring);
//        拼接type
        if(Tag==1){
            int intag=0;
            for(int i=0; i<menutype.getChildCount();i++) {
                //循环遍历rg下子元素的个数，并获取按钮

                RadioButton rabt = (RadioButton) menutype.getChildAt(i);
                //判断，答案前第一个字符。
                if (rabt.isChecked()) {
                    if(intag==0){
                        urlstring+="&type=";
                        intag=1;
                    }
                    urlstring+=rabt.getText().toString().trim();
                }
                if(i==menutype.getChildCount()-1 && intag==0){
                    Tag=0;
                }
            }
        }
        Log.e("拼接type",Tag+" urlstring "+urlstring);
//        拼接口味
        if(Tag==1){
            int intag1=0;
            int count = menukouwei.getChildCount();
            for(int i = 0;i < count;i++){
//			获得子控件对象
                View child = menukouwei.getChildAt(i);
//			判断是否是CheckBox
                if(child instanceof CheckBox){
//			转为CheckBox对象

                    CheckBox cb = (CheckBox)child;
                    if(cb.isChecked()){
                        if(intag1==0){
                            urlstring+="&kouwei=";
                            intag1=1;
                        }
                        urlstring+=i+1+"'";
                    }
                    if(i==count-1 && intag1==0){
                        Tag=0;
                    }

                }
            }
            if(Tag==1){
                urlstring=urlstring.substring(0,urlstring.length() - 1);
            }
        }
        Log.e("拼接口味",Tag+" urlstring "+urlstring);
        if(Tag==1){
            starurl(urlstring);
        }
        else{
            handler.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getBaseContext(), "填写数据有误无法上传", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
    public void starurl(String urlstring){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //1,找水源--创建URL
                    URL url = new URL(getBaseContext().getResources().getString(R.string.ip) + urlstring);//放网站
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
                    String str = "";
                    while ((line = reader.readLine()) != null) {
                        str += line;
                    }
//                    获取的是HTML样式的，此步骤是进行更改
                    int s = str.indexOf("<body>");
                    int e = str.indexOf("</body>");
                    String ss = str.substring(s + "<body>".length(), e);
                    if (ss.equals("1")){

                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                addmenu.this.finish();
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
    public void upload(String filepath){
            new Thread(){
                public void run(){
                    OkHttpClient client = new OkHttpClient();
                    File file = new File(filepath);
                    String imageType = "multipart/form-data";
                    RequestBody fileBody = RequestBody.create(MediaType.parse("image/jpg"), file);
                    RequestBody requestBody = new MultipartBody.Builder()
                            .setType(MultipartBody.FORM)
                            .addFormDataPart("upfile", file.getName(), fileBody)
                            .addFormDataPart("enctype", imageType)
                            .build();
                    Request.Builder builder = new Request.Builder();
                    Request request = builder.post(requestBody).url(getResources().getString(R.string.ip)+"addmenu").build();
                    Log.e("url",getResources().getString(R.string.ip)+"addmenu");
                    try {
                        Response response = client.newCall(request).execute();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
    }
}
