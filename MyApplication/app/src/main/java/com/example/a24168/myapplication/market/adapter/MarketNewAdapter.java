package com.example.a24168.myapplication.market.adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import com.example.a24168.myapplication.R;

import com.example.a24168.myapplication.market.entity.MarketNew;
import com.example.a24168.myapplication.market.sort.Goods;
import com.example.a24168.myapplication.market.xiangqing.XiangQing;
import static com.example.a24168.myapplication.market.MarketFragment.fragment;
import static com.example.a24168.myapplication.market.sort.Goods.s_id;
import java.util.List;

public class MarketNewAdapter extends RecyclerView.Adapter<MarketNewAdapter.ViewHolder> {
    public List<MarketNew> list;

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;
        TextView textView1;
        TextView textView2;
        TextView textView3 ;
        LinearLayout linearLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img51);
            textView = itemView.findViewById(R.id.text101);
            textView1 = itemView.findViewById(R.id.text111);
            textView2 = itemView.findViewById(R.id.text121);
             textView3 = itemView.findViewById(R.id.text131);
             linearLayout = itemView.findViewById(R.id.lin11);
        }
    }

    public MarketNewAdapter(List<MarketNew> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.market_new_item,viewGroup,false);
        //创建ViewHolder实例，参数为刚加载进来的子项布局
       ViewHolder viewHolder = new ViewHolder(view);//这样，子项布局里面的控件就缓存到了ViewHolder

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        MarketNew marketNew = list.get(i);
        Log.e("111","ok");
        Glide.with(viewHolder.itemView.getContext()).load(viewHolder.itemView.getContext().getResources().getString(R.string.ip1)+"/upload/"+marketNew.getImg())
                .into(viewHolder.imageView);
        viewHolder.textView.setText(marketNew.getTitle());
        viewHolder.textView1.setText(marketNew.getLittleContent());
        viewHolder.textView2.setText("￥"+marketNew.getPrice()+"");
        viewHolder.textView3.setText("已销"+marketNew.getSaleVolume());
        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s_id = marketNew.getGoodsId()+"";
                Intent intent = new Intent();
                intent.setClass(fragment, XiangQing.class);
                fragment.startActivity(intent);
                fragment.overridePendingTransition(R.anim.anim_in_right,R.anim.anim_out_left);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
