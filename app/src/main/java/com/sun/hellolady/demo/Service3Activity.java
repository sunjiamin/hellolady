package com.sun.hellolady.demo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.sun.hellolady.R;
import com.support.util.common.LogUtil;

public class Service3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service3);
        (findViewById(R.id.button3)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Service3Activity.this, ServiceDemo.class);
                bindService(intent, myServiceConnection, Context.BIND_AUTO_CREATE);
            }
        });
        (findViewById(R.id.button4)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unbindService(myServiceConnection);
            }
        });

        //onstart
        (findViewById(R.id.button7)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                serviceIntent = new Intent(Service3Activity.this, ServiceDemo.class);
                startService(serviceIntent);
            }
        });
        //onstop
        (findViewById(R.id.button8)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(serviceIntent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(myServiceConnection!=null){
            unbindService(myServiceConnection);
        }

    }

    private Intent serviceIntent;

    private ServiceDemo.MyBind  myBinder ;
    private ServiceDemo mService;
    private  MyServiceConnection myServiceConnection = new MyServiceConnection();

    public static final String TAG = "Service3Activity";
    public class MyServiceConnection implements ServiceConnection{


        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myBinder= (ServiceDemo.MyBind) service;
            mService=myBinder.getService();
            LogUtil.d(TAG, "onServiceConnected");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            LogUtil.d(TAG, "onServiceDisconnected");
        }
    }
}
