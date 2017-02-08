package com.sun.hellolady.demo.Wifi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.sun.hellolady.R;
import com.support.util.common.WifiManage;

import java.util.List;

public class LookWifiPasswordActivity extends AppCompatActivity {

    private RecyclerView recycler_wifi_list;
    private RelativeLayout activity_look_wifi_password;
    List<WifiManage.WifiInfo> wifiInfoList ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_look_wifi_password);
        initView();
    }

    private void initView() {
        recycler_wifi_list = (RecyclerView) findViewById(R.id.recycler_wifi_list);
        activity_look_wifi_password = (RelativeLayout) findViewById(R.id.activity_look_wifi_password);
        try {
            wifiInfoList = new WifiManage().Read();
            recycler_wifi_list.setLayoutManager(new LinearLayoutManager(this));
            recycler_wifi_list.setAdapter(new WifiAdapter(this,wifiInfoList));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
