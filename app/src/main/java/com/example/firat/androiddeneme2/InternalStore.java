package com.example.firat.androiddeneme2;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Firat on 13.1.2015.
 */
public class InternalStore extends Activity implements View.OnClickListener {
    Helper helper;
    EditText filename, entry;
    Button save;
    String FILENAME, JOUR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.internal_store);

        helper = Helper.INSTANCE;
        filename = helper.getirView(this, R.id.edt1, EditText.class);
        entry = helper.getirView(this, R.id.edt2, EditText.class);
        save = helper.getirView(this, R.id.btnSave, Button.class);
        save.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        FILENAME = filename.getText().toString();
        if (FILENAME.contentEquals("")) {
            FILENAME = "UNTITLED";
        }
        JOUR = entry.getText().toString();

        try {
            FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            fos.write(JOUR.getBytes());
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
