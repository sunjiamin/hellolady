package com.sun.hellolady.demo.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.sun.hellolady.R;
import com.support.util.common.ConvertUtil;

/**
 * Created by sunjiamin on 2017/2/9.
 */

public class FollowView extends View {
    public int textColor;

    int lastX=100;
    int lastY=100;
    Context c;

    Paint p = new Paint();

    public FollowView(Context context) {
        super(context,null);

    }

    public FollowView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.myView);
        textColor = typedArray.getColor(R.styleable.myView_textColor, Color.BLACK);
        typedArray.recycle();
        c=context;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        p.setAntiAlias(true);
        p.setColor(Color.BLACK);
        p.setTextSize(ConvertUtil.dip2px(c,20));
        //先画一个圆
        canvas.drawCircle(lastX,lastY,ConvertUtil.dip2px(c,30),p);
        p.setColor(textColor);
        canvas.drawText("Hello Jojo",lastX-ConvertUtil.dip2px(c,30),lastY+ConvertUtil.dip2px(c,50),p);

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        lastX = (int)event.getX();
        lastY = (int)event.getY();
        invalidate();
        return true;
    }
}
