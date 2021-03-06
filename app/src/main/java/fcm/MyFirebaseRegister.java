package fcm;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Config.ConstValue;
import util.CommonClass;
import util.JSONParser;
import util.JSONReader;



public class MyFirebaseRegister {

    Activity _context;
    CommonClass common;

    public MyFirebaseRegister(Activity context) {
        this._context = context;
        common = new CommonClass(context);

    }

    public void RegisterUser() {
        // [START subscribe_topics]
        String token = FirebaseInstanceId.getInstance().getToken();
        String school_id = new JSONReader(_context).getJSONkeyString("employee_data", "profile_id");
        String standard_id = new JSONReader(_context).getJSONkeyString("employee_data", "employee_standard");
        FirebaseMessaging.getInstance().subscribeToTopic(ConstValue.TOPIC_GLOBAL + "_" + school_id);
        FirebaseMessaging.getInstance().subscribeToTopic(ConstValue.TOPIC_GLOBAL + "_" + standard_id);
        // [END subscribe_topics]

        Log.e("topic1", ConstValue.TOPIC_GLOBAL + "_" + school_id);
        Log.e("topic2", ConstValue.TOPIC_GLOBAL + "_" + standard_id);
        Log.e("token", token);

        new loadDataTask(token).execute();

    }

    class loadDataTask extends AsyncTask<Void, Void, String> {
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);

        public loadDataTask(String token) {
            nameValuePairs.add(new BasicNameValuePair("employee_id", common.getSession(ConstValue.COMMON_KEY)));
            nameValuePairs.add(new BasicNameValuePair("token", token));
            nameValuePairs.add(new BasicNameValuePair("device", "android"));
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);

        }

        @Override
        protected String doInBackground(Void... params) {
            // TODO Auto-generated method stub
            String responseString = null;

            try {

                JSONParser jParser = new JSONParser();
                String json = jParser.makeHttpRequest(ConstValue.FCM_REGISTER_URL, "POST", nameValuePairs);
                Log.e("responce_token", json);

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return responseString;

        }


        @Override
        protected void onPostExecute(String result) {
            if (result != null) {

            } else {

            }

        }

        @Override
        protected void onCancelled() {
            // TODO Auto-generated method stub
        }
    }

}
