package com.example.firat.androiddeneme2;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

/**
 * Created by Firat on 12.1.2015.
 */
public class VideoViews extends Activity {
    Helper helper;
    VideoView videoView;
    Button btnPlayPause;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.videoviews);
        helper = Helper.INSTANCE;

        btnPlayPause = helper.getirView(this, R.id.btnPlayPause, Button.class);
        btnPlayPause.setText("Play");

        videoView = helper.getirView(this, R.id.videoView, VideoView.class);
        String urlPath = "android.resource://" + getPackageName() + "/" + R.raw.blackberry;
        videoView.setVideoURI(Uri.parse(urlPath));
        //videoView.start();

      /*  MediaController mc = new MediaController(this);
        mc.setMediaPlayer(videoView);
        videoView.setMediaController(mc);*/
    }

    public void btnBackwardOnClick(View v) {
        if (videoView.canSeekBackward()) {

        }
    }

    public void btnPlayPauseOnClick(View v) {
        if (videoView.isPlaying()) {
            videoView.pause();
            btnPlayPause.setText("Play");
        } else {
            videoView.start();
            btnPlayPause.setText("Pause");
        }
    }

    public void btnForwardOnClick(View v) {
        if (videoView.canSeekForward()) {

        }
    }
}