package com.example.a24168.myapplication.kitchen.like.custom;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

public class RoundImageView extends AppCompatImageView {
    //画笔
    private Paint paint;
    private Bitmap bitmap;
    public RoundImageView(Context context) {
        super(context);
    }

    public RoundImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
    }


    //画圆形图片
    @Override
    protected void onDraw(Canvas canvas) {
        Drawable drawable = getDrawable();
        if(drawable!=null){
            drawableToBitamp(drawable);
            /*BitmapDrawable bd = (BitmapDrawable) drawable;
            Bitmap bitmap = bd.getBitmap();*/
            //Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
            //获得圆形的位图
            Bitmap b = getCilrcleBitmap(bitmap);
            //绘制的区域
            Rect rect = new Rect(0,0,b.getWidth(),b.getHeight());
            //绘制的大小(可以稍大一点,为边框预留)
            Rect rectDest = new Rect(0,0,getWidth(),getHeight());
            //绘制
            canvas.drawBitmap(b,rect,rectDest,paint);

        }else{
            super.onDraw(canvas);
        }
    }


    private void drawableToBitamp(Drawable drawable)
    {
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();
        System.out.println("Drawable转Bitmap");
        Bitmap.Config config =
                drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                        : Bitmap.Config.RGB_565;
        bitmap = Bitmap.createBitmap(w,h,config);
        //注意，下面三行代码要用到，否在在View或者surfaceview里的canvas.drawBitmap会看不到图
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        drawable.draw(canvas);
    }

    //获取圆形图片
    private Bitmap getCilrcleBitmap(Bitmap bitmap){//源图像
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        //打开抗锯齿
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        //设置目标图像区域外的颜色,(透明度,红色,绿色,蓝色)
        //canvas.drawARGB(90,90,0,0);
        //绘制目标图像
        canvas.drawCircle(bitmap.getWidth()/2,bitmap.getHeight()/2,bitmap.getWidth()/2,paint);
        //设置图像混合模式
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        //绘制区域
        Rect rect = new Rect(0,0,bitmap.getWidth(),bitmap.getHeight());
        //绘制位置
        // Rect rectDest = new Rect(0,0,50,50);

        canvas.drawBitmap(bitmap,rect,rect,paint);
        paint.reset();
        return output;
    }
}