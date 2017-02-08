package com.sun.hellolady.demo.SlidingPanel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.SlidingDrawer;
import android.widget.TextView;

import com.sun.hellolady.R;

public class slidingdrawer extends AppCompatActivity {

    private SlidingDrawer mDrawer;
    private ImageButton imbg;
    private Boolean flag=false;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slidingdrawer);
        imbg=(ImageButton)findViewById(R.id.handle);
        mDrawer=(SlidingDrawer)findViewById(R.id.slidingdrawer);
        tv=(TextView)findViewById(R.id.tv);

        mDrawer.setOnDrawerOpenListener(new SlidingDrawer.OnDrawerOpenListener()
        {
            @Override
            public void onDrawerOpened() {
                flag=true;
                imbg.setImageResource(R.drawable.ic_error);
            }

        });

        mDrawer.setOnDrawerCloseListener(new SlidingDrawer.OnDrawerCloseListener(){

            @Override
            public void onDrawerClosed() {
                flag=false;
                imbg.setImageResource(R.drawable.ic_exception);
            }

        });

        mDrawer.setOnDrawerScrollListener(new SlidingDrawer.OnDrawerScrollListener(){

            @Override
            public void onScrollEnded() {
                tv.setText("结束拖动");
            }

            @Override
            public void onScrollStarted() {
                tv.setText("开始拖动");
            }

        });
    }
}
