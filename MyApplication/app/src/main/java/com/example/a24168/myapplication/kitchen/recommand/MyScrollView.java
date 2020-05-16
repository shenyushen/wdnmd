package com.example.a24168.myapplication.kitchen.recommand;
import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

import static com.example.a24168.myapplication.kitchen.recommand.menudetails.linearLayout;

public class MyScrollView extends ScrollView {
    private Context mContext;
    OnTouchListener mGestureListener;
    // 滑动距离及坐标
    private float xDistance, yDistance, xLast, yLast,upx,upy;
    public MyScrollView(Context context) {
        super(context);
        mContext=context;
    }
    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext=context;
    }
    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext=context;
    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                xDistance = yDistance = 0f;
                xLast = ev.getX();
                yLast = ev.getY();
                Log.e("xLast",xLast+"");
                Log.e("yLast",yLast+"");
                break;
            case MotionEvent.ACTION_MOVE:
                final float curX = ev.getX();
                final float curY = ev.getY();
                xDistance += Math.abs(curX - xLast);
                yDistance += Math.abs(curY - yLast);
                Log.e("xDistance",xDistance+"");
                Log.e("yDistance",yDistance+"");
                Log.e("curX",curX+"");
                Log.e("curY",curY+"");

                if(this.getScrollY() == 0){
                    // 到顶部了
//                    上滑事件
                    if((curY <yLast)){
                        return  true;
                    }
                    else{
                        return false;
                    }
                }


                if(linearLayout.getMeasuredHeight() <= getScrollY() + getHeight()){
                    //滑动到底部
                    //TODO
                    if((curY <yLast)){
                        return  false;
                    }
                    else{
                        return true;
                    }
                }
                if(xDistance > yDistance){
                    return false;
                }


                xLast = curX;
                yLast = curY;
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        try {
//            //最大高度显示为屏幕内容高度的一半
//            Display display = ((Activity) mContext).getWindowManager().getDefaultDisplay();
//            DisplayMetrics d = new DisplayMetrics();
//            display.getMetrics(d);
//            //此处是关键，设置控件高度不能超过屏幕高度一半（d.heightPixels / 2）（在此替换成自己需要的高度）
//            heightMeasureSpec = MeasureSpec.makeMeasureSpec(d.heightPixels+50, MeasureSpec.AT_MOST);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        //重新计算控件高、宽
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//    }
}