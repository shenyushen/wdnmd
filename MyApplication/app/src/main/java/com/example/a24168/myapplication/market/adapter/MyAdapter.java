package com.example.a24168.myapplication.market.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.a24168.myapplication.R;
import com.example.a24168.myapplication.kitchen.like.Like;
import com.example.a24168.myapplication.market.banner.MarketBanner;
import com.example.a24168.myapplication.market.entity.Banner;
import com.example.a24168.myapplication.market.entity.Good;
import com.example.a24168.myapplication.market.entity.MarketClassic;
import com.example.a24168.myapplication.market.entity.MarketComment;
import com.example.a24168.myapplication.market.entity.MarketLike;
import com.example.a24168.myapplication.market.entity.MarketNew;
import com.example.a24168.myapplication.market.entity.Type;
import com.example.a24168.myapplication.market.holder.BannerViewHolder;
import com.example.a24168.myapplication.market.holder.MarketClassicViewHolder;
import com.example.a24168.myapplication.market.holder.MarketCommentViewHolder;
import com.example.a24168.myapplication.market.holder.MarketLikeViewHolder;
import com.example.a24168.myapplication.market.holder.MarketNewViewHolder;
import com.example.a24168.myapplication.market.holder.TypeViewHolder;
import com.example.a24168.myapplication.market.like.LikeAdapter;
import com.example.a24168.myapplication.market.sort.AllSort;
import com.example.a24168.myapplication.market.sort.Goods;
import com.example.a24168.myapplication.market.xiangqing.XiangQing;

import java.util.List;

import static com.example.a24168.myapplication.market.MarketFragment.fragment;
import static com.example.a24168.myapplication.market.sort.Goods.s_id;

public class MyAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<List> lists;
    private Context context;
    private LayoutInflater layoutInflater;
    private LinearLayout mMoviceType;
    private LayoutInflater mInflater;
    private static final int type = 0;  // 头部分类
    private static final int banner = 1; //爱尝鲜
    private static final int market_new = 2; // 市集上新
    private static final int market_classic = 30; //市集经典
    private static final int market_comment = 3; //@xxx 区
    private static final int market_like = 5; // 猜你喜欢

    public MyAdapter(List<List> lists) {
        this.lists = lists;

    }

    @Override
    public int getItemViewType(int position) {
        Object o = lists.get(position).get(0);
        Log.e("1", o + "");
        if (o instanceof Type)
            return type;
        if (o instanceof Banner)
            return banner;
        if (o instanceof MarketNew)
            return market_new;
        if (o instanceof MarketClassic)
            return market_classic;
        if (o instanceof MarketComment)
            return market_comment;
        if (o instanceof MarketLike)
            return market_like;

        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (context == null)
            context = viewGroup.getContext();
        if (layoutInflater == null)
            layoutInflater = LayoutInflater.from(context);
        View view;
        switch (i) {
            case type: {
                // 分类
                view = layoutInflater.inflate(R.layout.market_recy, viewGroup, false);
                return new TypeViewHolder(view);
            }
            case banner: {
                // 中部图片
                view = layoutInflater.inflate(R.layout.market_banner, viewGroup, false);

                return new BannerViewHolder(view);
            }
            case market_new: {
                // 市集上新
                view = layoutInflater.inflate(R.layout.market_new, viewGroup, false);
                return new MarketNewViewHolder(view);
            }
            case market_classic: {
                // 市集经典
                view = layoutInflater.inflate(R.layout.market_classic, viewGroup, false);
                return new MarketClassicViewHolder(view);
            }
            case market_comment: {
                // 市集 @ xxxx
                view = layoutInflater.inflate(R.layout.market_comment, viewGroup, false);
                return new MarketCommentViewHolder(view);
            }
            case market_like: {
                // 猜你喜欢
                view = layoutInflater.inflate(R.layout.market_like, viewGroup, false);
                return new MarketLikeViewHolder(view);
            }

        }

    return null;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof TypeViewHolder) {
            // 市集分类
            final List<Type> list1 = lists.get(i);

            GridView recyclerView = ((TypeViewHolder) viewHolder).recyclerView;
            Adapter adapter = new Adapter(list1, context, R.layout.market_item);

            recyclerView.setAdapter(adapter);
        }
        if (viewHolder instanceof BannerViewHolder) {
            // 市集中部
            final List<Banner> list = lists.get(i);
            ImageView banner = ((BannerViewHolder) viewHolder).imageView;
            banner.setImageResource(list.get(0).getPic());
            banner.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setClass(fragment, MarketBanner.class);
                    fragment.startActivity(intent);
                    fragment.overridePendingTransition(R.anim.anim_in_right, R.anim.anim_out_left);
                }
            });
        }

        if (viewHolder instanceof MarketClassicViewHolder) {
            //  市集经典


        }
        if (viewHolder instanceof MarketNewViewHolder) {
            // 市集上新
            final List<MarketNew> list1 = lists.get(i);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

            MarketNewAdapter marketNewAdapter = new MarketNewAdapter(list1);
            ((MarketNewViewHolder) viewHolder).recyclerView.setLayoutManager(linearLayoutManager);
            ((MarketNewViewHolder) viewHolder).recyclerView.setAdapter(marketNewAdapter);
            /*
            HorizontalScrollView marketnew = ((MarketNewViewHolder) viewHolder).horizontalScrollView;
            mMoviceType = marketnew.findViewById(R.id.marketnew_ll);
            mInflater = LayoutInflater.from(this.context);
            for (int ii = 0; ii < list1.size(); ii++) {
                final View view = mInflater.inflate(R.layout.market_like_item, mMoviceType, false);
                LinearLayout linearLayout = view.findViewById(R.id.lin1);
                ImageView imageView = view.findViewById(R.id.img5);
                TextView textView = view.findViewById(R.id.text10);
                TextView textView1 = view.findViewById(R.id.text11);
                TextView textView2 = view.findViewById(R.id.text12);
                TextView textView3 = view.findViewById(R.id.text13);
                Glide.with(context).load(context.getResources().getString(R.string.ip1)+"/upload/"+list1.get(ii).getImg()).into(imageView);
                Log.e("123",list1.get(ii).getTitle());
                textView.setText(list1.get(ii).getTitle());
                textView1.setText(list1.get(ii).getLittleContent());
                textView2.setText("￥"+list1.get(ii).getPrice()+"");
                textView3.setText("已销"+list1.get(ii).getSaleVolume());
                linearLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                mMoviceType.addView(view);

            }*/

        }
            if (viewHolder instanceof MarketCommentViewHolder) {
                // 市集 @xxx
                final List<MarketComment> list2 = lists.get(i);
                ListView View = ((MarketCommentViewHolder) viewHolder).View;
                MarketComment_Adapter adapter = new MarketComment_Adapter(list2, context, R.layout.market_comment_item);
                View.setAdapter(adapter);
            }

            if (viewHolder instanceof MarketLikeViewHolder) {
                //猜你喜欢
                final List<MarketLike> list = lists.get(i);

                GridView gridView = ((MarketLikeViewHolder) viewHolder).gridView;
                LikeAdapter likeAdapter = new LikeAdapter(list, context, R.layout.market_like_item);
                gridView.setAdapter(likeAdapter);
                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        s_id = list.get(position).getGoodsId()+"";
                        Intent  intent = new Intent();
                        intent.setClass(fragment, XiangQing.class);
                        context.startActivity(intent);
                        fragment.overridePendingTransition(R.anim.anim_in_right,R.anim.anim_out_left);
                    }
                });
            }


        }



}
