package com.sun.hellolady.demo.PhotoView;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.sun.hellolady.R;
import com.sun.hellolady.demo.MeiNv.ImageDetail;
import com.support.util.imageloader.ImageLoader;
import com.support.util.imageloader.ImageLoadingListener;
import com.support.util.photoview.PhotoView;


public class PicassoSampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);

        PhotoView photoView = (PhotoView) findViewById(R.id.iv_photo);

//        Picasso.with(this)
//                .load("http://pbs.twimg.com/media/Bist9mvIYAAeAyQ.jpg")
//                .into(photoView);

        ImageLoader.loadImage(this,
                "http://pbs.twimg.com/media/Bist9mvIYAAeAyQ.jpg", photoView, null, new ImageLoadingListener() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {

                    }
                });

        photoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PicassoSampleActivity.this, ImageDetail.class);
                intent.putExtra("Image_Url", "http://pbs.twimg.com/media/Bist9mvIYAAeAyQ.jpg");


                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });
    }
}
