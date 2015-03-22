package com.example.firat.androiddeneme2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.HashMap;
import java.util.Map;

public class DialogActivity extends ActionBarActivity implements DialogInterface.OnClickListener, DialogInterface.OnMultiChoiceClickListener {

    Helper helper;
    AlertDialog dialogList;
    AlertDialog dialogCheckList;
    AlertDialog dialogYesNo;
    AlertDialog dialogYesNoCancel;
    AlertDialog dialogRadioList;
    ProgressDialog progressDialog;
    AlertDialog dialogCustom2;
    AlertDialog dialogCustomLoginUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        helper = Helper.INSTANCE;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dialog, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if (dialogList != null && dialogList.equals(dialog)) {
            helper.gosterBilgiMesaji(this, "dialogList" + String.valueOf(which) + " " + dialog.toString());
        } else if (dialogYesNo != null && dialogYesNo.equals(dialog)) {
            helper.gosterBilgiMesaji(this, "dialogYesNo" + String.valueOf(which) + " " + dialog.toString());
        } else if (dialogYesNoCancel != null && dialogYesNoCancel.equals(dialog)) {
            helper.gosterBilgiMesaji(this, "dialogYesNoCancel" + String.valueOf(which) + " " + dialog.toString());
        } else if (dialogRadioList != null && dialogRadioList.equals(dialog)) {
            helper.gosterBilgiMesaji(this, "dialogRadioList" + String.valueOf(which) + " " + dialog.toString());
        } else if (dialogCustom2 != null && dialogCustom2.equals(dialog)) {
            helper.gosterBilgiMesaji(this, "dialogCustom2" + String.valueOf(which) + " " + dialog.toString());
        } else if (dialogCustomLoginUser != null && dialogCustomLoginUser.equals(dialog)) {
            helper.gosterBilgiMesaji(this, "dialogCustom3" + String.valueOf(which) + " " + dialog.toString());
        } else if (dialogCheckList != null && dialogCheckList.equals(dialog)) {
            helper.gosterBilgiMesaji(this, "dialogCheckList" + String.valueOf(which) + " " + dialog.toString());
        } else {
            helper.gosterBilgiMesaji(this, "hata");
        }
    }

    @Override
    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
        if (dialogCheckList.equals(dialog)) {
            helper.gosterBilgiMesaji(this, "dialogCheckList" + String.valueOf(which) + " " + String.valueOf(isChecked) + " " + dialog.toString());
        } else {
            helper.gosterBilgiMesaji(this, "hata");
        }
    }

    public void btnAlert1OnClick(View v) {
        dialogRadioList = helper.showMessageBoxRadioList(this, AlertDialog.THEME_HOLO_LIGHT, "Alert1 Başlık", true, R.array.days, this, this, this);
        dialogRadioList.show();
    }

    public void btnAlert2OnClick(View v) {
        dialogList = helper.showMessageBoxList(this, AlertDialog.THEME_HOLO_LIGHT, "Alert2 Başlık", false, R.array.days, this);
        dialogList.show();
    }

    public void btnAlert3OnClick(View v) {
        dialogYesNo = helper.showMessageBoxYesNo(this, AlertDialog.THEME_HOLO_LIGHT, "Alert3 Başlık", "içerik", false, this, this);
        dialogYesNo.show();
    }

    public void btnAlert4OnClick(View v) {
        dialogCheckList = helper.showMessageBoxCheckList(this, AlertDialog.THEME_HOLO_LIGHT, "Alert4 Başlık", true, R.array.days, new boolean[]{true, false, true, false, false, false, false}, this, this, this);
        dialogCheckList.show();
    }

    public void btnAlert5OnClick(View v) {
        progressDialog = helper.showProgressDialogBox(this, AlertDialog.THEME_HOLO_LIGHT, "Alert5 Başlık", true);
        progressDialog.show();
        for (int i = 0; i < 51; i = i + 10) {
            //try {
            progressDialog.setProgress(i);
            //veya progressDialog.incrementProgressBy(10);
            //Thread.sleep(1000);
            //}
            //catch (InterruptedException e) {
            //}
        }
        //progressDialog.dismiss();
    }

    public void btnAlert6OnClick(View v) {
        dialogYesNoCancel = helper.showMessageBoxYesNoCancel(this, AlertDialog.THEME_HOLO_LIGHT, "Alert6 Başlık", "içerik", true, this, this, this);
        dialogYesNoCancel.show();
    }

    public void btnAlert7OnClick(View v) {
        progressDialog = helper.showLoadingDialogBox(this, AlertDialog.THEME_HOLO_LIGHT, "Yükleniyor...", true);
        progressDialog.show();
        //progressDialog.dismiss();
    }

    public void btnCustomDialog1OnClick(View v) {
        final Dialog dialog = helper.showCustomDialogContentViewBox(this, 0, "CustomDialog1 Başlık", false, R.layout.fire_missiles_dialog_fragment);
        // set the custom dialog components - text, image and button
        EditText text = helper.getirView(dialog, R.id.editText2, EditText.class);  // (EditText)dialog.findViewById(R.id.editText2);
        text.setText("Android custom dialog example!");
        ImageView image = helper.getirView(dialog, R.id.image, ImageView.class);   //(ImageView) dialog.findViewById(R.id.image);
        image.setImageResource(R.drawable.ic_launcher);

        Button dialogButton = helper.getirView(dialog, R.id.button2, Button.class);//(Button) dialog.findViewById(R.id.button2);
        // if button is clicked, close the custom dialog
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public void btnCustomDialog2OnClick(View v) {
        dialogCustom2 = helper.showCustomDialogViewBox(this, AlertDialog.THEME_HOLO_LIGHT, "CustomDialog2 Başlık", false, android.R.attr.alertDialogIcon, R.layout.fire_missiles_dialog_fragment, true, "Evet", this, true, "Hayır", this, true, "İptal", this);
        dialogCustom2.show();
       /* // set the custom dialog components - text, image and button
        EditText text = helper.GetirView(dialog,R.id.editText2,EditText.class);  // (EditText)dialog.findViewById(R.id.editText2);
        text.setText("Android custom dialog example!");
        ImageView image = helper.GetirView(dialog,R.id.image,ImageView.class);   //(ImageView) dialog.findViewById(R.id.image);
        image.setImageResource(R.drawable.ic_launcher);

        Button dialogButton = helper.GetirView(dialog,R.id.button2,Button.class);//(Button) dialog.findViewById(R.id.button2);
        // if button is clicked, close the custom dialog
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();*/
    }

    public void btnCustomDialog3OnClick(View v) {
        helper.goster(this, DialogView.class);
    }

    public void btnLoginUserOnClick(View v) {
        dialogCustomLoginUser = helper.showCustomDialogViewBox(this, AlertDialog.THEME_HOLO_LIGHT, "Kullanıcı Girişi", false, android.R.attr.alertDialogIcon, R.layout.login_layout, true, "Giriş", this, true, "İptal", this, false, "", null);
        dialogCustomLoginUser.show();
    }

    public void btnBackResultDialogOnClick(View V) throws Exception {
        Map params = new HashMap();
        params.put("ilkTutar", 5);
        params.put("ikinciTutar", 15);
        helper.gosterDialog(this, BackResultDialog.class, params);
        helper.gosterBilgiMesaji(this, "cccc");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data.getExtras().containsKey("sonuc")) {
            helper.gosterBilgiMesaji(this, data.getStringExtra("sonuc"));
        }
    }
}