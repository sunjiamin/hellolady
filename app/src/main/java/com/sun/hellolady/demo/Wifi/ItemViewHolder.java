package com.sun.hellolady.demo.Wifi;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.sun.hellolady.R;

/**
 * Created by sunjiamin on 2016/11/7.
 */
public class ItemViewHolder extends RecyclerView.ViewHolder{
    public ItemViewHolder(View itemView) {
        super(itemView);
        tv_wifi_name =  (TextView) itemView.findViewById(R.id.wifi_name);
        tv_wifi_psd =  (TextView) itemView.findViewById(R.id.wifi_psd);
    }
    TextView tv_wifi_name;
    TextView tv_wifi_psd;
}
