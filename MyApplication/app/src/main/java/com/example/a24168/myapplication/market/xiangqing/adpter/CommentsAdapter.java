package com.example.a24168.myapplication.market.xiangqing.adpter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.a24168.myapplication.R;
import com.example.a24168.myapplication.market.CommentXiangqing;
import com.example.a24168.myapplication.market.xiangqing.entity.Comments;

import java.util.List;

import static com.example.a24168.myapplication.market.CommentXiangqing.commentss;
import static com.example.a24168.myapplication.market.xiangqing.XiangQing.context;
import static com.example.a24168.myapplication.market.xiangqing.XiangQing.textView10;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.ViewHolder> {
    private List<Comments> commentsList;
    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView; //评论图
        ImageView imageView1; //头像
        TextView textView; //名字
        TextView textView1; // 评论内容
        LinearLayout linearLayout;
        RatingBar ratingBar;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.a1);
            textView = itemView.findViewById(R.id.a2);
            textView1 = itemView.findViewById(R.id.a3);
            imageView1 = itemView.findViewById(R.id.icon);
            linearLayout = itemView.findViewById(R.id.cc);
            ratingBar = itemView.findViewById(R.id.r);
        }
    }
    public CommentsAdapter(List<Comments> comments){
        commentsList = comments;
    }
    @NonNull
    @Override
    public CommentsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //动态加载布局
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.comments_item,viewGroup,false);
        //创建ViewHolder实例，参数为刚加载进来的子项布局
        ViewHolder viewHolder=new ViewHolder(view);//这样，子项布局里面的控件就缓存到了ViewHolder

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull CommentsAdapter.ViewHolder viewHolder, int i) {

        Comments comments = commentsList.get(i);
        String img = comments.getImg().split(",")[0];
        Glide.with(viewHolder.itemView.getContext()).load(viewHolder.itemView.getContext().getResources().getString(R.string.ip1)+"/upload/"+
                img).into(viewHolder.imageView);
        Glide.with(viewHolder.itemView.getContext()).load(viewHolder.itemView.getContext().getResources().getString(R.string.ip1)+"/pic/"+
                comments.getUser().getPhoto()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(viewHolder.imageView1);
        viewHolder.textView.setText(comments.getUser().getUsername());
        viewHolder.textView1.setText(comments.getContent());
        textView10.setText(getItemCount()+"个评价");
        viewHolder.ratingBar.setRating(Float.parseFloat(comments.getR1()));
        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commentss = comments;
                Intent intent = new Intent();
                intent.setClass(context, CommentXiangqing.class);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return commentsList.size();
    }
}
