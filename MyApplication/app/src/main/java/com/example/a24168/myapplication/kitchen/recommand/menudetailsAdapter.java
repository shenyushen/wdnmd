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
import com.bumptech.glide.request.RequestOptions;
import com.example.a24168.myapplication.R;

import static com.example.a24168.myapplication.kitchen.recommand.Recommand.context;

/*此文件为adapter，作用为填写菜单的教学步骤
* */
public class menudetailsAdapter extends BaseAdapter {
    private  String[] steps;
    private ImageView stepsphoto;
    private TextView stepnum;
    private TextView stepcontext;
    private LayoutInflater layoutInflater;
//    zidingyi

    public menudetailsAdapter(Context context, String[] steps) {
        this.steps = steps;
        layoutInflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return steps.length;
    }

    @Override
    public Object getItem(int i) {
        return steps[i];
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.menudetailsitem, null);
            if (steps != null) {
                stepsphoto=convertView.findViewById(R.id.stepphoto);
                stepnum=convertView.findViewById(R.id.stepnum);
                stepcontext=convertView.findViewById(R.id.stepcontext);
                stepnum.setText("步骤"+(i+1));
                stepcontext.setText(steps[i].trim().split("'")[3]);
                RequestOptions options = new RequestOptions();
                options.placeholder(R.drawable.jiazai);
                Glide.with(context)
                        .load(context.getResources().getString(R.string.photoip)+steps[i].trim().split("'")[4])
                        .apply(options)
                        .into(stepsphoto);
            }
        }
        return convertView;
    }
}
