package com.support.util.MyRecyclerView;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.support.util.MyRecyclerView.item.AdapterItem;
import com.support.util.MyRecyclerView.util.IAdapter;
import com.support.util.MyRecyclerView.util.ItemTypeUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：hellolady
 * 类描述：
 * 创建人：Jiamin.Sun
 * 创建时间：3/9/2016 9:14 AM
 * 修改人：Jiamin.Sun
 * 修改时间：3/9/2016 9:14 AM
 * 修改备注：
 */
public class CommonRecyclerAdapter<T> extends RecyclerView.Adapter implements IAdapter<T> {
    private List<T> mDataList;

    private Object mItemType;

    private ItemTypeUtil mUtil = new ItemTypeUtil();

    Context cxt;

    protected CommonRecyclerAdapter(@Nullable List<T> data,Context c) {
        if (data == null) {
            data = new ArrayList<>();
        }
        mDataList = data;
        cxt=c;
    }


    protected CommonRecyclerAdapter(@Nullable List<T> data) {
        if (data == null) {
            data = new ArrayList<>();
        }
        mDataList = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //这里发挥一个Holder
        return new RcvAdapterViewHolder(parent.getContext(), parent, createItem(mItemType));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        //这里的holder 是上面onCreateViewHolder返回的Holder
        //这里处理数据的绑定 即给各个view附上显示的值  这里封装是在Holder的Item里面处理
        ((RcvAdapterViewHolder) holder).item.handleData(cxt,getConvertedData(mDataList.get(position), mItemType), position);


    }

    @NonNull
    @Override
    public Object getConvertedData(T data, Object type) {
        return data;
    }


    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    @Override
    public void setData(@NonNull List<T> data) {
        mDataList=data;
    }

    @Override
    public List<T> getData() {
        return mDataList;
    }

    @Override
    public Object getItemType(T t) {
        return t;//default
    }

    @NonNull
    @Override
    public AdapterItem createItem(Object type) {
        return null;
    }



    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
       mItemType = getItemType(mDataList.get(position));
        return mUtil.getIntType(mItemType);
    }

    ///////////////////////////////////////////////////////////////////////////
    // 内部用到的viewHold
    ///////////////////////////////////////////////////////////////////////////

    private static class RcvAdapterViewHolder extends RecyclerView.ViewHolder {

        protected AdapterItem item;

        protected Context ctx;

        public boolean isNew = true; // debug中才用到

        protected RcvAdapterViewHolder(Context context, ViewGroup parent, AdapterItem item) {
            //调用父类构造函数初始化itemView
            super(LayoutInflater.from(context).inflate(item.getLayoutResId(), parent, false));
            ctx=context;
            this.item = item;
            this.item.bindViews(itemView);
            this.item.setViews();
            this.item.setItemClick(context,itemView);

        }
    }
}
