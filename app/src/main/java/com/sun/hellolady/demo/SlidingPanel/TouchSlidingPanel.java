package com.sun.hellolady.demo.SlidingPanel;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;

import com.sun.hellolady.R;
import com.support.util.common.LogUtil;

/**
 * Created by 82328 on 2016/9/9.
 */

public class TouchSlidingPanel extends LinearLayout /*implements GestureDetector.OnGestureListener*/{
    private static final String TAG = "SlidingPanel";
    private static final String GTAG = "GTAG";    //手势TAG

    private View mHandle;


    private int mContentRangeTop;   //content在父布局的移动范围
    private int mContentRangeBottom;

    private int mDownY;     //ACTION_DOWN时y的坐标
    private boolean mExpanded=false;  //是否展开

    private static final int ANIMATION_DURATION = 1000;   //  从底部到上面需要1s


    private static final int mHandHeight = 400;   //hand在父布局的移动范围

    public TouchSlidingPanel(Context context) {
        this(context, null);
    }


    public TouchSlidingPanel(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public TouchSlidingPanel(Context context, AttributeSet attrs, int defStyleAttr) {
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

    /**
     * 用于测量view的大小
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        LogUtil.e("sjm","onMeasure... widthMeasureSpec："+widthMeasureSpec+" heightMeasureSpec:"+heightMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        View handle = mHandle;
        measureChild(handle, widthMeasureSpec, heightMeasureSpec);

    }


    /**
     * 用于放置view的位置
     * @param changed  int left, int top, int right, int bottom
     * @param l left 左边
     * @param t top 上边
     * @param r right 右边
     * @param b bottom 下边
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        LogUtil.e("sjm","onLayout... left："+l+" top:"+t+" right:"+r+" bottom:"+b);
        //获取自身的宽高
        final int width = r - l;
        final int height = b - t;

        mContentRangeTop=0;
        mContentRangeBottom = b - t-mHandHeight; //高度

        final View handle = mHandle;
        int childHeight = handle.getMeasuredHeight();
        int childWidth = handle.getMeasuredWidth();

        handle.layout(0, height - mHandHeight, childWidth, handle.getMeasuredHeight()+(height - mHandHeight));

    }

    /**
     * 一般在这里绘制子控件
     * @param canvas
     */
    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);

        long drawingTime = getDrawingTime();

        final View handle = mHandle;
        drawChild(canvas, handle, drawingTime);
    }


//    /**
//     * 父控件是否拦截触摸
//     * 一但返回True（代表事件在当前的viewGroup中会被处理），则向下传递之路被截断，同时把事件传递给当前的控件的onTouchEvent()处理
//     *    返回false，则把事件交给子控件的onInterceptTouchEvent()
//     * @param ev
//     * @return
//     */
//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        return true;
//    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtil.e("sjm","onTouchEvent...event.getAction():"+event.getAction());
        switch (event.getAction()){

            case MotionEvent.ACTION_DOWN:
                mDownY= (int)event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                int nowY=(int)event.getY();
                //nowY-mDownY 滑动的高度差
                LogUtil.e("sjm","滑动的高度差："+(nowY - mDownY + (mExpanded ? mContentRangeTop : (mContentRangeBottom))));
                moveContent(nowY - mDownY + (mExpanded ? mContentRangeTop : (mContentRangeBottom)));
                break;
            case MotionEvent.ACTION_UP:
                adjustContentView();
                break;
        }
        return  super.onTouchEvent(event);
    }


    /**
     * 停止滑动
     *
     * @param
     */
    private void stopTracking() {
        //判断content是展开还是收缩
        updateExpanded();
    }


    /**
     * 更新mExpanded状态
     */
    private void updateExpanded(){
        if (mHandle.getTop() <= mContentRangeTop) {
            mExpanded = true;
        } else {
            mExpanded = false;
        }
    }


    /**
     * move content到指定位置
     *
     * @param position
     */
    private void moveContent(int position) {
        LogUtil.e("sjm", "*********move Content:position" + position + "**********");

        //不能移出上边界
        if (position < mContentRangeTop) {
            position = mContentRangeTop;
        } else if (position > mContentRangeBottom) {
            position = mContentRangeBottom;
        }

        final View content=mHandle;
        final int top = (int) mHandle.getY();
        final int deltaY =  top+position;

        // Left position, relative to parent
        // top position, relative to parent
        // Right position, relative to parent
        // Bottom position, relative to parent
        LogUtil.e("sjm","position+content.getHeight():"+(position+content.getHeight()));
        content.layout(0,position,content.getWidth(),position+content.getHeight());
    }


    //移动Content
    private void doAnimation(int nowY, final int targetY) {
        AccelerateInterpolator accelerateInterpolator = new AccelerateInterpolator();
        //BounceInterpolator bounceInterpolator=new BounceInterpolator();
        TranslateAnimation animation = new TranslateAnimation(0, 0, 0, targetY - nowY);
        animation.setDuration(ANIMATION_DURATION * Math.abs(targetY - nowY) / mContentRangeBottom);
        animation.setInterpolator(accelerateInterpolator);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //Log.i(TAG,"onAnimationEnd");
                mHandle.clearAnimation();
                mHandle.layout(0, targetY, mHandle.getMeasuredWidth(), targetY + mHandle.getMeasuredHeight());
                stopTracking();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        mHandle.startAnimation(animation);
    }


    /**
     * 点击时打开Content*
     */
    private void open() {
        doAnimation(mContentRangeBottom, 0);
        stopTracking();
    }

    /**
     * ACTION_UP时 contentView不是停靠在屏幕边缘（在屏幕中间）时，调整contentView的位置*
     */
    private void adjustContentView(){
        final int top = mHandle.getTop();
        //切割父容器，分成3等份
        final int perRange=(mContentRangeBottom-mContentRangeTop)/3;
        if(mExpanded){
            //小于1/3
            if(top<perRange+mContentRangeTop){
                doAnimation(top,0);
            }else{
                doAnimation(top, mContentRangeBottom);
            }
        }else{
            //小于2/3
            if(top<mContentRangeTop+perRange*2){
                doAnimation(top,0);
            }else{
                doAnimation(top,mContentRangeBottom);
            }
        }

    }

}
