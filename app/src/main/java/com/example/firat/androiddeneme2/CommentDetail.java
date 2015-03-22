package com.example.firat.androiddeneme2;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Firat on 25.1.2015.
 */
public class CommentDetail extends Activity {

    // Progress Dialog
    private ProgressDialog pDialog;

    TextView title = null, message = null, username = null;
    Helper helper = null;

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_TITLE = "title";
    private static final String TAG_USERNAME = "username";
    private static final String TAG_MESSAGE = "message";

    //testing on Emulator:
    private static final String COMMENT_URL = "http://192.168.1.101:80/webservis/comment.php";
    String postId="";
    private HashMap<String, String> map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comment_detail);
        helper = Helper.INSTANCE;

        title = helper.getirView(this, R.id.title, TextView.class);
        message = helper.getirView(this, R.id.message, TextView.class);
        username = helper.getirView(this, R.id.username, TextView.class);

        Bundle bundle = helper.getirEkranParametrelerini(this);
        postId = bundle.getString("post_id");
        new LoadComment().execute();
    }

    public class LoadComment extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = helper.showLoadingDialogBox(CommentDetail.this, AlertDialog.THEME_HOLO_LIGHT, "Yorum Yükleniyor...", true);
            pDialog.show();
        }

        @Override
        protected Boolean doInBackground(Void... arg0) {

            try {
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("postId", postId));

                //we will develop this method in version 2
                JSONObject json = JSONParser.INSTANCE.makeHttpRequest(COMMENT_URL, "POST", params);

                int success = json.getInt(TAG_SUCCESS);
                if (success == 1) {
                    String usernameText = json.getString(TAG_USERNAME);
                    String titleText = json.getString(TAG_TITLE);
                    String messageText = json.getString(TAG_MESSAGE);

                    map = new HashMap<String, String>();
                    map.put(TAG_TITLE, titleText);
                    map.put(TAG_MESSAGE, messageText);
                    map.put(TAG_USERNAME, usernameText);
                } else {

                }
            }catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);

            String usernameText = (String)map.get(TAG_USERNAME);
            String titleText = (String)map.get(TAG_TITLE);
            String messageText = (String)map.get(TAG_MESSAGE);

            title.setText(titleText);
            message.setText(messageText);
            username.setText(usernameText);

            pDialog.dismiss();
        }
    }
}
