package com.sun.hellolady;

import android.app.Application;
import android.app.IntentService;

import com.support.util.imageloader.ImageLoader;

/**
 * 项目名称：hellolady
 * 类描述：
 * 创建人：Jiamin.Sun
 * 创建时间：3/23/2016 11:29 AM
 * 修改人：Jiamin.Sun
 * 修改时间：3/23/2016 11:29 AM
 * 修改备注：
 */
public class App extends Application {
    @Override
    public void onCreate() {
        ImageLoader.init(getApplicationContext());
        //OkHttpUtils.getInstance().init(null);
        super.onCreate();

    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}
