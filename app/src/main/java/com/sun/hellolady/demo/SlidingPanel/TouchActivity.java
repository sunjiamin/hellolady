package com.sun.hellolady.demo.SlidingPanel;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;

import com.sun.hellolady.R;
import com.support.util.common.LogUtil;
import com.support.util.common.T;

public class TouchActivity extends AppCompatActivity  {


    private LinearLayout handle;
    private ImageView iv_content;
    private ImageView iv_hand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch);
        initView();

    }


    private void initView() {
        handle = (LinearLayout) findViewById(R.id.handle);
        iv_content = (ImageView) findViewById(R.id.iv_content);
        iv_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.e("sjm","---------iv_content");
                T.show(TouchActivity.this,"iv_content");

            }
        });
        iv_hand = (ImageView) findViewById(R.id.iv_hand);
        iv_hand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.e("sjm","---------iv_hand");
                T.show(TouchActivity.this,"iv_content");
            }
        });
    }


}
