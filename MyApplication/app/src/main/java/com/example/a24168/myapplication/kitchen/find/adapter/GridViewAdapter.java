package com.example.a24168.myapplication.kitchen.find.adapter;

import android.content.Context;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.a24168.myapplication.R;
import com.example.a24168.myapplication.kitchen.find.entity.Show;

import org.w3c.dom.Text;

import java.util.List;

public class GridViewAdapter extends BaseAdapter {
    private Context context;
    private List<Show> dataSource;
    private LayoutInflater inflater;

    Bitmap bitmap;

    public Bitmap getBitmap() {
        return bitmap;
    }

    public GridViewAdapter(Context context, List<Show> dataSource) {
        super();
        this.context = context;
        this.dataSource = dataSource;
    }

    @Override
    public int getCount() {
        return dataSource.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        RequestListener mRequestListener = new RequestListener() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target target, boolean isFirstResource) {
                Log.d("abc", "onException: " + e.toString()+"  model:"+model+" isFirstResource: "+isFirstResource+"shibaile");
                return false;
            }

            @Override
            public boolean onResourceReady(Object resource, Object model, Target target, DataSource dataSource, boolean isFirstResource) {
                Log.e("abc",  "model:"+model+" isFirstResource: "+isFirstResource+"haole");
                return false;
            }
        };
        ViewHolder viewHolder = null;
        if (convertView == null) {
            inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.find_grid_item,null);
            viewHolder = new ViewHolder();

            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.imageView);
            viewHolder.textView = (TextView) convertView.findViewById(R.id.textView);
            viewHolder.touxiang = (ImageView) convertView.findViewById(R.id.yonghutouxiang);
            viewHolder.nicheng = (TextView) convertView.findViewById(R.id.yonghunicheng);
            viewHolder.dianzan = convertView.findViewById(R.id.dianzan);


            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String image = dataSource.get(position).getImage();
        Glide.with(context).load(image)
                .apply( new RequestOptions().error(new ColorDrawable(Color.BLUE))).listener(mRequestListener).into(viewHolder.imageView);
        String title = dataSource.get(position).getTitle();
        viewHolder.textView.setText(title);
        String image1 = dataSource.get(position).getTouxiang();
        Glide.with(context).load(image1)
                .apply( new RequestOptions().error(new ColorDrawable(Color.BLUE))).listener(mRequestListener).into(viewHolder.touxiang);
        String mingzi = dataSource.get(position).getNicheng();
        viewHolder.nicheng.setText(mingzi);


        ViewHolder finalViewHolder = viewHolder;
        viewHolder.dianzan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("listener","点了");
                if (finalViewHolder.dianzan.getDrawable().getCurrent().getConstantState().equals(context.getResources().getDrawable(R.drawable.dianzan2).getConstantState())){
                    finalViewHolder.dianzan.setImageResource(R.drawable.dianzan1);
                }else{
                    finalViewHolder.dianzan.setImageResource(R.drawable.dianzan2);
                }
                bitmap = ((BitmapDrawable)finalViewHolder.dianzan.getDrawable()).getBitmap();
            }
        });

        return convertView;
    }

    class ViewHolder {
        public ImageView imageView;
        public TextView textView;
        public ImageView touxiang;
        public TextView nicheng;
        public ImageView dianzan;
    }


}
