package com.example.firat.androiddeneme2;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by Firat on 9.1.2015.
 */
public class CustomViewActivity extends Activity {
    CustomView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = new CustomView(this);
        setContentView(view);
    }
}
