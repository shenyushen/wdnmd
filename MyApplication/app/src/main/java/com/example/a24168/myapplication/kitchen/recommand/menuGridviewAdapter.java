package com.example.a24168.myapplication.kitchen.recommand;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.a24168.myapplication.R;
import com.example.a24168.myapplication.entity.label;
import com.example.a24168.myapplication.entity.menu;
import static com.example.a24168.myapplication.kitchen.recommand.Recommand.context;
import java.util.List;
/*此文件为浏览的构造器，调用者为recommand*/
public class menuGridviewAdapter extends BaseAdapter {

//    准备的空间
    private ImageView imageView;
    private TextView menu_nametextview;
//    准备的数据
    private  List<menu> menus;
    private LayoutInflater layoutInflater;

    public menuGridviewAdapter(Context context, List<menu> menus) {
        this.menus = menus;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return menus.size();
    }

    @Override
    public Object getItem(int position) {
        return menus.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.e("运行","view");
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.menu_list_gridview_adapter, null);
        }
        Log.e("position",position+"");
//        if (position==1){
//            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
//                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//            params.setMargins(20,0,0,20);
//            Log.e("运行","s");
//            convertView.setLayoutParams(params);
//        }
            menu menu = menus.get(position);
            menu_nametextview = convertView.findViewById(R.id.menu_name);
            imageView=convertView.findViewById(R.id.menu_photo);
//            grlide加载图片
            Glide.with(context)
                    .load(context.getResources().getString(R.string.photoip)+menus.get(position).getMenu_photo())
                    .into(imageView);
            if (menu != null) {
                String sd="";
                for(label l:menu.getLabels()){
                     sd+=l.getLabel_name()+" ";
                }
                menu_nametextview.setText(menu.getMenu_name()+" 口味："+sd);
            }

        return convertView;
    }
}
