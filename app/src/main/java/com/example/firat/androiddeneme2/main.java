package com.example.firat.androiddeneme2;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;

/**
 * Created by Firat on 5.1.2015.
 */
public class main extends Activity {

    Helper helper;
    MediaPlayer splash_sound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        helper = Helper.INSTANCE;
        helper.tamEkran(this);
        setContentView(R.layout.splash);


        splash_sound = MediaPlayer.create(main.this, R.raw.splash_sound);
        splash_sound.start();

        helper.calistirParalel(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    helper.goster(main.this, menu.class);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    finish();
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        splash_sound.release();
    }
}
