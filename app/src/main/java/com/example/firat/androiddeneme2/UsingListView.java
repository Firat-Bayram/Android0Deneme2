package com.example.firat.androiddeneme2;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by Firat on 7.1.2015.
 */
public class UsingListView extends ListActivity {

    Helper helper;
    String classNames[] = {"main", "menu", "gondersms"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        helper = Helper.INSTANCE;

        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, classNames));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        String openClass = classNames[position];
        try {
            Class selected = Class.forName("com.example.firat.androiddeneme2." + openClass);
            helper.goster(this, selected);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
