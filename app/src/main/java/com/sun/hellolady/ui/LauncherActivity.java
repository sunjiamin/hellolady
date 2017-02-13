package com.sun.hellolady.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.sun.hellolady.R;
import com.sun.hellolady.demo.APITestActivity;
import com.sun.hellolady.demo.AidlDemo.aidl.AIDLActivity;
import com.sun.hellolady.demo.Camera.PreCameraActivity;
import com.sun.hellolady.demo.Charts.ChartDemoActivity;
import com.sun.hellolady.demo.CiecleProgressBarActivity;
import com.sun.hellolady.demo.Clock.WatchBoardActivity;
import com.sun.hellolady.demo.IM.ImDemoActivity;
import com.sun.hellolady.demo.Loading.LoadingActivity;
import com.sun.hellolady.demo.MeiNv.Main3Activity;
import com.sun.hellolady.demo.PhotoView.PicassoSampleActivity;
import com.sun.hellolady.demo.PhotoView.RotationSampleActivity;
import com.sun.hellolady.demo.PhotoView.SimpleSampleActivity;
import com.sun.hellolady.demo.PhotoView.ViewPagerActivity;
import com.sun.hellolady.demo.PlaySound.PlaySoundActivity;
import com.sun.hellolady.demo.Recycler.RecyclerActivity;
import com.sun.hellolady.demo.SlidingPanel.SlidingPanelActivity;
import com.sun.hellolady.demo.SlidingPanel.TouchActivity;
import com.sun.hellolady.demo.SlidingPanel.slidingdrawer;
import com.sun.hellolady.demo.WidgetActivity.FlowLayoutActivity;
import com.sun.hellolady.demo.WidgetActivity.FollowViewActivity;
import com.sun.hellolady.demo.Wifi.LookWifiPasswordActivity;
import com.sun.hellolady.demo.behavior.MyBehaviorActivity;
import com.sun.hellolady.demo.behavior.MyBehaviorDependActivity;
import com.support.util.common.T;
import com.support.util.netstatus.NetChangeObserver;
import com.support.util.netstatus.NetStateReceiver;
import com.support.util.netstatus.NetUtils;
import com.support.util.widget.PopZoomGallery.ScreenUtil;

public class LauncherActivity extends AppCompatActivity {

    public static final String[] options = {"Simple Sample",
            "ViewPager Sample",
            "Rotation Sample",
            "Picasso Sample",
            "MainActiity",
            "美女",
            "Loading",
            "APITest",
            "LineChart",
            "IM",
            "RecyclerActivity",
    "CiecleProgressBarActivity",
    "SlidingPanelActivity",
            "slidingdrawer",
    "playSound",
    "TouchActivity",
    "PreCameraActivity",
    "WatchBoardActivity",
    "LookWifiPasswordActivity",
    "MyBehaviorActivity",
    "MyBehaviorDependActivity",
    "FollowViewActivity",
    "FlowLayoutActivity",
    "AIDLActivity"};

    NetChangeObserver mNetChangeObserver;

    protected Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        mToolbar =  (Toolbar)findViewById( R.id.common_toolbar);
        if (null != mToolbar) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        setImmerseLayout(mToolbar);




        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ItemAdapter());

        mNetChangeObserver = new NetChangeObserver() {
            @Override
            public void onNetConnected(NetUtils.NetType type) {
                super.onNetConnected(type);
                T.showShort(LauncherActivity.this,"Con:"+type.name());
            }

            @Override
            public void onNetDisConnect() {
                super.onNetDisConnect();
                //onNetworkDisConnected();
                T.showShort(LauncherActivity.this,"连接断开");
            }
        };

        NetStateReceiver.registerObserver(mNetChangeObserver);

    }

    protected void setImmerseLayout(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
                /*window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);*/
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            int statusBarHeight = ScreenUtil.getStatusBarHeight(this.getBaseContext());
            view.setPadding(0, statusBarHeight, 0, 0);
        }
    }


    private static class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder> {
        @Override
        public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return ItemViewHolder.newInstance(parent);
        }

        @Override
        public void onBindViewHolder(final ItemViewHolder holder, final int position) {
            holder.bind(options[position]);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Class c = null;

                    switch (position) {
                        default:
                        case 0:
                            c = SimpleSampleActivity.class;
                            break;
                        case 1:
                            c = ViewPagerActivity.class;
                            break;
                        case 2:
                            c = RotationSampleActivity.class;
                            break;
                        case 3:
                            c = PicassoSampleActivity.class;
                            break;
                        case 4:
                            c = MainActivity.class;
                            break;
                        case 5:
                            c = Main3Activity.class;
                            break;
                        case 6:
                            c = LoadingActivity.class;
                            break;
                        case 7:
                            c = APITestActivity.class;
                            break;
                        case 8:
                            c = ChartDemoActivity.class;
                            break;
                        case 9:

                           // c = ImActivity.class;
                            c = ImDemoActivity.class;
                            break;
                        case 10:
                            c = RecyclerActivity.class;
                            break;

                        case 11:
                            c = CiecleProgressBarActivity.class;
                            break;
                        case 12:
                            c = SlidingPanelActivity.class;
                            break;
                        case 13:
                            c = slidingdrawer.class;
                            break;
                        case 14:
                            c = PlaySoundActivity.class;
                            break;
                        case 15:
                            c = TouchActivity.class;
                            break;
                        case 16:
                            c = PreCameraActivity.class;
                            break;
                        case 17:
                            c = WatchBoardActivity.class;
                            break;
                        case 18:
                            c = LookWifiPasswordActivity.class;
                            break;

                        case 19:
                            c = MyBehaviorActivity.class;
                            break;
                        case 20:
                            c = MyBehaviorDependActivity.class;
                            break;
                        case 21:
                            c = FollowViewActivity.class;
                            break;
                        case 22:
                            c = FlowLayoutActivity.class;
                            break;
                        case 23:
                            c = AIDLActivity.class;
                            break;

                    }
                    Context context = holder.itemView.getContext();
                    if(c!=null){
                        context.startActivity(new Intent(context, c));
                    }

                }
            });
        }

        @Override
        public int getItemCount() {
            return options.length;
        }
    }

    private static final String[] strs = new String[] {
            "first", "second", "third", "fourth", "fifth"
    };

    private static class ItemViewHolder extends RecyclerView.ViewHolder {

        private static  Context c;
        public static ItemViewHolder newInstance(ViewGroup parent) {
            c =parent.getContext();
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_list_item, parent, false);
            return new ItemViewHolder(view);
        }

        public TextView mTextTitle;
        ListView item_l_rec;
        public ItemViewHolder(View view) {
            super(view);
            mTextTitle = (TextView) view.findViewById(R.id.title);
            item_l_rec = (ListView)view.findViewById(R.id.item_l_rec);
        }

        private void bind(String title) {
            mTextTitle.setText(title);
            item_l_rec.setAdapter(new ArrayAdapter<String>(c,
                    android.R.layout.simple_list_item_1, strs));
        }
    }

}
