package com.sun.support.demo;

import com.support.util.okhttp.OkHttpUtils;

import java.io.IOException;

import okhttp3.Response;

/**
 * Created by Administrator on 2016/1/20.
 */
public class OkHttpDemo {
    public   String get(){
        String url = "http://www.csdn.net/";
        try {
            Response ss =  OkHttpUtils
                      .get().tag(this)
                     .url(url)
                     .addParams("username", "hyman")
                     .addParams("password", "123")
                     .build()
                     .execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    void init(){
        
//        OkHttpClient client =
//                OkHttpUtils.getInstance().setConnectTimeout();
        //client.

          OkHttpUtils.getInstance().init(null);
    }
}
