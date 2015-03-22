package com.example.firat.androiddeneme2;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Firat on 14.1.2015.
 */
public class SaveToSD extends Activity implements View.OnClickListener {
    Button savePicture;
    Button saveSound;
    EditText filename;
    Helper helper;
    boolean isSDAvail = false, isSDWriteable = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.savetosd);
        helper = Helper.INSTANCE;
        savePicture = helper.getirView(this, R.id.savePicture, Button.class);
        saveSound = helper.getirView(this, R.id.saveSound, Button.class);
        filename = helper.getirView(this, R.id.filename, EditText.class);
        savePicture.setOnClickListener(this);
        saveSound.setOnClickListener(this);
        checkSDStuff();
    }

    private void checkSDStuff() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            //write
            isSDAvail = true;
            isSDWriteable = true;
        } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            //read-only
            isSDAvail = true;
            isSDWriteable = false;
        } else {
            //uh-oh
            isSDAvail = false;
            isSDWriteable = false;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.savePicture:
                if (isSDAvail && isSDWriteable) {
                    File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                    String name = filename.getText().toString();
                    File file = new File(path, name + ".png");

                    saveData(path, file, R.drawable.mail);
                }
                break;
            case R.id.saveSound:
                if (isSDAvail && isSDWriteable) {
                    File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
                    String name = filename.getText().toString();
                    File file = new File(path, name + ".mp3");

                    saveData(path, file, R.raw.splash_sound);
                }
                break;
            default:
                break;
        }
    }

    private void saveData(File path, File file, int info) {
        try {
            path.mkdirs();
            InputStream is = getResources().openRawResource(info);

            OutputStream os = new FileOutputStream(file);
            byte[] data = new byte[is.available()];
            is.read(data);
            os.write(data);
            is.close();
            os.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
