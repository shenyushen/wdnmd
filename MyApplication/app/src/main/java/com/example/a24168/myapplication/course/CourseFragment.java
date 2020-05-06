package com.example.a24168.myapplication.course;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.a24168.myapplication.R;
import com.example.a24168.myapplication.course.entity.course;
import com.example.a24168.myapplication.course.until.CustomAdapter;
import com.example.a24168.myapplication.course.videoControl.videoPlayActivity;

import java.util.ArrayList;

public class CourseFragment extends Fragment  {
     View view;
     Button course_label_btn_text_1;
    //获取控件
    private void getViews() {
        course_label_btn_text_1=view.findViewById(R.id.course_label_btn_text_1);
    }
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null){
            view = inflater.inflate(R.layout.course, container, false);
        }
        getViews();
        a();
        return view;
    }
    public void a(){
        //listview
        ListView listView = view.findViewById(R.id.lv_persons1);
        ArrayList<course> info=new ArrayList<>();
        info.add(new course(1,"2012-5-10","测试1","一些详细的描述1","111","up:sion"));
        info.add(new course(2,"2012-5-10","测试2","一些详细的描述2","111","up:sion"));
        info.add(new course(3,"2012-5-10","测试3","一些详细的描述3","11","up:sion"));
        info.add(new course(4,"2012-5-10","测试4","一些详细的描述4","11","up:sion"));
        info.add(new course(5,"2012-5-10","测试5","一些详细的描述5","11","up:sion"));
        info.add(new course(6,"2012-5-10","测试6","一些详细的描述3","11","up:sion"));
        info.add(new course(7,"2012-5-10","测试7","一些详细的描述4","11","up:sion"));
        info.add(new course(8,"2012-5-10","测试8","一些无关紧要的描述5","11","up:sion"));
        CustomAdapter custtomAdapter1 = new CustomAdapter(this.getActivity(), info, R.layout.course_list_item_view_layout);
        listView.setAdapter(custtomAdapter1);
        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Log.e(position+"","test");
                        Intent i=new Intent(getActivity(), videoPlayActivity.class);
                        startActivity(i);
                    }
                }
        );

    }
}
