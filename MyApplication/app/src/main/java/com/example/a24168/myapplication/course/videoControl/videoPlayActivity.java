package com.example.a24168.myapplication.course.videoControl;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.a24168.myapplication.R;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

/**
 * @author SIONON
 * @description:
 * @date :2020/5/5 13:57
 */
public class videoPlayActivity extends AppCompatActivity{
    JCVideoPlayer videoController1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_main_video_player_layout);

        videoController1 = (JCVideoPlayer) findViewById(R.id.videocontroller1);
        videoController1.setSkin(R.color.colorAccent, R.color.colorPrimary, R.drawable.skin_seek_progress,
                R.color.bottom_bg, R.drawable.skin_enlarge_video, R.drawable.skin_shrink_video);
        videoController1.setUp("http://2449.vod.myqcloud.com/2449_43b6f696980311e59ed467f792.f20.mp4",
                "http://p.qpic.cn/videoyun/0/2449_43b6f696980311e567f22794e792_1/640",
                "sion：视频测试");
    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
