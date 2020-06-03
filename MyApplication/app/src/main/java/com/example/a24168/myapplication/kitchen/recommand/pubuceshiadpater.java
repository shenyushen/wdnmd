package com.example.a24168.myapplication.kitchen.recommand;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.a24168.myapplication.R;
import com.example.a24168.myapplication.entity.menu;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static com.example.a24168.myapplication.kitchen.recommand.Recommand.context;

public class pubuceshiadpater extends RecyclerView.Adapter<pubuceshiadpater.ViewHolder> {
    private Handler handler=new Handler();
    private List<menu> menus;
    private Intent menudetalisintent=new Intent();
    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView menu_nametextview;
        public ViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.menu_photo);
            menu_nametextview = (TextView) view.findViewById(R.id.menu_name);
        }

    }

    public pubuceshiadpater(List<menu> fruitList) {
        menus = fruitList;
    }

    @Override

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_list_gridview_adapter, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    /**
     * 获取服务器上的图片尺寸
     */
    public static void getImgWH(int [] a,String urls) throws Exception {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(urls);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    //3，建管道--InputStream
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setConnectTimeout(8000);
                    httpURLConnection.setReadTimeout(8000);
                    httpURLConnection.setDoInput(true);
                    httpURLConnection.connect();
                    InputStream is = httpURLConnection.getInputStream();
                    Bitmap image = BitmapFactory.decodeStream(is);
                    int srcWidth = image.getWidth();      // 源图宽度
                    int srcHeight = image.getHeight();    // 源图高度
                    a[0] = srcWidth;
                    a[1] = srcHeight;
                    a[1]=a[1]*220/a[0];
                    a[0]=220;
                    //释放资源
                    image.recycle();
                    is.close();
                    httpURLConnection.disconnect();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
            new Thread(new Runnable(){
                @Override
                public void run() {
                    try {
                        int [] a=new int[2];
                        RequestOptions options = new RequestOptions();
                        options.placeholder(R.drawable.jiazai);
                        getImgWH(a,context.getResources().getString(R.string.photoip)+menus.get(position).getMenu_photo());
                        holder.imageView.getLayoutParams().height = a[1];
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                Glide.with(context)
                                        .load(context.getResources().getString(R.string.photoip)+menus.get(position).getMenu_photo())
                                        .apply(options)
                                        .into(holder.imageView);
                            }
                        });

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        menu menu = menus.get(position);
        holder.menu_nametextview.setText(menu.getMenu_name());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menudetalisintent.setClass(context, menudetails.class);
                menudetalisintent.putExtra("menu",menus.get(position).tointent());
                context.startActivity(menudetalisintent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return menus.size();
    }
}