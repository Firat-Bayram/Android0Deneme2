package com.example.firat.androiddeneme2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Firat on 14.1.2015.
 * <p/>
 * InternalStore da eklenenler bu ekranda okunur.
 */
public class ReadingData extends Activity implements View.OnClickListener {
    Helper helper;
    Spinner spinner;
    TextView title, entry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reading_data);
        helper = Helper.INSTANCE;

        spinner = helper.getirView(this, R.id.spinner1, Spinner.class);
        title = helper.getirView(this, R.id.textView1, TextView.class);
        entry = helper.getirView(this, R.id.textView2, TextView.class);
        getFileNames();
    }

    private void getFileNames() {
        String[] fileNames = getApplicationContext().fileList();
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < fileNames.length; i++) {
            list.add(fileNames[i]);
        }
        ArrayAdapter<String> filenameAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, list);
        spinner.setAdapter(filenameAdapter);
    }

    @Override
    public void onClick(View v) {
        String selectFile = String.valueOf(spinner.getSelectedItem());
        openFile(selectFile);
    }

    private void openFile(String selectFile) {
        String value = "";
        FileInputStream fis;

        try {
            fis = openFileInput(selectFile);
            byte[] input = new byte[fis.available()];
            while (fis.read(input) != -1) {
                value += new String(input);
            }
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        entry.setText(value);
    }
}
