package com.example.a24168.myapplication.market.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.a24168.myapplication.R;
import com.example.a24168.myapplication.market.entity.Type;
import com.example.a24168.myapplication.market.sort.AllSort;
import com.example.a24168.myapplication.market.sort.Goods;

import java.util.List;

import static com.example.a24168.myapplication.market.MarketFragment.fragment;

public class AllSortAdapter extends BaseAdapter {
    private List<Type> list;
    private Context context;
    private int id;

    public AllSortAdapter(List<Type> list, Context context, int id) {
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
        ImageView imageView = convertView.findViewById(R.id.img1);
        TextView textView = convertView.findViewById(R.id.text1);

        textView.setText(list.get(position).getType());
        imageView.setImageResource(list.get(position).getImg());

        RelativeLayout relativeLayout = convertView.findViewById(R.id.rela1);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(list.get(position).getType(),list.get(position).getId()+"");
                Intent  intent = new Intent();
                intent.setClass(fragment, Goods.class);
                intent.putExtra("name",list.get(position).getType());
                intent.putExtra("id",list.get(position).getId()+"");
                context.startActivity(intent);
                fragment.overridePendingTransition(R.anim.anim_in_right,R.anim.anim_out_left);

            }
        });


        return convertView;
    }


}
