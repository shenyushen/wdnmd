package com.example.a24168.myapplication.kitchen.find.unimportant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.a24168.myapplication.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.a24168.myapplication.kitchen.find.unimportant.Create.textleixing;

public class FindCreateListView extends AppCompatActivity  {
    private ListView listView;
    private String[] name = { "早餐", "晚餐", "午餐", "甜点","饮料","火锅","泡面"};
    private int[] imageids = {R.drawable.zaocan,R.drawable.wancan,R.drawable.wucan,R.drawable.tiandian,R.drawable.yinliao,R.drawable.huoguo,R.drawable.paomian};
    public static int lableid;
    public static String lablename;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_createlist);
        listView = findViewById(R.id.laablelistview);
        List<Map<String, Object>> listems = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < name.length; i++) {
            Map<String, Object> listem = new HashMap<String, Object>();
            listem.put("text", name[i]);
            listem.put("image",imageids[i]);
            listems.add(listem);
        }
        SimpleAdapter simplead = new SimpleAdapter(this, listems,
                R.layout.find_createlist_item, new String[] {"text","image"},
                new int[] {R.id.textitem,R.id.imageitem});

        listView.setAdapter(simplead);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                lableid = position+1;
                lablename = listems.get(position).get("text").toString();
                textleixing.setText("#"+lablename+"#");
                FindCreateListView.this.finish();
            }
        });
    }
}
