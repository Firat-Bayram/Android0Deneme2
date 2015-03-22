package com.example.firat.androiddeneme2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Firat on 30.12.2014.
 */
public class gondersms extends Activity {

    EditText editTxtTelNo;
    EditText editTxtMessage;
    Helper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gondersms);
        helper = Helper.INSTANCE;

        editTxtTelNo = helper.getirView(this, R.id.editTxtTelNo, EditText.class);
        editTxtMessage = helper.getirView(this, R.id.editTxtMessage, EditText.class);

    }

    public void btnGonderOnClick(View v) throws Exception {
        helper.gonderSMS(editTxtTelNo.getText().toString(), editTxtMessage.getText().toString());

        Map params = new HashMap();
        params.put("message", editTxtMessage.getText().toString());
        helper.goster(this, sonucsms.class, params);
    }

    public void btnGeriOnClick(View v) {
        helper.cikis(gondersms.this);
    }
}
