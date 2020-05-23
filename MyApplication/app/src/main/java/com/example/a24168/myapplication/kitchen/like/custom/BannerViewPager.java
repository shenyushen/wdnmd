package com.example.a24168.myapplication.kitchen.like.custom;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class BannerViewPager extends ViewPager {

    private List<View> list;
    private Context context;
    private Handler handler = new Handler();
    private Runnable runnable;
    private int i = 0;



    public BannerViewPager(@NonNull Context context) {
        this(context, null);
    }

    public BannerViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }



    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        super.onInterceptTouchEvent(ev);
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (getChildCount() <= 1) {
            super.onTouchEvent(ev);
        }
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (getParent() != null) {
                    getParent().requestDisallowInterceptTouchEvent(true);   //让事件不再分发
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (getParent() != null) {
                    getParent().requestDisallowInterceptTouchEvent(true);   //让事件不再分发
                }
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                if (getParent() != null) {
                    getParent().requestDisallowInterceptTouchEvent(true);   //让事件不再分发
                }
                break;
        }
        super.onTouchEvent(ev);
        return true;        //让事件不再分发
    }


    /**
     * 定义一个接收数据的方法
     */
    public void setAdapterData(List<View> list,ViewPager viewPager) {
        this.list = list;
        //创建适配器，自己调用自己
        PagerAdapter pagerAdapter = new PagerAdapter() {

            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                // TODO Auto-generated method stub
                return arg0 == arg1;
            }

            @Override
            public int getCount() {
                // TODO Auto-generated method stub
                return Integer.MAX_VALUE;
            }

            @Override
            public void destroyItem(ViewGroup container, int position,
                                    Object object) {
                // TODO Auto-generated method stub
                position = position % list.size();
                container.removeView(list.get(position));
            }


            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                // return super.instantiateItem(container, position);
                // 将图片控件添加到容器
                position = position % list.size();
                viewPager.removeView(list.get(position));
                container.addView(list.get(position));

                // 返回
                return list.get(position);
            }

            @Override
            public int getItemPosition(Object object) {
                return POSITION_NONE;
            }

        };
        this.setAdapter(pagerAdapter);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                int currentPosition = viewPager.getCurrentItem();
                Log.e("diyi","第"+i+"次:"+currentPosition);
                i++;
                viewPager.removeView(list.get(currentPosition));
                if(currentPosition == list.size() - 1){
                    // 最后一页
                    viewPager.setCurrentItem(0);
                }else{
                    viewPager.setCurrentItem(currentPosition + 1);
                }


                // 一直给自己发消息
                handler.postDelayed(this,2000);
            }
        },2000);
        //handler.post(runnable);

        this.addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                position = position % list.size();

            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    private interface OnImgClickListener{
        void onClick(int i);
    }
}
