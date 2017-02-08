package com.sun.hellolady.demo.Recycler;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sun.hellolady.R;
import com.sun.hellolady.demo.Recycler.RecItem.ImageItem;
import com.sun.hellolady.demo.Recycler.RecItem.ListItem;
import com.sun.hellolady.demo.Recycler.RecItem.TextItem;
import com.support.util.MyRecyclerView.CommonRecyclerAdapter;
import com.support.util.MyRecyclerView.item.AdapterItem;
import com.support.util.MyRecyclerView.util.IAdapter;

import java.util.List;

public class RecyclerActivity extends AppCompatActivity {
    RecyclerView rec;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        rec = (RecyclerView)findViewById(R.id.rec_test);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL,false);

        linearLayoutManager.setRecycleChildrenOnDetach(true);

        rec.setLayoutManager(linearLayoutManager);
        //放一个默认数据
        rec.setAdapter(getAdapter(null));

        List<DemoModel> mData = DataManager.loadData(this);
        ((IAdapter)rec.getAdapter()).setData(mData);//设置新数据
        rec.getAdapter().notifyDataSetChanged();//更新数据
    }

    private CommonRecyclerAdapter<DemoModel> getAdapter(List<DemoModel> data) {
        return new CommonRecyclerAdapter<DemoModel>(data,RecyclerActivity.this){
            @Override
            public Object getItemType(DemoModel demoModel) {
                return demoModel.type;
            }

            @NonNull
            @Override
            public AdapterItem createItem(Object type) {
                switch (((String) type)) {
                    case "text":
                        return new TextItem();
                    case "button":
                        return new ListItem();
                    case "image":
                        return new ImageItem();
                    case "list":
                        return new ListItem();
                    default:
                        throw new IllegalArgumentException("不合法的type");
                }
            }
        };

    }
}
