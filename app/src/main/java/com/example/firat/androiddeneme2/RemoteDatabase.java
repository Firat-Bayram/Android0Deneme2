package com.example.firat.androiddeneme2;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RemoteDatabase extends Activity implements OnClickListener {

    private EditText user, pass;
    private Button mSubmit, mRegister;

    // Progress Dialog
    private ProgressDialog pDialog;

    // JSON parser class
    //JSONParser jsonParser = new JSONParser();

    //php login script location:

    //localhost :
    //testing on your device
    //put your local ip instead,  on windows, run CMD > ipconfig
    //or in mac's terminal type ifconfig and look for the ip under en0 or en1
    // private static final String LOGIN_URL = "http://xxx.xxx.x.x:1234/webservice/login.php";

    //testing on Emulator:
    private static final String LOGIN_URL = "http://192.168.1.101:80/webservis/login.php";

    //testing from a real server:
    //private static final String LOGIN_URL = "http://www.yourdomain.com/webservice/login.php";

    //JSON element ids from repsonse of php script:
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    Helper helper = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.remote_database);
        helper = Helper.INSTANCE;

        //setup input fields
        user = helper.getirView(this, R.id.username, EditText.class);
        pass = helper.getirView(this, R.id.password, EditText.class);

        //setup buttons
        mSubmit = helper.getirView(this, R.id.login, Button.class);
        mRegister = helper.getirView(this, R.id.register, Button.class);

        //register listeners
        mSubmit.setOnClickListener(this);
        mRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.login:
                new AttemptLogin().execute();
                break;
            case R.id.register:
                helper.goster(this, Register.class);
                break;

            default:
                break;
        }
    }

    class AttemptLogin extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         */
        boolean failure = false;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = helper.showLoadingDialogBox(RemoteDatabase.this, AlertDialog.THEME_HOLO_LIGHT, "Giriş Yapılıyor...", true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... args) {
            // TODO Auto-generated method stub
            // Check for success tag
            int success;
            String username = user.getText().toString();
            String password = pass.getText().toString();
            try {
                // Building Parameters
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("username", username));
                params.add(new BasicNameValuePair("password", password));

                helper.yazLog(Helper.logTypes.debug, "request!", "starting");
                // getting product details by making HTTP request
                JSONObject json = JSONParser.INSTANCE.makeHttpRequest(LOGIN_URL, "POST", params);

                // check your log for json response
                helper.yazLog(Helper.logTypes.debug, "Login attempt", json.toString());

                // json success tag
                success = json.getInt(TAG_SUCCESS);
                if (success == 1) {
                    helper.yazLog(Helper.logTypes.debug, "Login Successful!", json.toString());
                    // save user data
                    SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(RemoteDatabase.this);
                    SharedPreferences.Editor edit = sp.edit();
                    edit.putString("username", username);
                    edit.commit();

                    helper.cikis(RemoteDatabase.this);
                    helper.goster(RemoteDatabase.this, ReadComments.class);

                    return json.getString(TAG_MESSAGE);
                } else {
                    helper.yazLog(Helper.logTypes.debug, "Login Failure!", json.getString(TAG_MESSAGE));
                    return json.getString(TAG_MESSAGE);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         *
         * @param file_url
         */
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once product deleted
            pDialog.dismiss();
            if (file_url != null) {
                helper.gosterBilgiMesaji(RemoteDatabase.this, file_url);
            }
        }
    }

}