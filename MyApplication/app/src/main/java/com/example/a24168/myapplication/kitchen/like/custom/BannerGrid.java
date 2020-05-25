package com.example.a24168.myapplication.kitchen.like.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

public class BannerGrid extends GridView {


    public BannerGrid(Context context) {
        super(context);
    }

    public BannerGrid(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BannerGrid(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public BannerGrid(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);//这里返回的是刚写好的expandSpec
    }

}
