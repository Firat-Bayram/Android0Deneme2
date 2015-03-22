package com.example.firat.androiddeneme2;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;
import java.util.TimerTask;


public class menu extends ActionBarActivity {

    Helper helper;
    MediaPlayer buttonClick_sound;
    CheckBox chkDisplayName;
    EditText edtName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        helper = Helper.INSTANCE;
        helper.tamEkran(this);
        setContentView(R.layout.menu);

        buttonClick_sound = MediaPlayer.create(menu.this, R.raw.button_click);
        chkDisplayName = helper.getirView(this, R.id.chkDisplayName, CheckBox.class);
        edtName = helper.getirView(this, R.id.edtName, EditText.class);

        //SharedPreferences uygulamanızdaki çeşitli değerleri anahtar - değer mantığı ile saklamanıza yardımcı olur.
        //Bu şekilde uygulamanıza özel tutulması gereken çeşitli parametreleri ya da kullanıcıdan talep ettiğiniz
        // bazı ayarları ya da bilgileri kolayca saklayıp gerektiğinde geri çağırabilirsiniz.

        //Saklanmak istenen değerler put metodları ile eklendikten sonra commit metodu ile yazma işlemi tamamlanır ve
        // değerler saklanmış olur. Context.MODE_PRIVATE ise bu değişkenlerin sadece bizim uygulamamız tarafından okunmasını sağlar.
        // /data/data/paket.adi/shared_prefs altında MyApp.xml dosyası oluşur
        //SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);

        SharedPreferences sp = getSharedPreferences("MyApp", Context.MODE_PRIVATE);
        SharedPreferences.Editor spEditor = sp.edit();
        spEditor.putString("UygulamaSahibi", "Fırat BAYRAM");
        spEditor.commit();

        boolean isDisplayName = sp.getBoolean("DisplayName", false);
        String name = sp.getString("Name", "Ad girin...");

        chkDisplayName.setChecked(isDisplayName);
        edtName.setText(name);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.itemMenu_Ara:
                helper.gosterBilgiMesaji(this, "Ara");
                break;
            case R.id.itemMenu_Cikis:
                helper.cikis(this);
                break;
            case R.id.action_settings:
                helper.gosterBilgiMesaji(this, "Ayarlar");
                break;
            case R.id.itemGonder_Sms:
                helper.goster(this, gondersms.class);
                break;
            default:
                helper.gosterBilgiMesaji(this, "Anlamsız");
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void btnSaveOnClick(View v) {
        buttonClick_sound.start();

        SharedPreferences sp = getSharedPreferences("MyApp", Context.MODE_PRIVATE);
        SharedPreferences.Editor spEditor = sp.edit();
        spEditor.putBoolean("DisplayName", chkDisplayName.isChecked());
        spEditor.putString("Name", edtName.getText().toString());
        spEditor.commit();

        helper.gosterBilgiMesaji(this, edtName.getText().toString());
    }

    public void btnSqliteOnClick(View v) {
        buttonClick_sound.start();

        helper.goster(this, digerekran.class);
    }

    public void btnParalelOnClick(View v) {
        buttonClick_sound.start();

        helper.calistirParalel(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                        System.out.println("Other thread time : " + new Date());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        System.out.println("Main thread");
    }

    public void btnTimerOnClick(View v) {
        buttonClick_sound.start();

        TimerTask task = new TimerTask() {
            public void run() {
                helper.yazLog(Helper.logTypes.information, "Task time : " + new Date());
            }
        };
        helper.calistirZamanli(task, 500, 2000);
    }

    public void btnDialogOnClick(View v) {
        buttonClick_sound.start();

        helper.goster(this, DialogActivity.class);
    }

    public void btnShowListViewOnClick(View v) {
        buttonClick_sound.start();

        helper.goster(this, UsingListView.class);
    }

    public void btnSetBackgroundOnClick(View v) {
        buttonClick_sound.start();

        helper.goster(this, ImageViews.class);
    }

    public void btnCustomViewOnClick(View v) {
        buttonClick_sound.start();

        helper.goster(this, CustomViewActivity.class);
    }

    public void btnGraphicSurfaceOnClick(View v) {
        buttonClick_sound.start();

        helper.goster(this, SurfaceViewExample.class);
    }

    public void btnVideoOnClick(View v) {
        buttonClick_sound.start();

        helper.goster(this, VideoViews.class);
    }

    public void btnInternalStoreOnClick(View v) {
        buttonClick_sound.start();

        helper.goster(this, InternalStore.class);
    }

    public void btnReadingDataOnClick(View v) {
        buttonClick_sound.start();

        helper.goster(this, ReadingData.class);
    }

    public void btnSaveToSDOnClick(View v){
        buttonClick_sound.start();

        helper.goster(this, SaveToSD.class);
    }

    public void btnRemoteDatabaseOnClick(View v){
        buttonClick_sound.start();

        helper.goster(this, RemoteDatabase.class);
    }
}