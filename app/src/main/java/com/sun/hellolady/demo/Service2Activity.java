package com.sun.hellolady.demo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.sun.hellolady.R;
import com.support.util.common.LogUtil;

public class Service2Activity extends AppCompatActivity {

    private Intent serviceIntent;


    private ServiceDemo.MyBind  myBinder ;
    private ServiceDemo mService;
    private  MyServiceConnection myServiceConnection = new MyServiceConnection();

    public static final String TAG = "Service3Activity";
    public class MyServiceConnection implements ServiceConnection {


        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myBinder = (ServiceDemo.MyBind) service;
            mService = myBinder.getService();
            LogUtil.d(TAG, "onServiceConnected");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            LogUtil.d(TAG, "onServiceDisconnected");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(myServiceConnection != null) {
            unbindService(myServiceConnection);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        //onstart
        (findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                serviceIntent = new Intent(Service2Activity.this, ServiceDemo.class);
                startService(serviceIntent);
            }
        });
        //onstop
        (findViewById(R.id.button2)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              stopService(serviceIntent);
            }
        });

        (findViewById(R.id.button5)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Service2Activity.this, ServiceDemo.class);
                bindService(intent, myServiceConnection, Context.BIND_AUTO_CREATE);
            }
        });
        (findViewById(R.id.button6)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unbindService(myServiceConnection);
            }
        });

    }



}
