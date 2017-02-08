package com.sun.hellolady.demo.Wifi;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.sun.hellolady.R;
import com.support.util.common.WifiManage;

import java.util.List;

/**
 * Created by sunjiamin on 2016/11/7.
 */

public class WifiAdapter extends RecyclerView.Adapter<ItemViewHolder>{

    List<WifiManage.WifiInfo> wifiInfoList;
    Context cxt;
    public WifiAdapter(Context cxt,List<WifiManage.WifiInfo> wifiInfoList){
        this.wifiInfoList=wifiInfoList;
        this.cxt = cxt;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(cxt).
                inflate(R.layout.item_wifiinfo,parent,false));
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.tv_wifi_name.setText(wifiInfoList.get(position).Ssid);
        holder.tv_wifi_psd.setText(wifiInfoList.get(position).Password);

    }

    @Override
    public int getItemCount() {
        return null!=wifiInfoList?wifiInfoList.size():0;
    }
}
