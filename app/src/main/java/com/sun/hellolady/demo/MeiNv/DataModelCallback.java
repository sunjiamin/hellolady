package com.sun.hellolady.demo.MeiNv;

import com.google.gson.Gson;
import com.support.util.okhttp.callback.Callback;

import okhttp3.Call;
import okhttp3.Response;

/**
 * 项目名称：hellolady
 * 类描述：
 * 创建人：Jiamin.Sun
 * 创建时间：3/23/2016 3:13 PM
 * 修改人：Jiamin.Sun
 * 修改时间：3/23/2016 3:13 PM
 * 修改备注：
 */
public abstract  class DataModelCallback extends Callback<GankMeiziResult> {
    @Override
    public GankMeiziResult parseNetworkResponse(Response response) throws Exception {
        String string = response.body().string();
        GankMeiziResult user = new Gson().fromJson(string, GankMeiziResult.class);
        return user;
    }

    @Override
    public void onError(Call call, Exception e) {

    }

    @Override
    public void onResponse(GankMeiziResult response) {

    }
}
