package com.example.a24168.myapplication.kitchen;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.example.a24168.myapplication.R;
import com.example.a24168.myapplication.kitchen.find.Find;
import com.example.a24168.myapplication.kitchen.like.Like;
import com.example.a24168.myapplication.kitchen.recommand.Recommand;

import java.util.ArrayList;
import java.util.List;

import static com.example.a24168.myapplication.main.MainActivity.findeditText;
import static com.example.a24168.myapplication.main.MainActivity.linearLayout;


public class KitchenFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private List<Fragment> list;
    private MyAdapter adapter;
    private String[] titles = {"关注", "推荐", "发现"};
    View view;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.kitchen, container,false);
        }

        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        tabLayout = (TabLayout) view.findViewById(R.id.tablayout);
        //页面，数据源
        list = new ArrayList<>();
        list.add(new Like());
        list.add(new Recommand());
        list.add(new Find());
        //ViewPager的适配器
        adapter = new MyAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setCurrentItem(1);
        //绑定
        tabLayout.setupWithViewPager(viewPager);
        linearLayout.setVisibility(View.GONE);
        findeditText.setVisibility(View.VISIBLE);
        findeditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_UNSPECIFIED) {
                    switch (keyEvent.getAction()) {
                        case KeyEvent.ACTION_UP:
                            String find=findeditText.getText().toString();
                            Recommand recommand= (Recommand) adapter.getItem(viewPager.getCurrentItem());
                            recommand.inittext("findbyfind?menuname="+find+"&type="+viewPager.getCurrentItem());
                        default:
                            //隐藏软键盘
                            InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(getActivity().getWindow().getDecorView().getWindowToken(), 0);
                    }
                }
                return (keyEvent.getKeyCode()== KeyEvent.KEYCODE_ENTER);
            }
        });
        return view;
    }
    class MyAdapter extends FragmentStatePagerAdapter {
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public int getItemPosition(Object object) {//最主要就是加了这个方法。
            return POSITION_NONE;
        }
        //重写这个方法，将设置每个Tab的标题
        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }

}
