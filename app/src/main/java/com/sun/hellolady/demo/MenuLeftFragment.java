package com.sun.hellolady.demo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sun.hellolady.R;

/**
 * 项目名称：hellolady
 * 类描述：
 * 创建人：Jiamin.Sun
 * 创建时间：3/18/2016 1:51 PM
 * 修改人：Jiamin.Sun
 * 修改时间：3/18/2016 1:51 PM
 * 修改备注：
 */
public class MenuLeftFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
          return inflater.inflate(R.layout.fragment_planet, container, false);
    }
}
