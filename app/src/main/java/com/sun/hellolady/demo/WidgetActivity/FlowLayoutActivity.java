package com.sun.hellolady.demo.WidgetActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sun.hellolady.R;
import com.sun.hellolady.demo.widget.FlowLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FlowLayoutActivity extends AppCompatActivity {

    LayoutInflater mInflater;
   // @BindView(R.id.id_flowlayout1)
    FlowLayout idFlowlayout1;
   // @BindView(R.id.id_flowlayout2)
    FlowLayout idFlowlayout2;
    private String[] mVals = new String[]
            {"Do", "one thing", "at a time", "and do well.", "Never", "forget",
                    "to say", "thanks.", "Keep on", "going ", "never give up."};
    Unbinder unB ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow_layout);
        //unB =  ButterKnife.bind(this);
        idFlowlayout1= (FlowLayout)findViewById(R.id.id_flowlayout1) ;
        idFlowlayout2= (FlowLayout)findViewById(R.id.id_flowlayout2) ;
        mInflater = LayoutInflater.from(this);
        initFlowlayout2();
    }

    private void initFlowlayout2() {
        for (int i = 0; i < mVals.length; i++) {
            final RelativeLayout rl2 = (RelativeLayout) mInflater.inflate(R.layout.flow_layout, idFlowlayout2, false);
            TextView tv2 = (TextView) rl2.findViewById(R.id.tv);
            tv2.setText(mVals[i]);
            rl2.setTag(i);
            idFlowlayout2.addView(rl2);
            rl2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int i = (int) v.getTag();
                    addViewToFlowlayout1(i);
                    rl2.setBackgroundResource(R.drawable.flow_layout_disable_bg);
                    rl2.setClickable(false);

                }
            });
        }
    }

    private void addViewToFlowlayout1(int i) {
        RelativeLayout rl1 = (RelativeLayout) mInflater.inflate(R.layout.flow_layout, idFlowlayout1, false);
        ImageView iv = (ImageView) rl1.findViewById(R.id.iv);
        iv.setVisibility(View.VISIBLE);
        TextView tv1 = (TextView) rl1.findViewById(R.id.tv);
        tv1.setText(mVals[i]);
        rl1.setTag(i);
        idFlowlayout1.addView(rl1);
        rl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = (int) v.getTag();
                idFlowlayout1.removeView(v);
                View view = idFlowlayout2.getChildAt(i);
                view.setClickable(true);
                view.setBackgroundResource(R.drawable.flow_layout_bg);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
       // unB.unbind();
    }
}
