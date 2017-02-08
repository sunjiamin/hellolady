package com.sun.hellolady.demo.MeiNv;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.sun.hellolady.R;
import com.support.util.imageloader.ImageLoader;
import com.support.util.photoview.PhotoView;

public class ImageDetail extends AppCompatActivity {
String mImageUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);
        Bundle extras = getIntent().getExtras();
        mImageUrl = extras.getString("Image_Url");
        PhotoView p = (PhotoView)findViewById(R.id.img_detail);
        ImageLoader.loadImage(this,
                mImageUrl, p, null);

    }
}
