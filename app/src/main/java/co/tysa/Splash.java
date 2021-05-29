package co.tysa;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import co.tysa.R;

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


public class Splash extends AppCompatActivity {
    CommonClass common;
    public static final int MY_PERMISSIONS_REQUEST_WRITE_FIELS = 102;
    AlertDialog dialog;

    JSONReader j_reader;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();

        progressBar = (ProgressBar) findViewById(R.id.pb_splash);

        progressBar.setVisibility(View.GONE);

        common = new CommonClass(this);
        j_reader = new JSONReader(this);

        Thread background = new Thread() {
            public void run() {

                try {
                    // Thread will sleep for 5 seconds
                    sleep(5 * 1000);
                    checkAppPermissions();

                    // After 5 seconds redirect to another intent

                } catch (Exception e) {

                }
            }
        };

        // start thread
        if (common.is_user_login()) {
            checkAppPermissions();
        } else {
            background.start();
        }

    }

    public void checkAppPermissions() {
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this,
                        android.Manifest.permission.INTERNET)
                        != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this,
                        android.Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this,
                        android.Manifest.permission.ACCESS_NETWORK_STATE)
                        != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this,
                        android.Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this,
                        Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this,
                        android.Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE) && ActivityCompat.shouldShowRequestPermissionRationale(this,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE) && ActivityCompat.shouldShowRequestPermissionRationale(this,
                    android.Manifest.permission.INTERNET) && ActivityCompat.shouldShowRequestPermissionRationale(this,
                    android.Manifest.permission.ACCESS_NETWORK_STATE) && ActivityCompat.shouldShowRequestPermissionRationale(this,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION) && ActivityCompat.shouldShowRequestPermissionRationale(this,
                    android.Manifest.permission.CALL_PHONE) && ActivityCompat.shouldShowRequestPermissionRationale(this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION)) {
                go_next();
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{
                                android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE,
                                android.Manifest.permission.INTERNET,
                                android.Manifest.permission.ACCESS_NETWORK_STATE,
                                android.Manifest.permission.ACCESS_COARSE_LOCATION,
                                android.Manifest.permission.CALL_PHONE,
                                android.Manifest.permission.ACCESS_FINE_LOCATION
                        },
                        MY_PERMISSIONS_REQUEST_WRITE_FIELS);
            }
        } else {
            go_next();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        if (requestCode == MY_PERMISSIONS_REQUEST_WRITE_FIELS) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                go_next();
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(Splash.this);
                builder.setMessage(R.string.permission_required)
                        .setPositiveButton(R.string.fire, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // FIRE ZE MISSILES!
                                openPermissionScreen();
                            }
                        })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                                dialog.dismiss();
                            }
                        });
                dialog = builder.show();
            }
            return;
        }
    }

    public void go_next() {
        if (common.is_user_login()) {
            /*Intent intent = new Intent(Splash.this, MainActivity.class);
            startActivity(intent);
            finish();*/
            new getUserData().execute();
        } else {
            Intent intent = new Intent(Splash.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void openPermissionScreen() {
        // startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                Uri.fromParts("package", Splash.this.getPackageName(), null));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
        super.onDestroy();
    }

    public class getUserData extends AsyncTask<Void, Void, String> {

        private JSONObject objStudData;
        private boolean response = false;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.
            String responseString = null;

            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);

            nameValuePairs.add(new BasicNameValuePair("employee_id", common.getSession(ConstValue.COMMON_KEY)));

            Log.e("Post:", nameValuePairs.get(0).getName() + "=" + nameValuePairs.get(0).getValue());

            JSONParser jsonParser = new JSONParser();

            try {
                String json_responce = jsonParser.makeHttpRequest(ConstValue.EMPLOYEE_PROFILE_URL, "POST", nameValuePairs);

                Log.e("Respnose:", json_responce);

                JSONObject jObj = new JSONObject(json_responce);
                if (jObj.has("responce") && !jObj.getBoolean("responce")) {
                    responseString = jObj.getString("error");
                    response = false;
                } else {
                    if (jObj.has("data")
                            && !jObj.getString("data").isEmpty()
                            && !jObj.getString("data").equals("null")) {
                        j_reader.setJSONPref("employee_data", jObj.getString("data"));
                        response = true;
                    } else {
                        responseString = "User not found";
                        response = false;
                    }
                }


            } catch (JSONException e) {
                // TODO Auto-generated catch block
                //responseString = e.getMessage();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                //responseString = e.getMessage();
                e.printStackTrace();
            }

            // TODO: register the new account here.
            return responseString;
        }

        @Override
        protected void onPostExecute(final String success) {
            progressBar.setVisibility(View.GONE);
            if (response) {
                Intent intent = new Intent(Splash.this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(Splash.this, success, Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected void onCancelled() {
            progressBar.setVisibility(View.GONE);
        }
    }

}
