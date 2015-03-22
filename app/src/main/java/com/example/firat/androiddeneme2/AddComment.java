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

public class AddComment extends Activity implements OnClickListener {

    private EditText title, message;
    private Button mSubmit;

    // Progress Dialog
    private ProgressDialog pDialog;

    // JSON parser class
    //JSONParser jsonParser = new JSONParser();

    //php login script

    //localhost :
    //testing on your device
    //put your local ip instead,  on windows, run CMD > ipconfig
    //or in mac's terminal type ifconfig and look for the ip under en0 or en1
    // private static final String POST_COMMENT_URL = "http://xxx.xxx.x.x:1234/webservice/addcomment.php";

    //testing on Emulator:
    private static final String POST_COMMENT_URL = "http://192.168.1.101:80/webservis/addcomment.php";

    //testing from a real server:
    //private static final String POST_COMMENT_URL = "http://www.mybringback.com/webservice/addcomment.php";

    //ids
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    Helper helper = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_comment);
        helper = Helper.INSTANCE;

        title = helper.getirView(this, R.id.title, EditText.class);
        message = helper.getirView(this, R.id.message, EditText.class);

        mSubmit = helper.getirView(this, R.id.submit, Button.class);
        mSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        new PostComment().execute();
    }


    class PostComment extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = helper.showLoadingDialogBox(AddComment.this, AlertDialog.THEME_HOLO_LIGHT, "Yorum Gönderiliyor...", true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... args) {
            // TODO Auto-generated method stub
            // Check for success tag
            int success;
            String post_title = title.getText().toString();
            String post_message = message.getText().toString();

            //Retrieving Saved Username Data:
            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(AddComment.this);
            String post_username = sp.getString("username", "anon");

            try {
                // Building Parameters
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("username", post_username));
                params.add(new BasicNameValuePair("title", post_title));
                params.add(new BasicNameValuePair("message", post_message));

                helper.yazLog(Helper.logTypes.debug, "request!", "starting");

                //Posting user data to script
                JSONObject json = JSONParser.INSTANCE.makeHttpRequest(POST_COMMENT_URL, "POST", params);

                // full json response
                helper.yazLog(Helper.logTypes.debug, "Post Comment attempt", json.toString());

                // json success element
                success = json.getInt(TAG_SUCCESS);
                if (success == 1) {
                    helper.yazLog(Helper.logTypes.debug, "Comment Added!", json.toString());
                    finish();
                    return json.getString(TAG_MESSAGE);
                } else {
                    helper.yazLog(Helper.logTypes.debug, "Comment Failure!", json.getString(TAG_MESSAGE));
                    return json.getString(TAG_MESSAGE);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;

        }

        protected void onPostExecute(String file_url) {
            // dismiss the dialog once product deleted
            pDialog.dismiss();
            if (file_url != null) {
                helper.gosterBilgiMesaji(AddComment.this, file_url);
            }
        }
    }
}