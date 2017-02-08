package com.sun.hellolady.demo.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.nineoldandroids.animation.ValueAnimator;
import com.sun.hellolady.R;

/**
 * 项目名称：hellolady
 * 类描述：
 * 创建人：Jiamin.Sun
 * 创建时间：5/31/2016 9:07 AM
 */
public class CircleProgressBar extends View {

    private int maxProgressbarAngle;    //最大弧形角度
    private int startCircleAngle;       //弧形开始的角度
    private final Context mContext;
    private int centerX;        //中心点
    private int centerY;

    private int mProgressBarColor;
    private float mProgressbarWidth;
    private String mTitleText;
    private float textSize;
    private int textColor;
    private int mProgressbarUnreachedColor;
    private int maxValue;
    private String mTextUnit;
    private Paint mCircleProgressBarPaint;
    private Paint mCircleProgressBarUnreachedPaint;
    private Paint mTextPaint;


    private RectF rectF;

    private int radius;
    private int progress;           //currentProgress变化的最大值
    private int currentProgress;        //随着动画变化
    private boolean animateFinish = false;
    private float numSize;
    private int numColor;
    private Paint mNumPaint;
    private int animationDuring = 1000;

    public CircleProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init(attrs);
        initView();
    }
    private void init(AttributeSet attrs) {

        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.CircleProgressBar);
        this.mProgressBarColor = typedArray.getColor(R.styleable.CircleProgressBar_progressbar_color, Color.BLUE);
        this.mProgressbarWidth = typedArray.getDimension(R.styleable.CircleProgressBar_progressbar_width, dipToPx(10));
        this.mTitleText = typedArray.getString(R.styleable.CircleProgressBar_title_text);
        this.textSize = typedArray.getDimension(R.styleable.CircleProgressBar_text_size, dipToPx(15));
        this.textColor = typedArray.getColor(R.styleable.CircleProgressBar_text_color, Color.BLACK);
        this.mProgressbarUnreachedColor = typedArray.getColor(R.styleable.CircleProgressBar_progressbar_unreached_color, Color.GRAY);
        this.maxValue = typedArray.getInteger(R.styleable.CircleProgressBar_max_value, 100);
        this.mTextUnit = typedArray.getString(R.styleable.CircleProgressBar_text_unit);
        this.numSize = typedArray.getDimension(R.styleable.CircleProgressBar_progress_num_size, dipToPx(80));
        this.numColor = typedArray.getColor(R.styleable.CircleProgressBar_progress_num_color, Color.BLACK);
        setCircleAngle(typedArray.getInteger(R.styleable.CircleProgressBar_max_progressbar_angle, 300));
        typedArray.recycle();
    }

    private void initView() {

        mCircleProgressBarPaint = new Paint();
        mCircleProgressBarPaint.setStyle(Paint.Style.STROKE);
        mCircleProgressBarPaint.setColor(mProgressBarColor);
        mCircleProgressBarPaint.setStrokeWidth(mProgressbarWidth);
        mCircleProgressBarPaint.setStrokeCap(Paint.Cap.ROUND);
        mCircleProgressBarPaint.setAntiAlias(true);

        mCircleProgressBarUnreachedPaint = new Paint();
        mCircleProgressBarUnreachedPaint.setStyle(Paint.Style.STROKE);
        mCircleProgressBarUnreachedPaint.setColor(mProgressbarUnreachedColor);
        mCircleProgressBarUnreachedPaint.setStrokeCap(Paint.Cap.ROUND);
        mCircleProgressBarUnreachedPaint.setStrokeWidth(mProgressbarWidth);
        mCircleProgressBarUnreachedPaint.setAntiAlias(true);

        //除了数字的其他文字
        mTextPaint = new Paint();
        mTextPaint.setColor(textColor);
        mTextPaint.setTextSize(textSize);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mTextPaint.setAntiAlias(true);

        //数字
        mNumPaint = new Paint();
        mNumPaint.setColor(numColor);
        mNumPaint.setTextSize(numSize);
        mNumPaint.setTextAlign(Paint.Align.CENTER);
        mNumPaint.setAntiAlias(true);
    }



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int min = Math.min(width, height);
        setMeasuredDimension(min, min);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if ((centerX & centerY) == 0) {
            radius = (int) (getWidth() / 2 - mProgressbarWidth / 2);
            centerX = radius + (int) mProgressbarWidth / 2;
            centerY = radius + (int) mProgressbarWidth / 2;
            rectF = new RectF();
            rectF.left = mProgressbarWidth / 2;
            rectF.top = mProgressbarWidth / 2;
            rectF.right = getWidth() - mProgressbarWidth / 2;
            rectF.bottom = getHeight() - mProgressbarWidth / 2;
        }

        canvas.drawArc(rectF, startCircleAngle, maxProgressbarAngle, false, mCircleProgressBarUnreachedPaint);
        if (currentProgress >= progress) {
            animateFinish = true;
        }
        canvas.drawArc(rectF, startCircleAngle, getProgressAngle(), false, mCircleProgressBarPaint);

        if (mTitleText != null && !mTitleText.trim().isEmpty()) {
            canvas.drawText(mTitleText,
                    centerX, centerY - 2 * numSize / 3, mTextPaint);
            canvas.drawText(String.valueOf(currentProgress),
                    centerX, centerY + numSize / 3, mNumPaint);
            canvas.drawText(mTextUnit, centerX, centerY + 2 * numSize / 3 + 2 * textSize / 3, mTextPaint);
        }
        if (!animateFinish)
            invalidate();
    }
    public void setProgress(int value) {
        if (value > maxValue) {
            value = maxValue;
        }
        if (value < 0) {
            value = 0;
        }
        this.progress = value;
        setAnimation(0, value, animationDuring);

    }

    private void setAnimation(int start, int end, int during) {
        ValueAnimator animator = new ValueAnimator();
        animator.setDuration(during);
        animator.setIntValues(start, end);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentProgress = (int) animation.getAnimatedValue();
            }
        });
        animateFinish = false;
        postInvalidate();
        animator.start();
    }


    private int dipToPx(float dip) {
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (dip * density + 0.5f * (dip >= 0 ? 1 : -1));
    }

    private float getProgressAngle() {
        float angle = (currentProgress * 1f) / maxValue * maxProgressbarAngle;
        return angle == 0 ? 0.001f : angle;
    }

    public void setProgressBarTitle(String text) {
        this.mTitleText = text;
        postInvalidate();
    }

    private void setCircleAngle(int angle) {
        if (angle > 360)
            this.maxProgressbarAngle = angle % 360;
        else
            this.maxProgressbarAngle = angle;
        startCircleAngle = -maxProgressbarAngle / 2 - 90;
    }

    public void setUnitText(String text) {
        this.mTextUnit = text;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
        postInvalidate();
    }
}
