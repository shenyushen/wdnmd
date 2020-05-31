package com.example.a24168.myapplication.start;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.a24168.myapplication.R;
import com.example.a24168.myapplication.sign.Sign;

import java.util.Timer;
import java.util.TimerTask;

public class videoview extends AppCompatActivity implements View.OnClickListener {
    private int recLen = 10;//跳过倒计时提示5秒
    private TextView tv;
    Timer timer = new Timer();  //定义一个计时器
    private Handler handler;
    private Runnable runnable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.my_videoview);
        initView();
        timer.schedule(task, 1000, 1000);//等待时间一秒，停顿时间一秒
        /**
         * 正常情况下不点击跳过
         */
        handler = new Handler();
        handler.postDelayed(runnable = new Runnable() {
            @Override
            public void run() {
                //从闪屏界面跳转到首界面
                Intent intent = new Intent(videoview.this, Sign.class);
                startActivity(intent);
                finish();
            }
        }, 10000);//延迟5S后发送handler信息

        final CustomVideoView vv = (CustomVideoView ) this.findViewById(R.id.videoView);
        final String uri = "android.resource://" + getPackageName() + "/" + R.raw.vid;
        vv.setVideoURI(Uri.parse(uri));
        vv.start();


        vv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {


 @Override
 public void onPrepared(MediaPlayer mp) {
 mp.start();
mp.setLooping(false);


}
 });
        /*
      vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
          @Override
          public void onCompletion(MediaPlayer mp) {
              Intent intent = new Intent();
              intent.setClass(videoview.this, Sign.class);
              startActivity(intent);
          }
      });
*/

    }


    private void initView() {
        tv = findViewById(R.id.tv);//跳过
        tv.setOnClickListener(this);//跳过监听
    }

    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            runOnUiThread(new Runnable() { // UI thread
                @Override
                public void run() {
                    recLen--;
                    tv.setText("跳过 " + recLen);
                    if (recLen < 0) {
                        timer.cancel();
                        tv.setVisibility(View.GONE);//倒计时到0隐藏字体
                    }
                }
            });
        }
    };

    /**
     * 点击跳过
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv:
                //从闪屏界面跳转到首界面
                Intent intent = new Intent(videoview.this, Sign.class);
                startActivity(intent);
                finish();
                if (runnable != null) {
                    handler.removeCallbacks(runnable);
                }
                break;
            default:
                break;
        }
    }
}
