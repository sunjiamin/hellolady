package com.sun.hellolady.demo.Recycler;

import android.content.Context;

import com.sun.hellolady.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 项目名称：hellolady
 * 类描述：
 * 创建人：Jiamin.Sun
 * 创建时间：3/9/2016 10:00 AM
 * 修改人：Jiamin.Sun
 * 修改时间：3/9/2016 10:00 AM
 * 修改备注：
 */
public class DataManager {

    public static List<DemoModel> loadData(Context context) {
        return loadData(context, 200);
    }

    /**
     * 模拟加载数据的操作
     */
    public static List<DemoModel> loadData(Context context, int num) {
        List<String> originList = Arrays.asList(context.getResources().getStringArray(R.array.country_names));
        List<DemoModel> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            int type = (int) (Math.random() * 3);
            //Log.d(TAG, "type = " + type);
            DemoModel model = new DemoModel();
            switch (type) {
                case 0:
                    model.type = "text";
                    model.content = originList.get(i);
                    break;
                case 1:
                    model.type = "button";
                    model.content = originList.get(i);
                    break;
                case 2:
                    model.type = "list";
                    //model.content = String.valueOf(R.mipmap.ic_launcher);
                    break;
                default:
            }
            list.add(model);
        }
        return list;
    }
}
