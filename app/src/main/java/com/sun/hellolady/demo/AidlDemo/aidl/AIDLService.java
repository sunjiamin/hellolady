package com.sun.hellolady.demo.AidlDemo.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

/**
 * Created by sunjiamin on 2017/2/10.
 */

public class AIDLService extends Service {


    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new IService.Stub(){

            @Override
            public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

            }

            @Override
            public String hello(String name) throws RemoteException {
                  return "hello"+name;
            }
        };
    }
}
