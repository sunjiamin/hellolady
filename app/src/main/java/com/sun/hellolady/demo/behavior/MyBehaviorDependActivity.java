package com.sun.hellolady.demo.behavior;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.sun.hellolady.R;

public class MyBehaviorDependActivity extends AppCompatActivity {

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
            "LookWifiPasswordActivity"};

    RecyclerView rvBehavior;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_behavior_depend);

        rvBehavior = (RecyclerView)findViewById(R.id.rv_behavior);
        rvBehavior.setLayoutManager(new LinearLayoutManager(MyBehaviorDependActivity.this));

        rvBehavior.setAdapter(new ItemAdapter());
    }


    private static class ItemAdapter extends RecyclerView.Adapter<MyBehaviorDependActivity.ItemViewHolder> {
        @Override
        public MyBehaviorDependActivity.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return MyBehaviorDependActivity.ItemViewHolder.newInstance(parent);
        }

        @Override
        public void onBindViewHolder(final MyBehaviorDependActivity.ItemViewHolder holder, final int position) {
            holder.bind(options[position]);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


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

        private static Context c;
        public static MyBehaviorDependActivity.ItemViewHolder newInstance(ViewGroup parent) {
            c =parent.getContext();
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_list_item, parent, false);
            return new MyBehaviorDependActivity.ItemViewHolder(view);
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
            // item_l_rec.setAdapter(new ArrayAdapter<String>(c,
            //   android.R.layout.simple_list_item_1, strs));
        }
    }
}
