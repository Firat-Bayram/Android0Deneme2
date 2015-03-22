package com.example.firat.androiddeneme2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

/**
 * Created by Firat on 9.1.2015.
 */
public class CustomView extends View {

    Bitmap ball;
    int x, y;

    public CustomView(Context context) {
        super(context);
        ball = BitmapFactory.decodeResource(getResources(), R.drawable.mail);
        x = 0;
        y = 0;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Rect ourRect = new Rect();
        ourRect.set(0, 0, canvas.getWidth(), canvas.getHeight() / 2);

        Paint blue = new Paint();
        blue.setColor(Color.BLUE);
        blue.setStyle(Paint.Style.FILL);

        canvas.drawRect(ourRect, blue);

        if (x < canvas.getWidth()) {
            x += 10;
        } else {
            x = 0;
        }
        if (y < canvas.getHeight()) {
            y += 10;
        } else {
            y = 0;
        }
        Paint p = new Paint();
        canvas.drawBitmap(ball, x, y, p);
        invalidate();
    }
}
