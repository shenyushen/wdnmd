package com.example.a24168.myapplication.kitchen.find.custom;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewParent;

public class BannerRecyclerView extends RecyclerView {
    public BannerRecyclerView(@NonNull Context context) {
        super(context);
    }

    public BannerRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BannerRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean  dispatchTouchEvent (MotionEvent ev)   {

        ViewParent parent = this;

        while(!((parent = parent.getParent()) instanceof ViewPager)) ;// 循环查找viewPager

        parent.requestDisallowInterceptTouchEvent(true);

        return super.dispatchTouchEvent(ev);
    }
}
