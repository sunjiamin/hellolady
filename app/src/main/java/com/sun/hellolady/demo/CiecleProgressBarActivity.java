package com.sun.hellolady.demo;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.sun.hellolady.R;
import com.sun.hellolady.demo.widget.CircleProgressBar;

public class CiecleProgressBarActivity extends AppCompatActivity {
    CircleProgressBar arcProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ciecle_progress_bar);
        arcProgressBar = (CircleProgressBar) findViewById(R.id.cirPro);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //arcProgressBar.setMaxValue(100);
                try {
                    Thread.sleep(1000);
                    arcProgressBar.setMaxValue(20);
                    arcProgressBar.setProgress(20);
                    Thread.sleep(1000);
                    arcProgressBar.setMaxValue(30);
                    arcProgressBar.setProgress(40);
                    Thread.sleep(1000);
                    arcProgressBar.setMaxValue(40);
                    arcProgressBar.setProgress(60);
                    Thread.sleep(1000);
                    arcProgressBar.setMaxValue(80);
                    arcProgressBar.setProgress(80);
                    Thread.sleep(1000);
                    arcProgressBar.setMaxValue(100);
                    arcProgressBar.setProgress(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        },1000);
    }
}
