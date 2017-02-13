package com.sun.hellolady.demo.AidlDemo.aidl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.sun.hellolady.R;

public class AIDLActivity extends AppCompatActivity {
    IService RemoteService; //监听服务
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            RemoteService = IService.Stub.asInterface(service);
            try {
                String jojo = RemoteService.hello("jojo");
                Toast.makeText(AIDLActivity.this,jojo, Toast.LENGTH_LONG).show();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl);
        Intent i = new Intent( );
        i.setAction("android.intent.action.AIDLService");
        bindService(i,serviceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(serviceConnection);
        serviceConnection = null;
    }
}
