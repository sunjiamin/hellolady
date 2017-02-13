package com.sun.hellolady.demo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunjiamin on 2017/2/9.
 */

public class FlowLayout extends ViewGroup {
    public FlowLayout(Context context) {
        super(context,null);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        mAllViews.clear();
        mLineHeight.clear();
        // 当前ViewGroup的宽度
        int width = getWidth();
        int lineWidth = 0;
        int lineHeight = 0;
        // 存储每一行所有的childView
        List<View> lineViews = new ArrayList<View>();
        int cCount = getChildCount();
        for (int i = 0; i < cCount; i++)
        {
            View child = getChildAt(i);
            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();

            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();
            lineWidth += childWidth + lp.leftMargin + lp.rightMargin;
            lineHeight = Math.max(lineHeight, childHeight + lp.topMargin+ lp.bottomMargin);
            lineViews.add(child);
            // 换行，在onMeasure中childWidth是加上Margin值的
            if (childWidth + lineWidth + lp.leftMargin + lp.rightMargin > width - getPaddingLeft() - getPaddingRight())
            {
                // 记录行高
                mLineHeight.add(lineHeight);
                // 记录当前行的Views
                mAllViews.add(lineViews);

                // 新行的行宽和行高
                lineWidth = 0;
                lineHeight = childHeight + lp.topMargin + lp.bottomMargin;
                // 新行的View集合
                lineViews = new ArrayList<View>();
            }

        }

        // 处理最后一行
        mLineHeight.add(lineHeight);
        mAllViews.add(lineViews);

        // 设置子View的位置

        int left = getPaddingLeft();
        int top = getPaddingTop();

        // 行数
        int lineNum = mAllViews.size();
        for (int i = 0; i < lineNum; i++)
        {
            // 当前行的所有的View
            lineViews = mAllViews.get(i);
            lineHeight = mLineHeight.get(i);
            for (int j = 0; j < lineViews.size(); j++)
            {
                View child = lineViews.get(j);
                if(child.getVisibility() == View.INVISIBLE ){
                    continue;
                }
                MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
                int lc = left+lp.leftMargin;
                int tc = top+lp.topMargin;
                int rc = lc+child.getMeasuredWidth();
                int bc = tc+child.getMeasuredHeight();
                // 为子View进行布局
                child.layout(lc,tc,rc,bc);

                left += child.getMeasuredWidth() + lp.leftMargin+ lp.rightMargin;
            }
            top += lineHeight ;
            left = getPaddingLeft() ;
        }


    }

    /**
     * 确定宽高
     * @param widthMeasureSpec  widthMeasureSpec
     * @param heightMeasureSpec heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        // // 获得它的父容器为它设置的测量模式和大小
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);

        // 用于warp_content情况下，来记录父view宽和高
        int width = 0;
        int height = 0;

        // 取每一行宽度的最大值
        int lineWidth = 0;
        // 每一行的高度累加
        int lineHeight = 0;

        // 获得子view的个数
        int cCount = getChildCount();

        for (int i = 0; i < cCount; i++) {
            View child = getChildAt(i);
            // 测量子View的宽和高（子view在布局文件中是wrap_content）
            measureChild(child,widthMeasureSpec,heightMeasureSpec);
            // 得到LayoutParams
            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
            // 根据测量宽度加上Margin值算出子view的实际宽度（上文中有说明）
            int childWidth = child.getMeasuredWidth()+lp.rightMargin+lp.leftMargin;
            // 根据测量高度加上Margin值算出子view的实际高度
            int childHeight = child.getMeasuredHeight()+lp.topMargin+lp.bottomMargin;
            if(lineWidth+childWidth>sizeWidth-getPaddingLeft()-getPaddingRight()){
                // 父view宽度=以前父view宽度、当前行宽的最大值
                width = Math.max(width, lineWidth);
                // 换行了，当前行宽=第一个view的宽度
                lineWidth = childWidth;
                // 父view的高度=各行高度之和
                height += lineHeight;
                //换行了，当前行高=第一个view的高度
                lineHeight = childHeight;
            }else{
                // 叠加行宽
                lineWidth+=childWidth;
                lineHeight = Math.max(lineHeight,childHeight);
            }
            if(i==cCount-1){
                // 最后一个控件
                width = Math.max(lineWidth, width);
                height += lineHeight;
            }
        }
        /**
         * EXACTLY对应match_parent 或具体值
         * AT_MOST对应wrap_content
         * 在FlowLayout布局文件中
         * android:layout_width="fill_parent"
         * android:layout_height="wrap_content"
         *
         * 如果是MeasureSpec.EXACTLY则直接使用父ViewGroup传入的宽和高，否则设置为自己计算的宽和高。
         */

        setMeasuredDimension(
                modeWidth==MeasureSpec.EXACTLY?sizeWidth:width+getPaddingRight()+getPaddingLeft(),
                modeHeight==MeasureSpec.EXACTLY?sizeHeight:height+getPaddingTop()+getPaddingBottom()
        );

    }

    //存储所有的View
    private List<List<View>> mAllViews = new ArrayList<List<View>>();
    //存储每一行的高度
    private List<Integer> mLineHeight = new ArrayList<Integer>();

    /**
     * 因为我们只需要支持margin，所以直接使用系统的MarginLayoutParams
     */
    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs)
    {
        return new MarginLayoutParams(getContext(), attrs);
    }
}
