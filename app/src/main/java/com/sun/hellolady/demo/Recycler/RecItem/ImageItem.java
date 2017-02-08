package com.sun.hellolady.demo.Recycler.RecItem;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.sun.hellolady.R;
import com.sun.hellolady.demo.Recycler.DemoModel;
import com.support.util.MyRecyclerView.item.AdapterItem;

/**
 * 项目名称：hellolady
 * 类描述：
 * 创建人：Jiamin.Sun
 * 创建时间：3/9/2016 10:57 AM
 * 修改人：Jiamin.Sun
 * 修改时间：3/9/2016 10:57 AM
 * 修改备注：
 */
public class ImageItem  implements AdapterItem<DemoModel> {

    @Override
    public int getLayoutResId() {
        return  R.layout.demo_item_image;
    }

    ImageView image;
    @Override
    public void bindViews(View root) {
          image = (ImageView)root.findViewById(R.id.img);

    }

    @Override
    public void setViews() {

    }

    @Override
    public void handleData(Context t,DemoModel demoModel, int position) {
        image.setImageResource(R.mipmap.ic_launcher);
    }

    @Override
    public void setItemClick(final Context c, View v) {
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(c, "image", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void setItemClick(Context c, View v, DemoModel demoModel) {

    }
}
