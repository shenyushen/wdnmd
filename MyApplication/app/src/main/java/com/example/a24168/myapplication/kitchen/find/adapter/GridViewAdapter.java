package com.example.a24168.myapplication.kitchen.find.adapter;

import android.content.Context;

import android.graphics.Color;
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

import java.util.List;

public class GridViewAdapter extends BaseAdapter {
    private Context context;
    private List<Show> dataSource;
    private LayoutInflater inflater;


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
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String image = dataSource.get(position).getImage();
        Glide.with(context).load(image)
                .apply( new RequestOptions().error(new ColorDrawable(Color.BLUE))).listener(mRequestListener).into(viewHolder.imageView);
        String title = dataSource.get(position).getTitle();
        viewHolder.textView.setText(title);

        return convertView;
    }

    static class ViewHolder {
        public ImageView imageView;
        public TextView textView;
    }

}
