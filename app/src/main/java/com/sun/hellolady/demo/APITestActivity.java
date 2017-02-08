package com.sun.hellolady.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.sun.hellolady.R;
import com.sun.hellolady.demo.MeiNv.StringCallback;
import com.support.util.okhttp.OkHttpUtils;

import okhttp3.Call;
import okhttp3.Response;

public class APITestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apitest);

        Button bt = (Button)findViewById(R.id.button9);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpUtils
                        .get()//
                        .url("http://180.169.1.58:351/ib_tranx_req.asp?uid=ggg111&sessionid=ccb17a861107a78704d696f6e33fc878&termid=77025")//
                        .build()//
                        .execute(new StringCallback() {
                            @Override
                            public String parseNetworkResponse(Response response) throws Exception {
                                return super.parseNetworkResponse(response);
                            }

                            @Override
                            public void onError(Call call, Exception e) {
                                Toast.makeText(APITestActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
                                super.onError(call, e);
                            }

                            @Override
                            public void onResponse(final String response) {
                                Toast.makeText(APITestActivity.this,response,Toast.LENGTH_LONG).show();

                            }
                        });
            }
        });

        Button bt2 = (Button)findViewById(R.id.button10);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpUtils
                        .get()//
                        .url("http://180.169.1.58:351/ib_tranx_req.asp?uid=ggg111&sessionid=ccb17a861107a78704d696f6e33fc878&termid=77025")//
                        //.addParams("num","10")
                        //.addHeader("apikey", "74863574a3c1906a298abcc1f17c5d16")//
                        .build()//
                        .execute(new StringCallback() {
                            @Override
                            public String parseNetworkResponse(Response response) throws Exception {
                                return super.parseNetworkResponse(response);
                            }

                            @Override
                            public void onError(Call call, Exception e) {
                                Toast.makeText(APITestActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
                                super.onError(call, e);
                            }

                            @Override
                            public void onResponse(final String response) {
                                Toast.makeText(APITestActivity.this,response,Toast.LENGTH_LONG).show();

                            }
                        });
            }
        });

    }
}
