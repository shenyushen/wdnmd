package com.example.a24168.myapplication.kitchen.find.unimportant;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a24168.myapplication.R;
import com.example.a24168.myapplication.kitchen.find.adapter.FullyGridLayoutManager;
import com.example.a24168.myapplication.kitchen.find.adapter.GridImageAdapter;
import com.example.a24168.myapplication.kitchen.find.entity.FindFriend;
import com.google.gson.Gson;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.permissions.Permission;
import com.luck.picture.lib.permissions.RxPermissions;

import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownServiceException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.functions.Consumer;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Create extends AppCompatActivity {
    private int maxSelectNum = 5;
    private List<LocalMedia> selectList = new ArrayList<>();
    private GridImageAdapter adapter;
    private RecyclerView mRecyclerView;
    private PopupWindow pop;
    private Button button;
    private EditText editbiaoti;
    private EditText editneirong;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_create);
        button = findViewById(R.id.tijiao);
        mRecyclerView = findViewById(R.id.recycler);
        editbiaoti = findViewById(R.id.editbiaoti);
        editneirong = findViewById(R.id.editneirong);

        initWidget();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                upload();
            }
        });
    }

    private void initWidget() {
        FullyGridLayoutManager manager = new FullyGridLayoutManager(this, 1, GridLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(manager);
        adapter = new GridImageAdapter(this, onAddPicClickListener);
        adapter.setList(selectList);
        adapter.setSelectMax(maxSelectNum);
        mRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new GridImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                if (selectList.size() > 0) {
                    LocalMedia media = selectList.get(position);
                    String pictureType = media.getPictureType();
                    int mediaType = PictureMimeType.pictureToVideo(pictureType);
                    switch (mediaType) {
                        case 1:
                            // 预览图片 可自定长按保存路径
                            //PictureSelector.create(MainActivity.this).externalPicturePreview(position, "/custom_file", selectList);
                            PictureSelector.create(Create.this).externalPicturePreview(position, selectList);
                            break;
                        case 2:
                            // 预览视频
                            PictureSelector.create(Create.this).externalPictureVideo(media.getPath());
                            break;
                        case 3:
                            // 预览音频
                            PictureSelector.create(Create.this).externalPictureAudio(media.getPath());
                            break;
                    }
                }
            }
        });
    }

    private GridImageAdapter.onAddPicClickListener onAddPicClickListener = new GridImageAdapter.onAddPicClickListener() {

        @SuppressLint("CheckResult")
        @Override
        public void onAddPicClick() {
            //获取写的权限

            int REQUEST_EXTERNAL_STORAGE=1;
            String[] PERMISSIONS_STORAGE={Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
            if (PackageManager.PERMISSION_GRANTED!= ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_CONTACTS))
            {
                RxPermissions rxPermission = new RxPermissions(Create.this);
                rxPermission.requestEach(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .subscribe(new Consumer<Permission>() {
                            @Override
                            public void accept(Permission permission) {
                                if (permission.granted) {// 用户已经同意该权限
                                    //第一种方式，弹出选择和拍照的dialog
                                    showPop();

                                    //第二种方式，直接进入相册，但是 是有拍照得按钮的
                                    //showAlbum();
                                } else {
                                    Toast.makeText(Create.this, "拒绝", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }


        }
    };

    /*private void showAlbum() {
        //参数很多，根据需要添加
        PictureSelector.create(MainActivity.this)
                .openGallery(PictureMimeType.ofImage())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                .maxSelectNum(maxSelectNum)// 最大图片选择数量
                .minSelectNum(1)// 最小选择数量
                .imageSpanCount(4)// 每行显示个数
                .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选PictureConfig.MULTIPLE : PictureConfig.SINGLE
                .previewImage(true)// 是否可预览图片
                .isCamera(true)// 是否显示拍照按钮
                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                //.setOutputCameraPath("/CustomPath")// 自定义拍照保存路径
                .enableCrop(true)// 是否裁剪
                .compress(true)// 是否压缩
                //.sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                .withAspectRatio(1, 1)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                //.selectionMedia(selectList)// 是否传入已选图片
                //.previewEggs(false)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
                //.cropCompressQuality(90)// 裁剪压缩质量 默认100
                //.compressMaxKB()//压缩最大值kb compressGrade()为Luban.CUSTOM_GEAR有效
                //.compressWH() // 压缩宽高比 compressGrade()为Luban.CUSTOM_GEAR有效
                //.cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效
                .rotateEnabled(false) // 裁剪是否可旋转图片
                //.scaleEnabled()// 裁剪是否可放大缩小图片
                //.recordVideoSecond()//录制视频秒数 默认60s
                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
    }*/

    private void showPop() {
        View bottomView = View.inflate(Create.this, R.layout.findlayout_bottom_dialog, null);
        TextView mAlbum = bottomView.findViewById(R.id.tv_album);
        TextView mCamera = bottomView.findViewById(R.id.tv_camera);
        TextView mCancel = bottomView.findViewById(R.id.tv_cancel);

        pop = new PopupWindow(bottomView, -1, -2);
        pop.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        pop.setOutsideTouchable(true);
        pop.setFocusable(true);
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.5f;
        getWindow().setAttributes(lp);
        pop.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });
        pop.setAnimationStyle(R.style.main_menu_photo_anim);
        pop.showAtLocation(getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.tv_album:
                        //相册
                        PictureSelector.create(Create.this)
                                .openGallery(PictureMimeType.ofImage())
                                .maxSelectNum(maxSelectNum)
                                .minSelectNum(1)
                                .imageSpanCount(4)
                                .selectionMode(PictureConfig.MULTIPLE)
                                .forResult(PictureConfig.CHOOSE_REQUEST);
                        break;
                    case R.id.tv_camera:
                        //拍照
                        PictureSelector.create(Create.this)
                                .openCamera(PictureMimeType.ofImage())
                                .forResult(PictureConfig.CHOOSE_REQUEST);
                        break;
                    case R.id.tv_cancel:
                        //取消
                        //closePopupWindow();
                        break;
                }
                closePopupWindow();
            }
        };

        mAlbum.setOnClickListener(clickListener);
        mCamera.setOnClickListener(clickListener);
        mCancel.setOnClickListener(clickListener);
    }

    public void closePopupWindow() {
        if (pop != null && pop.isShowing()) {
            pop.dismiss();
            pop = null;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        List<LocalMedia> images;
        if (resultCode == RESULT_OK) {
            if (requestCode == PictureConfig.CHOOSE_REQUEST) {// 图片选择结果回调

                images = PictureSelector.obtainMultipleResult(data);
                selectList.addAll(images);

                //selectList = PictureSelector.obtainMultipleResult(data);

                // 例如 LocalMedia 里面返回三种path
                // 1.media.getPath(); 为原图path
                // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
                adapter.setList(selectList);
                adapter.notifyDataSetChanged();
            }
        }

    }

    /*public void postSubmit(View view){
        //获取输入框的用户名和密码
        String aname=et_aname.getText().toString();
        String apass=et_apass.getText().toString();
        //服务器验证路径
        String path="http://192.168.43.210:8080/T2/alogin.do";
        new MyPostTask().execute(aname,apass,path);
    }*/






    public void upload(){
        new Thread(){
            public void run(){
                OkHttpClient client = new OkHttpClient.Builder()
                        .writeTimeout(30, TimeUnit.SECONDS)
                        .build();
                try {
                    MediaType MutilPart_Form_Data = MediaType.parse("multipart/form-data; charset=utf-8");

                    MultipartBody.Builder requestBodyBuilder = new MultipartBody.Builder()
                            .setType(MultipartBody.FORM);
                    String biaoti = editbiaoti.getText().toString().trim();
                    String neirong = editneirong.getText().toString().trim();

                    FindFriend findFriend = new FindFriend();
                    findFriend.setTheme(biaoti);
                    findFriend.setData(neirong);
                    Gson gson = new Gson();
                    String json = gson.toJson(findFriend);
                    //循环添加文件

                    for (int i = 0; i < selectList.size(); i++) {
                        Log.e("selectlist",selectList.get(i).getPath());
                        File file = new File(selectList.get(i).getPath());
                        requestBodyBuilder.addFormDataPart("selectfile", file.getName(), RequestBody.create(MutilPart_Form_Data, new File(selectList.get(i).getPath())));
                        requestBodyBuilder.addFormDataPart("data", json);
                    }

                    RequestBody requestBody = requestBodyBuilder.build();
                    Request request = new Request.Builder()
                            .url( "http://10.0.2.2:8080/shixun3/find/pic")
                            .post(requestBody)
                            .build();
                    Response response = client.newCall(request).execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public void LoginByPost()  {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String biaoti = editbiaoti.getText().toString().trim();
                String neirong = editneirong.getText().toString().trim();

                FindFriend findFriend = new FindFriend();
                findFriend.setTheme(biaoti);
                findFriend.setData(neirong);
                Gson gson = new Gson();
                String json = gson.toJson(findFriend);
                // 将需要传递的参数封装到List<NameValuePair>类型的对象中
        /*List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("username", user.getUserName()));
        params.add(new BasicNameValuePair("password", user.getUserPass()));*/
                // 将封装参数的对象存入request中，并设置编码方式
                String params = "findfriend=" + json;
                byte[] data = params.getBytes();
                Log.e("selectlist", String.valueOf(data));
                try {
                    URL url = new URL("http://10.0.2.2:8080/shixun3/find/create");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setConnectTimeout(1000);
                    //这是请求方式为POST
                    conn.setRequestMethod("POST");
                    //设置post请求必要的请求头
                    conn.setRequestProperty("Charsert", "UTF-8");
                    conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8"); // 请求头, 必须设置
                    conn.setRequestProperty("Content-Length", data.length + ""); // 注意是字节长度, 不是字符长度
                    conn.setDoOutput(true); // 准备写出
                    conn.getOutputStream().write(data); // 写出数据

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        }

}
