package com.sun.hellolady.demo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import com.nineoldandroids.view.ViewHelper;
import com.sun.hellolady.R;

public class DrawerLayoutDemoActivity extends FragmentActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_drawer_layout_demo);
        mDrawerLayout= (DrawerLayout)findViewById(R.id.drawer_layout);


        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED,
                Gravity.RIGHT);

        mDrawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                View mContent = mDrawerLayout.getChildAt(0);
                View mMenu = drawerView;
                float scale = 1 - slideOffset;
                float rightScale = 0.8f + scale * 0.2f;

                if (drawerView.getTag().equals("LEFT")) {

                    float leftScale = 1 - 0.3f * scale;

                    ViewHelper.setScaleX(mMenu, leftScale);
                    ViewHelper.setScaleY(mMenu, leftScale);
                    ViewHelper.setAlpha(mMenu, 0.6f + 0.4f * (1 - scale));
                    ViewHelper.setTranslationX(mContent,
                            mMenu.getMeasuredWidth() * (1 - scale));
                    ViewHelper.setPivotX(mContent, 0);
                    ViewHelper.setPivotY(mContent,
                            mContent.getMeasuredHeight() / 2);
                    mContent.invalidate();
                    ViewHelper.setScaleX(mContent, rightScale);
                    ViewHelper.setScaleY(mContent, rightScale);
                } else {
                    ViewHelper.setTranslationX(mContent,
                            -mMenu.getMeasuredWidth() * slideOffset);
                    ViewHelper.setPivotX(mContent, mContent.getMeasuredWidth());
                    ViewHelper.setPivotY(mContent,
                            mContent.getMeasuredHeight() / 2);
                    mContent.invalidate();
                    ViewHelper.setScaleX(mContent, rightScale);
                    ViewHelper.setScaleY(mContent, rightScale);
                }
            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                mDrawerLayout.setDrawerLockMode(
                        DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.RIGHT);
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });

        myTouch();
    }



    public void myTouch(){
        View mContent = mDrawerLayout.getChildAt(0);
        mContent.setLongClickable(true);
        mContent.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d("DrawerLayoutDemo", "onTouch:" + event.getAction());
                final int action = event.getAction();
                //
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        //当手指按下的时候
                        x1 = event.getX();
                        y1 = event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        //当手指离开的时候
                        x2 = event.getX();
                        y2 = event.getY();
                        Log.d("DrawerLayoutDemo", "onTouch:" + (x2 - x1));
                        if (x2 - x1 > 50) {

                            OpenRightMenu(v);
                            return true;
                        }
                        if (x1 - x2 > 50) {

                            CloseRightMenu(v);
                            return true;
                        }

                        break;
                }
                return false;
            }
        });

    }


    public void OpenRightMenu(View view)
    {
        mDrawerLayout.openDrawer(Gravity.LEFT);
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED,
                Gravity.LEFT);
    }

    public void CloseRightMenu(View view)
    {
        mDrawerLayout.closeDrawers();
//        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED,
//                Gravity.LEFT);
    }


    //手指按下的点为(x1, y1)手指离开屏幕的点为(x2, y2)
    float x1 = 0;
    float x2 = 0;

    float y1 = 0;
    float y2 = 0;



    /**
     *
     * 如果 return true，事件会分发给当前 View 并由 dispatchTouchEvent 方法进行消费，同时事件会停止向下传递；
     * 如果 return false，事件分发分为两种情况：
     *
     * 如果当前 View 获取的事件直接来自 Activity，则会将事件返回给 Activity 的 onTouchEvent 进行消费；
     *  如果当前 View 获取的事件来自外层父控件，则会将事件返回给父 View 的  onTouchEvent 进行消费。
     *
     * 如果返回系统默认的 super.dispatchTouchEvent(ev)，事件会自动的分发给当前 View 的 onInterceptTouchEvent 方法。
     *
     * @param event
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.d("DrawerLayoutDemo","dispatchTouchEvent:"+event.getAction());
        final int action = event.getAction();
        //
        switch (action){
            case MotionEvent.ACTION_DOWN:
                //当手指按下的时候
                x1 = event.getX();
                y1 = event.getY();
                break;
            case  MotionEvent.ACTION_UP:
                //当手指离开的时候
                x2 = event.getX();
                y2 = event.getY();
                if(y1 - y2 > 50) {
                    Log.d("DrawerLayoutDemo","dispatchTouchEvent:向上滑-->"+(y1 - y2) );
                    return false;
                    //Toast.makeText(DrawerLayoutDemoActivity.this, "向上滑", Toast.LENGTH_SHORT).show();
                } else if(y2 - y1 > 50) {
                    Log.d("DrawerLayoutDemo","dispatchTouchEvent:向下滑-->"+(y2 - y1) );
                    return false;
                    //Toast.makeText(DrawerLayoutDemoActivity.this, "向下滑", Toast.LENGTH_SHORT).show();
                } else if(x1 - x2 > 50) {
                    Log.d("DrawerLayoutDemo","dispatchTouchEvent:向左滑-->"+(x1 - x2));
                    return false;
                   // Toast.makeText(DrawerLayoutDemoActivity.this, "向左滑", Toast.LENGTH_SHORT).show();
                } else if(x2 - x1 > 50) {
                    Log.d("DrawerLayoutDemo","dispatchTouchEvent:向右滑-->"+ (x2 - x1));
                    return false;
                    //Toast.makeText(DrawerLayoutDemoActivity.this, "向右滑", Toast.LENGTH_SHORT).show();
                }
                break;
        }

        return super.dispatchTouchEvent(event);
    }

    /**
     * Fragment that appears in the "content_frame", shows a planet
     */
    public static class PlanetFragment extends Fragment {
        public static final String ARG_PLANET_NUMBER = "planet_number";
        public PlanetFragment() {
            // Empty constructor required for fragment subclasses
        }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_planet, container, false);
            int i = getArguments().getInt(ARG_PLANET_NUMBER);

            ((ImageView) rootView.findViewById(R.id.image)).setImageResource(R.drawable.ic_gender_male);
            getActivity().setTitle("sss");
            return rootView;
        }
    }
}
