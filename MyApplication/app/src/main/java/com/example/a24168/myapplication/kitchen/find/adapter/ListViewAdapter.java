package com.example.a24168.myapplication.kitchen.find.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.a24168.myapplication.R;
import com.example.a24168.myapplication.kitchen.find.entity.FindComment;

import java.util.ArrayList;
import java.util.List;

public class ListViewAdapter extends BaseAdapter {
    Context context;
    List<FindComment> findCommentList ;
    private LayoutInflater inflater;

    public ListViewAdapter() {
    }

    public ListViewAdapter(Context context, List<FindComment> findCommentList) {
        this.context = context;
        this.findCommentList = findCommentList;
    }

    @Override
    public int getCount() {
        return findCommentList == null?0:findCommentList.size();
    }

    @Override
    public Object getItem(int position) {
        return findCommentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        if (convertView == null) {
            inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.find_listview_item,null);
            viewHolder = new ViewHolder();
            viewHolder.commentImage = convertView.findViewById(R.id.commenttouxiang);
            viewHolder.commentText = convertView.findViewById(R.id.commentnicheng);
            viewHolder.comment_comment = convertView.findViewById(R.id.comment_comment);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //Log.e("comments",findCommentList.get(position).getComment());

        String image = "http://10.0.2.2:8080/shixun3/pic/"+findCommentList.get(position).getUser().getPhoto();
        Glide.with(context).load(image)
                .apply( new RequestOptions().error(new ColorDrawable(Color.BLUE))).into(viewHolder.commentImage);
        viewHolder.commentText.setText(findCommentList.get(position).getUser().getUsername());
        viewHolder.comment_comment.setText(findCommentList.get(position).getComment());
        return convertView;
    }
    private class ViewHolder {
        public ImageView commentImage;
        public TextView commentText;
        public TextView comment_comment;
    }
}
