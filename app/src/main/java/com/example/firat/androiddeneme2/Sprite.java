package com.example.firat.androiddeneme2;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Created by Firat on 10.1.2015.
 */
public class Sprite {

    int x, y;
    int xSpeed, ySpeed;
    int height, width;
    Bitmap b;
    SurfaceViewExample.OurView ov;
    int currentFrame = 0;
    int direction = 3;
    GraphicsHelper graphicsHelper;
    Helper helper;

    public Sprite(SurfaceViewExample.OurView ourView, Bitmap blob) {
        graphicsHelper = GraphicsHelper.INSTANCE;
        helper = Helper.INSTANCE;

        b = blob;
        ov = ourView;
        //4 rows
        height = b.getHeight() / 4;
        width = b.getWidth() / 4;

        x = y = 0;
        xSpeed = 5;
        ySpeed = 0;
    }

    private void update() {

        //direction
        //0 = up
        //1 = down
        //2 = left
        //3 = right

        //facing down
        if (x > ov.getWidth() - width - xSpeed) {
            xSpeed = 0;
            ySpeed = 5;
            direction = 1;
        }
        //going left
        if (y > ov.getHeight() - height - ySpeed) {
            xSpeed = -5;
            ySpeed = 0;
            direction = 2;
        }
        //facing up
        if (x + xSpeed < 0) {
            x = 0;
            xSpeed = 0;
            ySpeed = -5;
            direction = 0;
        }
        //facing right
        if (y + ySpeed < 0) {
            y = 0;
            xSpeed = 5;
            ySpeed = 0;
            direction = 3;
        }

        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        currentFrame = ++currentFrame % 4;
        x += xSpeed;
        y += ySpeed;
    }

    public void onDraw(Canvas canvas) {
        update();
        int srcX = currentFrame * width;
        int srcY = direction * height;
        helper.yazLog(Helper.logTypes.information, "x: " + x + "y: " + y + "width: " + width + "height: " + height + "srcX: " + srcX + "srcY: " + srcY);
        Rect src = graphicsHelper.GetirDikdortgen(srcX, srcY, width, height);
        //Rect src= new Rect(400,600,600,800);
        //Rect dst= new Rect(128,0,183,79);
        Rect dst = graphicsHelper.GetirDikdortgen(x, y, width, height);
        canvas.drawBitmap(b, src, dst, null);
    }
}
