package com.example.a24168.myapplication.kitchen.find.adapter;

/*
 *Create By 小群子    2018/12/10
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.a24168.myapplication.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoadPicAdapter extends RecyclerView.Adapter<LoadPicAdapter.MyViewHolder> {

    Context context;
    List<LoadFileVo> fileList = null;
    View view;
    int picNum = 8;//列表的图片个数最大值

    public LoadPicAdapter(Context context, List<LoadFileVo> fileList) {
        this.context = context;
        this.fileList = fileList;
    }

    public LoadPicAdapter(Context context, List<LoadFileVo> fileList, int picNum) {
        this.context = context;
        this.fileList = fileList;
        this.picNum = picNum;
    }

    public interface OnItemClickListener {
        void click(View view, int positon);

        void del(View view);
    }

    OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        view = LayoutInflater.from(context).inflate(R.layout.findload_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        //通过默认设置第一个为空文件为添加退保，且在文件个数小于最大限制值的情况。当图片个数等于最大限制值，第一个则不是添加按钮
        if (position == 0&&fileList.get(position).getBitmap()==null) {
            holder.ivPic.setImageResource(R.drawable.tupianshangchuan);//加号图片
            holder.ivPic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.click(view, position);
                }
            });
            holder.ivDel.setVisibility(View.INVISIBLE);
            holder.bg_progressbar.setVisibility(View.GONE);

        } else {
//            Uri uri = Uri.parse(fileList.get(position).getFile().getPath());
//            holder.ivPic.setImageURI(uri);

            holder.ivPic.setImageBitmap(fileList.get(position).getBitmap());
            //使用压缩后的图片进行填充到界面上


            holder.ivDel.setVisibility(View.VISIBLE);
            holder.bg_progressbar.setVisibility(View.VISIBLE);
            holder.bg_progressbar.setProgress(fileList.get(position).getPg());
        }


        holder.ivDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //判断图片是否上传，上传后将无法删除
                if (fileList.get(position).isUpload()) {
                    Toast.makeText(context, "该图片已上传！", Toast.LENGTH_SHORT).show();
                } else {
                    fileList.remove(position);
                    if (fileList.size()==picNum-1&&fileList.get(0).getBitmap()!=null){
                        fileList.add(0,new LoadFileVo());
                    }//如果数量达到最大数时，前面的加号去掉，然后再减去时，则添加前面的加号
                    notifyDataSetChanged();
                    if (listener!=null){
                        listener.del(view);//传递接口，计算图片个数显示在界面中
                    }

                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return fileList.size();
    }


    static class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ivPic)
        ImageView ivPic;
        @BindView(R.id.ivDel)
        ImageView ivDel;
        @BindView(R.id.bg_progressbar)
        ProgressBar bg_progressbar;

        View view;


        MyViewHolder(View view) {
            super(view);
            this.view = view;
            ButterKnife.bind(this, view);
        }
    }
}
