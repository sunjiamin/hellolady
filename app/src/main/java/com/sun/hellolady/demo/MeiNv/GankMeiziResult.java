package com.sun.hellolady.demo.MeiNv;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by 82328 on 2016/8/5.
 */

public class GankMeiziResult {

    public boolean error;

    @SerializedName("results")
    public List<GankMeiziInfo> gankMeizis;
}
