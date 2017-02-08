package com.sun.hellolady.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.squareup.picasso.Picasso;
import com.sun.hellolady.R;
import com.sun.hellolady.demo.widget.PieImageView;

public class PieImageViewActivity extends AppCompatActivity {

    PieImageView pieImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_image_view);
        pieImageView = (PieImageView)findViewById(R.id.pieImage);

        Picasso.with(this).load("")
                .into(pieImageView);

    }
}
