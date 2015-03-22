package com.example.firat.androiddeneme2;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

/**
 * Created by Firat on 10.1.2015.
 */
public class SurfaceViewExample extends Activity implements View.OnTouchListener {

    Helper helper;
    OurView v;
    Bitmap ball, blob;
    float x, y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        helper = Helper.INSTANCE;
        helper.tamEkran(this);
        v = new OurView(this);
        v.setOnTouchListener(this);
        ball = BitmapFactory.decodeResource(getResources(), R.drawable.mail);
        blob = BitmapFactory.decodeResource(getResources(), R.drawable.spritesheeta);
        helper.gosterBilgiMesaji(this, "Width: " + String.valueOf(blob.getWidth()) + "Height: " + String.valueOf(blob.getHeight()));

        x = y = 0;
        setContentView(v);
    }

    @Override
    protected void onPause() {
        super.onPause();
        v.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        v.resume();
    }

    @Override
    public boolean onTouch(View v, MotionEvent me) {

        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        switch (me.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x = me.getX();
                y = me.getY();
                break;
            case MotionEvent.ACTION_UP:
                x = me.getX();
                y = me.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                x = me.getX();
                y = me.getY();
                break;
            default:
                break;
        }

        return true;
    }

    public class OurView extends SurfaceView implements Runnable {
        Thread t = null;
        SurfaceHolder holder;
        boolean isItOk = false;
        Sprite sprite;

        public OurView(Context context) {
            super(context);
            holder = getHolder();
        }

        @Override
        public void run() {

            sprite = new Sprite(this, blob);

            while (isItOk == true) {
                if (!holder.getSurface().isValid()) {
                    continue;
                }

                Canvas c = holder.lockCanvas();
                onDraw(c);
                holder.unlockCanvasAndPost(c);
            }
        }

        protected void onDraw(Canvas canvas) {
            canvas.drawARGB(255, 150, 150, 10);
            canvas.drawBitmap(ball, x - (ball.getWidth() / 2), y - (ball.getHeight() / 2), null);
            sprite.onDraw(canvas);
        }

        public void pause() {
            isItOk = false;
            while (true) {
                try {
                    t.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            }
            t = null;
        }

        public void resume() {
            isItOk = true;
            t = new Thread(this);
            t.start();
        }
    }
}
