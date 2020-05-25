package com.example.a24168.myapplication.course.videoControl;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.loadmore.SimpleLoadMoreView;
import com.example.a24168.myapplication.R;
import com.example.a24168.myapplication.course.commentDiaolg.bean.FirstLevelBean;
import com.example.a24168.myapplication.course.commentDiaolg.bean.SecondLevelBean;
import com.example.a24168.myapplication.course.commentDiaolg.dialog.InputTextMsgDialog;
import com.example.a24168.myapplication.course.commentDiaolg.single.CommentDialogSingleAdapter;
import com.example.a24168.myapplication.course.commentDiaolg.util.RecyclerViewUtil;
import com.example.a24168.myapplication.course.commentDiaolg.widget.VerticalCommentLayout;
import com.example.a24168.myapplication.course.entity.comment;

import java.util.ArrayList;
import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

/**
 * @author SIONON
 * @description:
 * @date :2020/5/5 13:57
 */
public class videoPlayActivity extends AppCompatActivity implements VerticalCommentLayout.CommentItemClickListener, BaseQuickAdapter.RequestLoadMoreListener{
    JCVideoPlayer videoController1;
    private List<FirstLevelBean> data = new ArrayList<>();
    private BottomSheetDialog bottomSheetDialog;
    private InputTextMsgDialog inputTextMsgDialog;
    private float slideOffset = 0;
    String content ="";
    private CommentDialogSingleAdapter bottomSheetAdapter;
    private RecyclerView rv_dialog_lists;
    private long totalCount = 30;//总条数不得超过它
    private int offsetY;
    private RecyclerViewUtil mRecyclerViewUtil;
    private String videoPath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        data.clear();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_main_video_player_layout);
        data.clear();
        mRecyclerViewUtil = new RecyclerViewUtil();
        initData();

        ArrayList<String> infoList = new ArrayList<String>();
        Intent intent=getIntent();
        infoList= intent.getStringArrayListExtra("infoList");
        for(String a:infoList){
            videoPath=a;
        }
        showSheetDialog(videoPath);
        bottomSheetDialog.show();
        infoList.clear();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }





    //初始化数据 在项目中是从服务器获取数据
    private void initData() {
        data.clear();
       ArrayList<comment> listObj =  (ArrayList<comment>) getIntent().getSerializableExtra("listobj");
        for(comment comment:listObj){
            Log.e("mytestlalala",comment.toString());
        }
        for (int i=0;i<listObj.size();++i) {
            FirstLevelBean firstLevelBean = new FirstLevelBean();
            firstLevelBean.setContent(listObj.get(i).getContent());
            firstLevelBean.setCreateTime(System.currentTimeMillis());
            firstLevelBean.setHeadImg(listObj.get(i).getTime());
            firstLevelBean.setId(i + "");
            firstLevelBean.setUserId("UserId" + i);
            firstLevelBean.setIsLike(0);
            firstLevelBean.setPosition(i);
            firstLevelBean.setLikeCount(i);
            firstLevelBean.setUserName(listObj.get(i).getAuthor());
            List<SecondLevelBean> beanList = new ArrayList<>();
            firstLevelBean.setSecondLevelBeans(beanList);
            data.add(firstLevelBean);
        }
    }

    /**
     * 重新排列数据
     * 未解决滑动卡顿问题
     */
    private void sort() {
        int size = data.size();
        for (int i = 0; i < size; i++) {
            FirstLevelBean firstLevelBean = data.get(i);
            firstLevelBean.setPosition(i);

            List<SecondLevelBean> secondLevelBeans = firstLevelBean.getSecondLevelBeans();
            if (secondLevelBeans == null || secondLevelBeans.isEmpty()) continue;
            int count = secondLevelBeans.size();
            for (int j = 0; j < count; j++) {
                SecondLevelBean secondLevelBean = secondLevelBeans.get(j);
                secondLevelBean.setPosition(i);
                secondLevelBean.setChildPosition(j);
            }
        }

        bottomSheetAdapter.notifyDataSetChanged();

    }

   /* public void show(View view) {
        slideOffset = 0;
        bottomSheetDialog.show();
    }*/

    private void showSheetDialog(String videoPath) {
        if (bottomSheetDialog != null) {
            bottomSheetDialog.show();
            return;
        }
        View view = View.inflate(this, R.layout.dialog_bottomsheet, null);
        ImageView iv_dialog_close = view.findViewById(R.id.dialog_bottomsheet_iv_close);
        rv_dialog_lists = view.findViewById(R.id.dialog_bottomsheet_rv_lists);
        RelativeLayout rl_comment = view.findViewById(R.id.rl_comment);

        iv_dialog_close.setOnClickListener(v -> bottomSheetDialog.dismiss()
        );

        rl_comment.setOnClickListener(v -> {
            videoPlayActivity.this.initInputTextMsgDialog(null, false, null, -1);
        });


      //视频 sion   sion-------------------------------
        videoController1 = view.findViewById(R.id.videocontroller2);
        videoController1.setSkin(R.color.colorAccent, R.color.colorPrimary, R.drawable.skin_seek_progress,
                R.color.bottom_bg, R.drawable.skin_enlarge_video, R.drawable.skin_shrink_video);
        videoController1.setUp(videoPath,
                "http://p.qpic.cn/videoyun/0/2449_43b6f696980311e567f22794e792_1/640",
                "sion：视频测试");
        //视频 sion   sion-------------------------------






        bottomSheetAdapter = new CommentDialogSingleAdapter(this);
        bottomSheetAdapter.setNewData(data);
        rv_dialog_lists.setHasFixedSize(true);
        rv_dialog_lists.setLayoutManager(new LinearLayoutManager(this));
        rv_dialog_lists.setItemAnimator(new DefaultItemAnimator());
        bottomSheetAdapter.setLoadMoreView(new SimpleLoadMoreView());
        bottomSheetAdapter.setOnLoadMoreListener(this, rv_dialog_lists);
        rv_dialog_lists.setAdapter(bottomSheetAdapter);

        initListener();

        bottomSheetDialog = new BottomSheetDialog(this, R.style.dialog);
        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.setCanceledOnTouchOutside(true);
        final BottomSheetBehavior mDialogBehavior = BottomSheetBehavior.from((View) view.getParent());
        mDialogBehavior.setPeekHeight(getWindowHeight());
        mDialogBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_HIDDEN) {
//                    bottomSheetDialog.dismiss();
                    mDialogBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                } else if (newState == BottomSheetBehavior.STATE_SETTLING) {
                    if (slideOffset <= -0.28) {
                        bottomSheetDialog.dismiss();
                    }
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                videoPlayActivity.this.slideOffset = slideOffset;

            }
        });
    }

    private void initListener() {
        // 点击事件
        bottomSheetAdapter.setOnItemChildClickListener((adapter, view1, position) -> {
            FirstLevelBean firstLevelBean = bottomSheetAdapter.getData().get(position);
            if (firstLevelBean == null) return;
            if (view1.getId() == R.id.ll_like) {
                //一级评论点赞 项目中还得通知服务器 成功才可以修改
                firstLevelBean.setLikeCount(firstLevelBean.getLikeCount() + (firstLevelBean.getIsLike() == 0 ? 1 : -1));
                firstLevelBean.setIsLike(firstLevelBean.getIsLike() == 0 ? 1 : 0);
                data.set(position, firstLevelBean);
                bottomSheetAdapter.notifyItemChanged(firstLevelBean.getPosition());
            } else if (view1.getId() == R.id.rl_group) {
                //添加二级评论
                videoPlayActivity.this.initInputTextMsgDialog((View) view1.getParent(), false, firstLevelBean.getHeadImg(), position);
            }
        });
        //滚动事件
        if (mRecyclerViewUtil != null) mRecyclerViewUtil.initScrollListener(rv_dialog_lists);
    }

    private void initInputTextMsgDialog(View view, final boolean isReply, final String headImg, final int position) {
        dismissInputDialog();
        if (view != null) {
            offsetY = view.getTop();
            scrollLocation(offsetY);
        }
        if (inputTextMsgDialog == null) {
            inputTextMsgDialog = new InputTextMsgDialog(this, R.style.dialog_center);
            inputTextMsgDialog.setmOnTextSendListener(new InputTextMsgDialog.OnTextSendListener() {
                @Override
                public void onTextSend(String msg) {
                    addComment(isReply,headImg,position,msg);
                }

                @Override
                public void dismiss() {
                    scrollLocation(-offsetY);
                }
            });
        }
        showInputTextMsgDialog();
    }

    private void addComment(boolean isReply, String headImg, final int position, String msg) {
        if (position >= 0) {
            //添加二级评论
            SecondLevelBean secondLevelBean = new SecondLevelBean();
            FirstLevelBean firstLevelBean = bottomSheetAdapter.getData().get(position);
            secondLevelBean.setReplyUserName("replyUserName");
            secondLevelBean.setIsReply(isReply ? 1 : 0);
            secondLevelBean.setContent(msg);
            secondLevelBean.setHeadImg(headImg);
            secondLevelBean.setCreateTime(System.currentTimeMillis());
            secondLevelBean.setIsLike(0);
            secondLevelBean.setUserName("小唯");
            secondLevelBean.setId(firstLevelBean.getSecondLevelBeans() + "");
            firstLevelBean.getSecondLevelBeans().add(secondLevelBean);

            data.set(firstLevelBean.getPosition(), firstLevelBean);
            bottomSheetAdapter.notifyDataSetChanged();
            rv_dialog_lists.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ((LinearLayoutManager) rv_dialog_lists.getLayoutManager())
                            .scrollToPositionWithOffset(position == data.size() - 1 ? position
                                    : position + 1, position == data.size() - 1 ? Integer.MIN_VALUE : rv_dialog_lists.getHeight() / 2);
                }
            }, 100);

        } else {
            //添加一级评论
            FirstLevelBean firstLevelBean = new FirstLevelBean();
            firstLevelBean.setUserName("小唯");
            firstLevelBean.setId(bottomSheetAdapter.getItemCount() + 1 + "");
            firstLevelBean.setHeadImg("http://tiebapic.baidu.com/forum/pic/item/6b63f6246b600c336a98ada50d4c510fd8f9a1fe.jpg");
            firstLevelBean.setCreateTime(System.currentTimeMillis());
            firstLevelBean.setContent(msg);
            firstLevelBean.setLikeCount(0);
            firstLevelBean.setSecondLevelBeans(new ArrayList<SecondLevelBean>());
            data.add(0, firstLevelBean);
            sort();
            rv_dialog_lists.scrollToPosition(0);
        }
    }

    private void dismissInputDialog() {
        if (inputTextMsgDialog != null) {
            if (inputTextMsgDialog.isShowing()) inputTextMsgDialog.dismiss();
            inputTextMsgDialog.cancel();
            inputTextMsgDialog = null;
        }
    }

    private void showInputTextMsgDialog() {
        inputTextMsgDialog.show();
    }

    private int getWindowHeight() {
        Resources res = getResources();
        DisplayMetrics displayMetrics = res.getDisplayMetrics();
        return displayMetrics.heightPixels;
    }

    //在项目中是从服务器获取数据，其实就是二级评论分页获取
    @Override
    public void onMoreClick(View layout, int position) {
        FirstLevelBean firstLevelBean = data.get(position);
        List<SecondLevelBean> beans = firstLevelBean.getSecondLevelBeans();
        int size = beans.size();
        sort();
    }

    //添加二级评论（回复某人）
    @Override
    public void onItemClick(View layout, SecondLevelBean bean, int position) {
        initInputTextMsgDialog(layout, true, bean.getHeadImg(), position);
    }

    //二级评论点赞 本地数据更新喜欢状态
    // 在项目中是还需要通知服务器成功才可以修改本地数据
    @Override
    public void onLikeClick(View layout, SecondLevelBean bean, int position) {
        bean.setLikeCount(bean.getLikeCount() + (bean.getIsLike() == 1 ? -1 : 1));
        bean.setIsLike(bean.getIsLike() == 1 ? 0 : 1);
        data.get(bean.getPosition()).getSecondLevelBeans().set(bean.getChildPosition(), bean);
        bottomSheetAdapter.notifyItemChanged(bean.getPosition());
    }

    //在项目中是从服务器获取数据，其实就是一级评论分页获取
    @Override
    public void onLoadMoreRequested() {
        if (data.size() >= totalCount) {
            bottomSheetAdapter.loadMoreEnd(false);
            return;
        }
        //加载更多
        for (int i = 0; i < 1; i++) {
            FirstLevelBean firstLevelBean = new FirstLevelBean();
            firstLevelBean.setUserName("test" + i);
            firstLevelBean.setId(bottomSheetAdapter.getItemCount() + (i + 1) + "");
            firstLevelBean.setHeadImg("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1918451189,3095768332&fm=26&gp=0.jpg");
            firstLevelBean.setCreateTime(System.currentTimeMillis());
            firstLevelBean.setContent("test");
            firstLevelBean.setLikeCount(0);
            firstLevelBean.setSecondLevelBeans(new ArrayList<SecondLevelBean>());
            data.add(firstLevelBean);
        }
        sort();
        bottomSheetAdapter.loadMoreComplete();
    }

    // item滑动到原位
    public void scrollLocation(int offsetY) {
        try {
            rv_dialog_lists.smoothScrollBy(0, offsetY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   @Override
    public void onBackPressed() {
        super.onBackPressed();
        data.clear();
        bottomSheetDialog.dismiss();
        mRecyclerViewUtil.destroy();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
         finish();
        if (mRecyclerViewUtil != null){
            mRecyclerViewUtil.destroy();
            mRecyclerViewUtil = null;
        }
        bottomSheetAdapter = null;
    }
}
