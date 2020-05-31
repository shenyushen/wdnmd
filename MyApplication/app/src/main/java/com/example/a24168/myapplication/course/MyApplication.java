package com.example.a24168.myapplication.course;

import android.app.Application;

import com.example.a24168.myapplication.course.until.utils.ScreenUtil;

/**
 * Author: Mr.xiao on 2017/5/24
 *
 * @mail:xhb_199409@163.com
 * @github:https://github.com/xiaohaibin
 * @describe:
 */

public class MyApplication extends Application {
    public static Application sApplication;

    public static Application getApplication() {
        return sApplication;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;
        ScreenUtil.init(this);

    }
}
