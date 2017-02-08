package com.support.util.SwipeRefreshRecyclerView;

/**
 * Created by Jiamin.Sun on 1/26/2016.
 * 普通的滚动监听，可以在LayoutManager中获取到RecyclerViewScrollManager后add多个
 */
public interface  OnRecyclerViewScrollListener {
     void onScrollStateChanged(android.support.v7.widget.RecyclerView recyclerView, int newState);

     void onScrolled(android.support.v7.widget.RecyclerView recyclerView, int dx, int dy);
}
