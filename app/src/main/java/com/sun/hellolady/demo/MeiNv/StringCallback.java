package com.sun.hellolady.demo.MeiNv;

import com.support.util.okhttp.callback.Callback;

import okhttp3.Call;
import okhttp3.Response;

/**
 * 项目名称：hellolady
 * 类描述：
 * 创建人：Jiamin.Sun
 * 创建时间：4/11/2016 8:45 PM
 * 修改人：Jiamin.Sun
 * 修改时间：4/11/2016 8:45 PM
 * 修改备注：
 */
public abstract  class StringCallback extends Callback<String> {
    @Override
    public String parseNetworkResponse(Response response) throws Exception {
      return response.body().string();

    }

    @Override
    public void onError(Call call, Exception e) {

    }

    @Override
    public void onResponse(String response) {

    }
}
