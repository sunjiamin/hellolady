package com.sun.hellolady.demo.behavior;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by sunjiamin on 2017/2/8.
 */

public class FooterBehaviorDependAppBar extends CoordinatorLayout.Behavior<View>  {

    public FooterBehaviorDependAppBar(Context context, AttributeSet attrs) {
          super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        //Log.e("sjm",dependency.getClass().getName());
        return dependency instanceof AppBarLayout;
    }


    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        float translationY = Math.abs(dependency.getTop());
        Log.e("sjm","dependency.getTop():"+dependency.getTop());
        child.setTranslationY(translationY);
        return true;
    }

}
