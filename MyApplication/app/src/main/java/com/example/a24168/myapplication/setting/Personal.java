package com.example.a24168.myapplication.setting;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a24168.myapplication.R;
import com.example.a24168.myapplication.main.MainActivity;
import com.zxy.tiny.Tiny;
import com.zxy.tiny.callback.FileWithBitmapCallback;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.example.a24168.myapplication.main.MainActivity.we;

public class Personal extends AppCompatActivity  implements View.OnClickListener{

    //MainActivity mainActivity=new MainActivity();
    //调取系统摄像头的请求码
    private static final int MY_ADD_CASE_CALL_PHONE = 6;
    //打开相册的请求码
    private static final int MY_ADD_CASE_CALL_PHONE2 = 7;
    private AlertDialog.Builder builder;
    private AlertDialog dialog;
    private LayoutInflater inflater;
    private ImageView imageView;
    private View layout;
    private TextView takePhotoTV;
    private TextView choosePhotoTV;
    private TextView cancelTV;
    private TextView save;
    private TextView quit;
    private EditText ed_username;
    private EditText ed_sex;
    private EditText ed_profession;
    private EditText ed_home;
    private EditText ed_label;
    private EditText ed_birthday;

    private  File file;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personaldata);
        imageView = findViewById(R.id.personalphoto);
        ed_username = findViewById(R.id.edt_name);
        ed_sex = findViewById(R.id.edt_xing);
        ed_profession = findViewById(R.id.edt_ai);
        ed_birthday = findViewById(R.id.edt_sheng);
        ed_home = findViewById(R.id.edt_home);
        ed_label = findViewById(R.id.edt_qian);
        save = findViewById(R.id.save);
        quit = findViewById(R.id.quit);

        /*Intent intent=this.getIntent();
        String idd=intent.getStringExtra("iddd");*/
       // Log.e("idd",idd);

        save.setOnClickListener(v -> {
            new Thread(){
                @Override
                public void  run(){
                    try {

                        URL url = new URL(getResources().getString(R.string.ipuser)+"/user/updateuser?id="+we+"&username="+ed_username.getText().toString()
                        +"&sex="+ed_sex.getText().toString()+"&profession="+ed_profession.getText().toString()+"&birthday="+ed_birthday.getText().toString()
                        +"&home="+ed_home.getText().toString()+"&label="+ed_label.getText().toString()+"&photo="+file.getName());

                        //Log.e("updateurl", url.toString());
                        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                        //3，建管道--InputStream
                        httpURLConnection.setRequestMethod("GET");
                        httpURLConnection.setConnectTimeout(8000);
                        httpURLConnection.setReadTimeout(8000);
                        InputStream in = httpURLConnection.getInputStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                        String string2=reader.readLine();
                        //Log.e("jsonstring",string2);


                        Intent intent=new Intent(Personal.this, MainActivity.class);
                        startActivity(intent);
                        //overridePendingTransition(R.anim.anim_int_right1,R.anim.anim_out_left1);
                    }catch (MalformedURLException e){
                        e.printStackTrace();
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
            }.start();
        });

        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Personal.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_int_right1,R.anim.anim_out_left1);
            }
        });

    }


    /*
    初始化控件方法
     */
    public void viewInit() {

        builder = new AlertDialog.Builder(this);//创建对话框
        inflater = getLayoutInflater();
        layout = inflater.inflate(R.layout.dialog_select_photo, null);//获取自定义布局
        builder.setView(layout);//设置对话框的布局
        dialog = builder.create();//生成最终的对话框
        dialog.show();//显示对话框

        takePhotoTV = layout.findViewById(R.id.photograph);
        choosePhotoTV = layout.findViewById(R.id.photo);
        cancelTV = layout.findViewById(R.id.cancel);
        //设置监听
        takePhotoTV.setOnClickListener(this);
        choosePhotoTV.setOnClickListener(this);
        cancelTV.setOnClickListener(this);

        quit.setOnClickListener(this);
    }

    /**
     * 修改头像按钮执行方法
     * @param view
     */
    public void UpdatePhoto(View view) {
        viewInit();
    }

    private void takePhoto() throws IOException {
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        // 获取文件
        File file = createFileIfNeed("UserIcon.png");
        //拍照后原图回存入此路径下
        Uri uri;
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) {
            uri = Uri.fromFile(file);
        } else {
            /**
             * 7.0 调用系统相机拍照不再允许使用Uri方式，应该替换为FileProvider
             * 并且这样可以解决MIUI系统上拍照返回size为0的情况
             */
            uri = FileProvider.getUriForFile(this, "com.example.a24168.myapplication.fileprovider", file);
        }
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
        startActivityForResult(intent, 1);
    }

    // 在sd卡中创建一保存图片（原图和缩略图共用的）文件夹
    private File createFileIfNeed(String fileName) throws IOException {
        String fileA = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath() + "/nbinpic";
        File fileJA = new File(fileA);
        if (!fileJA.exists()) {
            fileJA.mkdirs();
        }
        File file = new File(fileA, fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        return file;
    }

    /**
     * 打开相册
     */
    private void choosePhoto() {
        //这是打开系统默认的相册(就是你系统怎么分类,就怎么显示,首先展示分类列表)
        Intent picture = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(picture, 2);
    }

    /**
     * 申请权限回调方法
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        if (requestCode == MY_ADD_CASE_CALL_PHONE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                try {
                    takePhoto();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(this,"拒绝了你的请求",Toast.LENGTH_SHORT).show();
                //"权限拒绝");
                // TODO: 2018/12/4 这里可以给用户一个提示,请求权限被拒绝了
            }
        }


        if (requestCode == MY_ADD_CASE_CALL_PHONE2) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                choosePhoto();
            } else {
                //"权限拒绝");
                // TODO: 2018/12/4 这里可以给用户一个提示,请求权限被拒绝了
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    /**
     * startActivityForResult执行后的回调方法，接收返回的图片
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode != Activity.RESULT_CANCELED) {

            String state = Environment.getExternalStorageState();
            if (!state.equals(Environment.MEDIA_MOUNTED)) return;
            // 把原图显示到界面上
            Tiny.FileCompressOptions options = new Tiny.FileCompressOptions();
            Tiny.getInstance().source(readpic()).asFile().withOptions(options).compress(new FileWithBitmapCallback() {
                @Override
                public void callback(boolean isSuccess, Bitmap bitmap, String outfile, Throwable t) {
                    saveImageToServer(bitmap, outfile);//显示图片到imgView上
                }
            });
        } else if (requestCode == 2 && resultCode == Activity.RESULT_OK
                && null != data) {
            try {
                Uri selectedImage = data.getData();//获取路径
                Tiny.FileCompressOptions options = new Tiny.FileCompressOptions();
                Tiny.getInstance().source(selectedImage).asFile().withOptions(options).compress(new FileWithBitmapCallback() {
                    @Override
                    public void callback(boolean isSuccess, Bitmap bitmap, String outfile, Throwable t) {
                        saveImageToServer(bitmap, outfile);
                    }
                });
            } catch (Exception e) {
                //"上传失败");
            }
        }
    }

    /**
     * 从保存原图的地址读取图片
     */
    private String readpic() {
        String filePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath() + "/nbinpic/" + "UserIcon.png";
        return filePath;
    }

    private void saveImageToServer(final Bitmap bitmap, final String outfile) {

        Log.e("dd",outfile);
        new Thread(){
            public void run(){
                file = new File(outfile);
                OkHttpClient client = new OkHttpClient();
                String encrypt = "multipart/form-data";
                RequestBody fileBody = RequestBody.create(MediaType.parse("image/jpeg"), file);
                RequestBody requestBody = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("upfile", file.getName(), fileBody)
                        .addFormDataPart("enctype", encrypt)
                        .build();
                Request.Builder builder = new Request.Builder();
                Request request = builder.post(requestBody).url(getResources().getString(R.string.ipuser)+"/user/upload2").build();

                Call call=client.newCall(request);
                try {
                    Response response=call.execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        // TODO: 2018/12/4  这里就可以将图片文件 file 上传到服务器,上传成功后可以将bitmap设置给你对应的图片展示
        imageView.setImageBitmap(bitmap);

        File file=new File(outfile);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.photograph:
                //"点击了照相";
                //  6.0之后动态申请权限 摄像头调取权限,SD卡写入权限
                //判断是否拥有权限，true则动态申请
                if (ContextCompat.checkSelfPermission(Personal.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                        && ContextCompat.checkSelfPermission(Personal.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(Personal.this,
                            new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            MY_ADD_CASE_CALL_PHONE);
                } else {
                    try {
                        //有权限,去打开摄像头
                        takePhoto();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                dialog.dismiss();
                break;


            /*case R.id.save:
                Intent intent=new Intent(this, MainActivity.class);
                startActivity(intent);

                break;*/

            case R.id.quit:
                dialog.dismiss();
                break;

            case R.id.photo:
                //"点击了相册";
                //  6.0之后动态申请权限 SD卡写入权限
                if (ContextCompat.checkSelfPermission(Personal.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(Personal.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            MY_ADD_CASE_CALL_PHONE2);

                } else {
                    //打开相册
                    choosePhoto();
                }
                dialog.dismiss();
                break;
            case R.id.cancel:
                dialog.dismiss();//关闭对话框
                break;
            default:break;
        }
    }


}

