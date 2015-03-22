package com.example.firat.androiddeneme2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Firat on 13.1.2015.
 */
public class BackResultDialog extends Activity {

    EditText editTextTutar;
    Helper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.back_result_dialog);
        helper = Helper.INSTANCE;

        Bundle bundle = helper.getirEkranParametrelerini(this);
        int ilkTutar = bundle.getInt("ilkTutar");
        int ikinciTutar = bundle.getInt("ikinciTutar");
        editTextTutar = helper.getirView(this, R.id.editTextTutar, EditText.class);
        editTextTutar.setText(String.valueOf(ilkTutar + ikinciTutar));
    }

    public void btnOkOnClick(View v) throws Exception {
        Map params = new HashMap();
        params.put("sonuc", editTextTutar.getText().toString());
        helper.sonuclaDialog(this, params, RESULT_OK);
        finish();
    }
}
