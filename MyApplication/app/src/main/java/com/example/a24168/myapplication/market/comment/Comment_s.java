package com.example.a24168.myapplication.market.comment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
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
import com.example.a24168.myapplication.kitchen.find.entity.FindFriend;
import com.example.a24168.myapplication.market.sort.Goods;
import com.example.a24168.myapplication.market.xiangqing.XiangQing;
import com.google.gson.Gson;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.permissions.Permission;
import com.luck.picture.lib.permissions.RxPermissions;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.functions.Consumer;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import static com.example.a24168.myapplication.sign.Sign.user_id;
import static com.example.a24168.myapplication.main.MainActivity.findeditText;

public class Comment_s extends AppCompatActivity {

    private int maxSelectNum = 5;
    private List<LocalMedia> selectList = new ArrayList<>();
    private Grid adapter;
    private RecyclerView mRecyclerView;
    private PopupWindow pop;
    private Button button;
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.market_comment_s);

        mRecyclerView = findViewById(R.id.recycler_s);

        initWidget();
        button = findViewById(R.id.btn_s);
        editText = findViewById(R.id.edit_s);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);int month = calendar.get(Calendar.MONTH)+1;
                int day = calendar.get(Calendar.DATE);
                String time = year+"-"+month+"-"+day;
                send(time);
                //  selectList 路径

            }
        });

    }
    public void send(String time){
        new Thread(){
            public void run(){
                OkHttpClient client = new OkHttpClient.Builder()
                        .writeTimeout(30, TimeUnit.SECONDS)
                        .build();
                try {
                    MediaType MutilPart_Form_Data = MediaType.parse("multipart/form-data; charset=utf-8");

                    MultipartBody.Builder requestBodyBuilder = new MultipartBody.Builder()
                            .setType(MultipartBody.FORM);
                    String content = editText.getText().toString();

                    //循环添加文件
                    for (int i = 0; i < selectList.size(); i++) {
                        Log.e("selectlist",selectList.get(i).getPath());
                        File file = new File(selectList.get(i).getPath());
                        requestBodyBuilder.addFormDataPart("selectfile", file.getName(), RequestBody.create(MutilPart_Form_Data, new File(selectList.get(i).getPath())));

                    }
                    requestBodyBuilder.addFormDataPart("content", content);
                    requestBodyBuilder.addFormDataPart("time", time);
                    requestBodyBuilder.addFormDataPart("user_id", user_id+"");
                    requestBodyBuilder.addFormDataPart("goods_id","1") ;
                    RequestBody requestBody = requestBodyBuilder.build();
                    Request request = new Request.Builder()
                            .url( "http://10.0.2.2:8080/shixun3/type/insert33")
                            .post(requestBody)
                            .build();
                    Response response = client.newCall(request).execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
    private void initWidget() {
        Full manager = new Full(this, 3, GridLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);
        adapter = new Grid(this, onAddPicClickListener);
        adapter.setList(selectList);
        adapter.setSelectMax(maxSelectNum);
        mRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new Grid.OnItemClickListener() {
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
                            PictureSelector.create(Comment_s.this).externalPicturePreview(position, selectList);
                            Log.e("123",media.getPath());
                            break;
                        case 2:
                            // 预览视频
                            PictureSelector.create(Comment_s.this).externalPictureVideo(media.getPath());
                            break;
                        case 3:
                            // 预览音频
                            PictureSelector.create(Comment_s.this).externalPictureAudio(media.getPath());
                            break;
                    }
                }
            }
        });
    }

    private Grid.onAddPicClickListener onAddPicClickListener = new Grid.onAddPicClickListener() {

        @SuppressLint("CheckResult")
        @Override
        public void onAddPicClick() {
            //获取写的权限
            RxPermissions rxPermission = new RxPermissions(Comment_s.this);
            rxPermission.requestEach(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .subscribe(new Consumer<Permission>() {
                        @Override
                        public void accept(Permission permission) {
                            if (permission.granted) {// 用户已经同意该权限
                                //第一种方式，弹出选择和拍照的dialog
                                showPop();

                                //第二种方式，直接进入相册，但是 是有拍照得按钮的
//                                showAlbum();
                            } else {
                                Toast.makeText(Comment_s.this, "拒绝", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    };

//    private void showAlbum() {
//        //参数很多，根据需要添加
//        PictureSelector.create(MainActivity.this)
//                .openGallery(PictureMimeType.ofImage())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
//                .maxSelectNum(maxSelectNum)// 最大图片选择数量
//                .minSelectNum(1)// 最小选择数量
//                .imageSpanCount(4)// 每行显示个数
//                .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选PictureConfig.MULTIPLE : PictureConfig.SINGLE
//                .previewImage(true)// 是否可预览图片
//                .isCamera(true)// 是否显示拍照按钮
//                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
//                //.setOutputCameraPath("/CustomPath")// 自定义拍照保存路径
//                .enableCrop(true)// 是否裁剪
//                .compress(true)// 是否压缩
//                //.sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
//                .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
//                .withAspectRatio(1, 1)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
//                //.selectionMedia(selectList)// 是否传入已选图片
//                //.previewEggs(false)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
//                //.cropCompressQuality(90)// 裁剪压缩质量 默认100
//                //.compressMaxKB()//压缩最大值kb compressGrade()为Luban.CUSTOM_GEAR有效
//                //.compressWH() // 压缩宽高比 compressGrade()为Luban.CUSTOM_GEAR有效
//                //.cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效
//                .rotateEnabled(false) // 裁剪是否可旋转图片
//                //.scaleEnabled()// 裁剪是否可放大缩小图片
//                //.recordVideoSecond()//录制视频秒数 默认60s
//                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
//    }

    private void showPop() {
        View bottomView = View.inflate(Comment_s.this, R.layout.layout_bottom_dialog_s, null);
        TextView mAlbum = bottomView.findViewById(R.id.tv_album_s);
        TextView mCamera = bottomView.findViewById(R.id.tv_camera_s);
        TextView mCancel = bottomView.findViewById(R.id.tv_cancel_s);

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
                    case R.id.tv_album_s:
                        //相册
                        PictureSelector.create(Comment_s.this)
                                .openGallery(PictureMimeType.ofImage())
                                .maxSelectNum(maxSelectNum)
                                .minSelectNum(1)
                                .imageSpanCount(4)
                                .selectionMode(PictureConfig.MULTIPLE)
                                .forResult(PictureConfig.CHOOSE_REQUEST);
                        break;
                    case R.id.tv_camera_s:
                        //拍照
                        PictureSelector.create(Comment_s.this)
                                .openCamera(PictureMimeType.ofImage())
                                .forResult(PictureConfig.CHOOSE_REQUEST);
                        break;
                    case R.id.tv_cancel_s:
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

}
