package com.sun.hellolady.demo.Recycler.RecItem;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.sun.hellolady.R;
import com.sun.hellolady.demo.Recycler.DemoModel;
import com.support.util.MyRecyclerView.item.AdapterItem;

/**
 * 项目名称：hellolady
 * 类描述：
 * 创建人：Jiamin.Sun
 * 创建时间：3/9/2016 10:08 AM
 * 修改人：Jiamin.Sun
 * 修改时间：3/9/2016 10:08 AM
 * 修改备注：
 */
public class TextItem implements AdapterItem<DemoModel> {

    private static final String TAG = "TextItem";

    @Override
    public int getLayoutResId() {
        return R.layout.demo_item_text;
    }

    TextView textView;

    @Override
    public void bindViews(View root) {
        textView = (TextView) root.findViewById(R.id.textView);

    }

    @Override
    public void setViews() {
        //Log.d(TextItem.class.getSimpleName(), "setViews--------->");
    }

    DemoModel demoModel;
    @Override
    public void handleData(final Context t,DemoModel model, int position) {
        textView.setText(model.content);
        demoModel= model;

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(t, demoModel.content+"Child", Toast.LENGTH_LONG).show();
            }
        });

    }
    @Override
    public void setItemClick(final Context c,View root) {

        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(c, demoModel.content, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void setItemClick(Context c, View v, DemoModel demoModel) {

    }


}
