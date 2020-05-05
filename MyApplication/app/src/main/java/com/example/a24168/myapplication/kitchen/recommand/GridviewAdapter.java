package com.example.a24168.myapplication.kitchen.recommand;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.a24168.myapplication.R;

import java.util.ArrayList;
import java.util.List;
/*此页面为选择筛选的构造器*/
public class GridviewAdapter extends BaseAdapter {

    List<String> list = new ArrayList<>();//用来存放选中属性的集合
    private List<Classify> data;

    Context context;

    public GridviewAdapter(List<Classify> data, Context context) {
        this.data = data;
        this.context=context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder holder;
        if (convertView == null) {
            holder = new MyViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.gridview_item, null);
            //利用view对象，找到布局中的组件
            holder.mGridContent = (TextView) convertView.findViewById(R.id.tv_content);
            convertView.setTag(holder);
        } else {
            holder = (MyViewHolder) convertView.getTag();
        }
        if (data != null && data.size() > 0) {
            holder.mGridContent.setText(data.get(position).getStr());
            if (data.get(position).isChecked()) {
                holder.mGridContent.setBackgroundResource(R.drawable.rect_shaixuan2);
                holder.mGridContent.setTextColor(Color.parseColor("#FF4500"));
                list.add(data.get(position) + "");
            } else {
                holder.mGridContent.setBackgroundResource(R.drawable.rect_shaixuan);
                holder.mGridContent.setTextColor(Color.BLACK);
                list.remove(data.get(position) + "");
            }
        }
        return convertView;
    }

    class MyViewHolder {
        public TextView mGridContent;
    }


    public List getSaveList(){

        return list;
    }
}
