package com.sun.hellolady.demo.SlidingPanel;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.sun.hellolady.R;
import com.support.util.common.LogUtil;

/**
 * Created by sunjiamin on 2016/10/19.
 */

public class TouchMyPanel extends LinearLayout {

    private View mHandle;

    private int mHandHeight = 200;   //hand在父布局的移动范围
    private int mDownY;     //ACTION_DOWN时y的坐标
    public TouchMyPanel(Context context) {
        this(context, null);
    }


    public TouchMyPanel(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public TouchMyPanel(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // mGestureDetector = new GestureDetector(context, onGestureListener);
        setOrientation(LinearLayout.VERTICAL);
    }

    @Override
    protected void onFinishInflate() {
        mHandle = findViewById(R.id.handle);
        if (mHandle == null) {
            throw new IllegalArgumentException("The handle attribute is required and must refer "
                    + "to a valid child.");
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        LogUtil.e("sjm","onMeasure... widthMeasureSpec："+widthMeasureSpec+" heightMeasureSpec:"+heightMeasureSpec);
        View handle = mHandle;
        measureChild(handle, widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        int height = b - t;
        final View handle = mHandle;
        int childWidth = handle.getMeasuredWidth();
        handle.layout(0, height - mHandHeight, childWidth, height);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtil.e("sjm","TouchMyPanel...onTouchEvent...event.getAction():"+event.getAction());

        switch (event.getAction()){

            case MotionEvent.ACTION_DOWN:
                mDownY= (int)event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                int nowY=(int)event.getY();
                //nowY-mDownY 滑动的高度差
                moveContent(nowY-mDownY);
                break;
            case MotionEvent.ACTION_UP:
                //adjustContentView();
                break;
        }
        return super.onTouchEvent(event);
    }

    private void moveContent(int i) {

    }
}
