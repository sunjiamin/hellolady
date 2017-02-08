package com.sun.hellolady.demo.Loading;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.sun.hellolady.R;
import com.support.util.imageloader.ImageLoader;
import com.support.util.imageloader.ImageLoadingListener;
import com.support.util.loading.VaryViewHelperController;

public class LoadingActivity extends AppCompatActivity {

    VaryViewHelperController mVaryViewHelperController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        final ImageView img = (ImageView)findViewById(R.id.img_Loading);
        mVaryViewHelperController = new VaryViewHelperController(img);

        mVaryViewHelperController.showLoading(null);

        ImageLoader.loadImage(this, "http://img0.imgtn.bdimg.com/it/u=4168762024,922499492&fm=21&gp=0.jpg", img, null, new ImageLoadingListener() {
            @Override
            public void onSuccess() {


                    mVaryViewHelperController.restore();

            }

            @Override
            public void onError() {
                mVaryViewHelperController.showError(null, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ImageLoader.loadImage(LoadingActivity.this, "http://img0.imgtn.bdimg.com/it/u=4168762024,1922499492&fm=21&gp=0.jpg", img);
                        mVaryViewHelperController.restore();
                    }
                });
            }
        });
    }
}
