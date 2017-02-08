package com.sun.hellolady.demo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.support.util.common.LogUtil;
import com.support.util.okhttp.utils.L;

/**
 * 项目名称：HelloLady
 * 类描述：
 * 创建人：Jiamin.Sun
 * 创建时间：2/26/2016 2:25 PM
 * 修改人：Jiamin.Sun
 * 修改时间：2/26/2016 2:25 PM
 * 修改备注：
 */
public class ServiceDemo  extends Service{
    public static final String TAG = "MyService";

    private MyBind mBind = new MyBind();
    public class MyBind extends Binder{
        ServiceDemo getService(){
            return ServiceDemo.this;
        }
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        LogUtil.d(TAG,"------>onBind"+" onBind"+this);

        return mBind;
    }

    @Override
    public void onCreate() {
        LogUtil.d(TAG, "------>onCreate"+" onCreate"+this);
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        LogUtil.d(TAG, "------>onDestroy"+" onDestroy"+this);
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        LogUtil.d(TAG, "------>onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        L.e("------>onRebind");
        super.onRebind(intent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtil.d(TAG, "------>onStartCommand"+" SerciceName"+this);
        return super.onStartCommand(intent, flags, startId);
    }
}
