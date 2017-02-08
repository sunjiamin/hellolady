package com.sun.hellolady.demo.Recycler.RecItem;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sun.hellolady.R;
import com.sun.hellolady.demo.APITestActivity;
import com.sun.hellolady.demo.Charts.ChartDemoActivity;
import com.sun.hellolady.demo.IM.ImDemoActivity;
import com.sun.hellolady.demo.Loading.LoadingActivity;
import com.sun.hellolady.demo.MeiNv.Main3Activity;
import com.sun.hellolady.demo.PhotoView.PicassoSampleActivity;
import com.sun.hellolady.demo.PhotoView.RotationSampleActivity;
import com.sun.hellolady.demo.PhotoView.SimpleSampleActivity;
import com.sun.hellolady.demo.PhotoView.ViewPagerActivity;
import com.sun.hellolady.demo.Recycler.DemoModel;
import com.sun.hellolady.demo.Recycler.RecyclerActivity;
import com.sun.hellolady.ui.MainActivity;
import com.support.util.MyRecyclerView.item.AdapterItem;

/**
 * 项目名称：hellolady
 * 类描述：
 * 创建人：Jiamin.Sun
 * 创建时间：5/16/2016 11:12 AM
 * 修改人：Jiamin.Sun
 * 修改时间：5/16/2016 11:12 AM
 * 修改备注：
 */
public class ListItem implements AdapterItem<DemoModel> {


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
            "RecyclerActivity"};

    @Override
    public int getLayoutResId() {
        return  R.layout.demo_item_recyclerview;
    }

    RecyclerView recyclerView;
    @Override
    public void bindViews(View root) {
        recyclerView = (RecyclerView)root.findViewById(R.id.rec_list);


    }

    @Override
    public void setViews() {

    }

    @Override
    public void handleData(Context t,DemoModel demoModel, int position) {
        recyclerView.setLayoutManager(new LinearLayoutManager(t));
        recyclerView.setAdapter(new ItemAdapter());

    }

    @Override
    public void setItemClick(Context c, View v) {

    }

    @Override
    public void setItemClick(Context c, View v, DemoModel demoModel) {

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

    private static class ItemViewHolder extends RecyclerView.ViewHolder {

        public static ItemViewHolder newInstance(ViewGroup parent) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_list_item, parent, false);
            return new ItemViewHolder(view);
        }

        public TextView mTextTitle;

        public ItemViewHolder(View view) {
            super(view);
            mTextTitle = (TextView) view.findViewById(R.id.title);
        }

        private void bind(String title) {
            mTextTitle.setText(title);
        }
    }
}
