package com.example.a24168.myapplication.course.until;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a24168.myapplication.R;
import com.example.a24168.myapplication.course.entity.course;

import java.util.ArrayList;
import java.util.List;

/**
 * @author SIONON
 * @description:
 * @date :2020/5/1 20:21
 */
    public class CustomAdapter extends BaseAdapter {
        private List<course> texts = new ArrayList<>();
        private int itemLayoutId;
        private Context context;
        private   ArrayList<Integer> drawables=new ArrayList<>();
        public CustomAdapter(Context context, List<course> texts, int itemLayoutId){

            this.context = context;
            this.texts = texts;
            this.itemLayoutId = itemLayoutId;
        }
        @Override


        public int getCount() {
            if (null != texts) {
                return texts.size();
            }else {
                return 0;
            }
        }

        @Override

        public Object getItem(int position) {
            if (null != texts) {
                return texts.get(position);
            }else {
                return null;
            }
        }

        @Override

        public long getItemId(int position) {
            return position;
        }

        @Override

        public View getView(final int position, View convertView, ViewGroup parent) {
            drawables.add(R.drawable.course_cha);
            drawables.add(R.drawable.course_chaofan);
            drawables.add(R.drawable.course_dangao);
            drawables.add(R.drawable.course_dangao2);
            drawables.add(R.drawable.course_huage);
            drawables.add(R.drawable.course_jiamo);
            drawables.add(R.drawable.course_lajiao);
            drawables.add(R.drawable.course_mifan);
            drawables.add(R.drawable.course_pizza);
            drawables.add(R.drawable.course_roupian);
            if (null == convertView) {
                LayoutInflater inflater = LayoutInflater.from(context);
                convertView = inflater.inflate(itemLayoutId, null);
            }
            ImageView imageView=convertView.findViewById(R.id.list_item_image_1);
            imageView.setImageResource(drawables.get(Integer.valueOf(texts.get(position).getCourse_id())));
            System.out.println(R.drawable.mi+"               abcde");
            TextView mv_title = convertView.findViewById(R.id.list_item_title);
            TextView mv_time=convertView.findViewById(R.id.list_item_time);
            TextView mv_content = convertView.findViewById(R.id.list_item_content);
            TextView mv_up = convertView.findViewById(R.id.list_item_userName);
         /*   mv_title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i=new Intent(CourseFragment.getActivity(), videoPlayActivity.class);
                    startActivity(i);
                }
            });*/
            mv_title.setText(texts.get(position).getMv_name());
            mv_content.setText(texts.get(position).getMv_account());
            mv_time.setText(texts.get(position).getTime());
            mv_up.setText(texts.get(position).getMv_up());
            return convertView;
        }
    }
