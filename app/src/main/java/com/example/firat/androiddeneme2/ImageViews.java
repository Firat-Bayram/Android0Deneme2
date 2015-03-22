package com.example.firat.androiddeneme2;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Firat on 8.1.2015.
 */
public class ImageViews extends Activity implements View.OnClickListener {

    ImageView display;
    Helper helper;
    int toPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        helper = Helper.INSTANCE;
        helper.tamEkran(this);
        setContentView(R.layout.imageviews);


        toPhone = R.drawable.wallpapera;

        display = helper.getirView(this, R.id.imageView, ImageView.class);
        ImageView image1 = helper.getirView(this, R.id.imageView1, ImageView.class);
        ImageView image2 = helper.getirView(this, R.id.imageView2, ImageView.class);
        ImageView image3 = helper.getirView(this, R.id.imageView3, ImageView.class);
        ImageView image4 = helper.getirView(this, R.id.imageView4, ImageView.class);
        ImageView image5 = helper.getirView(this, R.id.imageView5, ImageView.class);
        ImageView image6 = helper.getirView(this, R.id.imageView6, ImageView.class);
        Button btnSetWallpaper = helper.getirView(this, R.id.btnSetWallpaper, Button.class);

        image1.setOnClickListener(this);
        image2.setOnClickListener(this);
        image3.setOnClickListener(this);
        image4.setOnClickListener(this);
        image5.setOnClickListener(this);
        image6.setOnClickListener(this);
        btnSetWallpaper.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageView1:
                display.setImageResource(R.drawable.wallpapera);
                toPhone = R.drawable.wallpapera;
                break;
            case R.id.imageView2:
                display.setImageResource(R.drawable.wallpaperb);
                toPhone = R.drawable.wallpaperb;
                break;
            case R.id.imageView3:
                display.setImageResource(R.drawable.wallpaperc);
                toPhone = R.drawable.wallpaperc;
                break;
            case R.id.imageView4:
                display.setImageResource(R.drawable.wallpaperd);
                toPhone = R.drawable.wallpaperd;
                break;
            case R.id.imageView5:
                display.setImageResource(R.drawable.wallpapere);
                toPhone = R.drawable.wallpapere;
                break;
            case R.id.imageView6:
                display.setImageResource(R.drawable.wallpaperf);
                toPhone = R.drawable.wallpaperf;
                break;
            case R.id.btnSetWallpaper:

                InputStream yeaaa = getResources().openRawResource(toPhone);
                Bitmap whatever = BitmapFactory.decodeStream(yeaaa);

                try {
                    getApplicationContext().setWallpaper(whatever);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }
}
