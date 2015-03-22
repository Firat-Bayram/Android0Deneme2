package com.example.firat.androiddeneme2;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Firat on 30.12.2014.
 */
public class sonucsms extends Activity {

    Helper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sonucsms);
        helper = Helper.INSTANCE;

        SharedPreferences sp = getSharedPreferences("MyApp", Context.MODE_PRIVATE);
        String uygulamaSahibi = sp.getString("UygulamaSahibi", "Uygulama Sahibi Bulunamadı");


        Bundle bundle = helper.getirEkranParametrelerini(this);
        String message = bundle.getString("message");

        TextView textViewMessage = helper.getirView(this, R.id.textViewMessage, TextView.class);
        textViewMessage.setText(uygulamaSahibi + ": " + message);
    }

    public void btnGeriOnClick(View v) {
        helper.cikis(this);
    }
}
