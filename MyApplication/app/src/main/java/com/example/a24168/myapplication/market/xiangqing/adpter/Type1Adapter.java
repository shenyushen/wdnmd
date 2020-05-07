package com.example.a24168.myapplication.market.xiangqing.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.a24168.myapplication.R;
import com.example.a24168.myapplication.market.entity.Type;
import com.example.a24168.myapplication.market.xiangqing.entity.Type1;

import java.util.List;

public class Type1Adapter  extends BaseAdapter {
    private List<Type1> list;
    private Context context;
    private int id;

    public Type1Adapter(List<Type1> list, Context context, int id) {
        this.list = list;
        this.context = context;
        this.id = id;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(null == convertView){
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(id,null);
        }
        TextView button = convertView.findViewById(R.id.button1);
        button.setText(list.get(position).getType());




        return convertView;
    }
}
