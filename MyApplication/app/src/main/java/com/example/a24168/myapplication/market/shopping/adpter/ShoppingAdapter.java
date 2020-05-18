package com.example.a24168.myapplication.market.shopping.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.a24168.myapplication.R;
import com.example.a24168.myapplication.market.shopping.entity.Order;

import java.util.List;

public class ShoppingAdapter extends BaseAdapter {
    private AOnClickListener mOnClickListener;
    private float Allprice;
    private TextView tv_show_price ;
    private CheckBox checkBox;
    private boolean isShow = true;//是否显示编辑/完成
    private List<Order> list;
    private Context context;
    private int id;

    public ShoppingAdapter(List<Order> list, Context context, int id) {
        this.list = list;
        this.context = context;
        this.id = id;
        this.mOnClickListener = (AOnClickListener) context ;
    }



    public interface AOnClickListener {
        //checkBox不选中
       public void jianNum(float num);
       //checkBox选中
       public void jiaNum(float num);
       //count增加
       public void chang(String count, String goods_id);
       //count减少
        public void chang2(String count, String goods_id);
        //item删除
        public void del(String goods_id);
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
        ImageView imageView = convertView.findViewById(R.id.market_shopping_pic);
        TextView textView = convertView.findViewById(R.id.tv_commodity_name);
        TextView textView1 = convertView.findViewById(R.id.tv_commodity_attr);
        TextView textPrice = convertView.findViewById(R.id.tv_commodity_price);
        TextView textNum = convertView.findViewById(R.id.tv_commodity_show_num);
        //-
        TextView textjianhao = convertView.findViewById(R.id.iv_sub);
        //+
        TextView textjiahao = convertView.findViewById(R.id.iv_add);
        ImageView del = convertView.findViewById(R.id.tv_commodity_delete);

        checkBox = convertView.findViewById(R.id.ck_chose);
        checkBox.setChecked(list.get(position).getChecked());

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnClickListener.del(list.get(position).getGoodsId());
            }
        });
        textjiahao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnClickListener.chang(list.get(position).getGoodsCount(),list.get(position).getGoodsId());
            }
        });

        textjianhao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Integer.parseInt(list.get(position).getGoodsCount())>1) {
                    mOnClickListener.chang2(list.get(position).getGoodsCount(),list.get(position).getGoodsId());
                }
            }
        });

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (list.get(position).getChecked()) {
//                    float now =Float.parseFloat(tv_show_price.getText().toString().substring(3))-Float.parseFloat(list.get(position).getGoodsPrice().substring(1))*Integer.parseInt(list.get(position).getGoodsCount());
//                    Log.e("lzy12",""+now);
                    mOnClickListener.jianNum(Float.parseFloat(list.get(position).getGoodsPrice().substring(1))*Integer.parseInt(list.get(position).getGoodsCount()));
                    checkBox.setChecked(false);
                    list.get(position).setChecked(false);
                    notifyDataSetChanged();

                } else {
//                    float now =Float.parseFloat(tv_show_price.getText().toString().substring(3))+Float.parseFloat(list.get(position).getGoodsPrice().substring(1))*Integer.parseInt(list.get(position).getGoodsCount());
//                    Log.e("lzy12",""+now);
//                    tv_show_price.setText("合计："+now);

                    mOnClickListener.jiaNum(Float.parseFloat(list.get(position).getGoodsPrice().substring(1))*Integer.parseInt(list.get(position).getGoodsCount()));
                    checkBox.setChecked(true);
                    list.get(position).setChecked(true);
                    notifyDataSetChanged();
                }
            }
        });






        Glide.with(convertView.getContext()).load(convertView.getContext().getResources().getString(R.string.ip1)+"/upload/"+
                list.get(position).getGoods().getImg()).into(imageView);

        textView.setText(list.get(position).getGoodsContent());
        textView1.setText(list.get(position).getGoodsType());
        textPrice.setText(""+list.get(position).getGoodsPrice());
        textNum.setText(""+list.get(position).getGoodsCount());
        return convertView;
    }
}
