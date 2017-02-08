package com.support.util.widget;

import android.content.Context;
import android.os.Build;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewConfiguration;


/**
 * Created by Jiamin.Sun on 1/25/2016.
 * 在官方原有的下拉刷新基础上添加上拉加载更多
 */
public class MySwipeRefreshLayout extends SwipeRefreshLayout {


    /**
     * 滑动到最下面时的上拉操作
     */

    private int mTouchSlop;
    /**
     * listview实例
     */
    private RecyclerView mListView;

    /**
     * 按下时的y坐标
     */
    private int mYDown;
    /**
     * 抬起时的y坐标, 与mYDown一起用于滑动到底部时判断是上拉还是下拉
     */
    private int mLastY;

    /**
     * 是否在加载中 ( 上拉加载更多 )
     */
    private boolean isLoading = false;

    public MySwipeRefreshLayout(Context context) {
        super(context);
    }

    public MySwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        // 初始化ListView对象
        if (mListView == null) {
            getRecyclerView();
        }

    }

    /**
     * 获取RecyclerView
     */
    private void getRecyclerView() {

        int childs=getChildCount();
        if(childs>0){
            View childView = getChildAt(0);
            if(childView instanceof  RecyclerView){
                mListView = (RecyclerView) childView;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    mListView.setOnScrollChangeListener(new OnScrollChangeListener() {
                        @Override
                        public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                        }
                    });
                }
            }
        }
    }





}
