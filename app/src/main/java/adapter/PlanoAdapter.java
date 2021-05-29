package adapter;


import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import co.tysa.R;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Config.ConstValue;
import util.CommonClass;
import util.JSONParser;
import util.JSONReader;

public class PlanoAdapter extends BaseAdapter {

    private Activity context;
    //private ArrayList<HashMap<String, String>> postItems;
    private JSONArray postItems;
    CommonClass common;
    JSONReader j_reader;

    Double cLat, cLog;
    int count = 0;
    TextView txtLikes;
    private int lastPosition = -1;
    ProgressDialog dialog;

    public PlanoAdapter(Activity act, JSONArray arraylist) {
        this.context = act;
        common = new CommonClass(act);
        j_reader = new JSONReader(act);
        postItems = arraylist;
        new loadDataTask().execute();
    }

    @Override
    public int getCount() {
        return postItems.length();
    }

    @Override
    public JSONObject getItem(int position) {
        try {
            return postItems.getJSONObject(position);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.row_for_exam, null);
        }

        lastPosition = position;

        try {
            JSONObject jObj = postItems.getJSONObject(position);

            //TextView txtDate = (TextView) convertView.findViewById(R.id.date);
            //TextView txtMonth = (TextView) convertView.findViewById(R.id.month);
            //TextView txtYear = (TextView) convertView.findViewById(R.id.year);
            //TextView txtTitle = (TextView) convertView.findViewById(R.id.textzona);
            //TextView txtcode = (TextView) convertView.findViewById(R.id.textcode);
            TextView txtplanoId = (TextView) convertView.findViewById(R.id.textplanoId);
            TextView txtDescription = (TextView) convertView.findViewById(R.id.plano);

            //String date_month = CommonClass.changeDateFormate(jObj.getString("exam_date"), 5);
            //String date_day = CommonClass.changeDateFormate(jObj.getString("exam_date"), 1);
            //String date_year = CommonClass.changeDateFormate(jObj.getString("exam_date"), 3);
            //txtDate.setText(date_day);
            //txtYear.setText(date_year);
            //txtMonth.setText(date_month);

            //txtcode.setText(jObj.getInt("plano_id"));
            //txtTitle.setText(jObj.getString("zona"));
            txtplanoId.setText(jObj.getString("plano_id"));
            txtDescription.setText(jObj.getString("user_id"));


        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        return convertView;
    }

    class loadDataTask extends AsyncTask<Void, Void, String> {
        @Override
        protected void onPreExecute() {
            dialog = ProgressDialog.show(context, "",
                    context.getResources().getString(R.string.loading_please_wait), true);
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
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
                nameValuePairs.add(new BasicNameValuePair("plano_id", j_reader.getJSONkeyString("employee_data", "plano_id")));
                nameValuePairs.add(new BasicNameValuePair("user_id", j_reader.getJSONkeyString("employee_data", "user_id")));

                JSONParser jParser = new JSONParser();
                String json = jParser.makeHttpRequest(ConstValue.PLANOS_URL, "POST", nameValuePairs);

                JSONObject jObj = new JSONObject(json);
                if (jObj.has("responce") && !jObj.getBoolean("responce")) {
                    responseString = jObj.getString("error");
                } else {
                    if (jObj.has("data")) {
                        postItems = jObj.getJSONArray("data");
                    } else {
                        responseString = "Not Data found";
                    }
                }

            } catch (JSONException e) {
                // TODO Auto-generated catch block
                responseString = e.getMessage();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return responseString;

        }


        @Override
        protected void onPostExecute(String result) {
            if (result != null) {
                Toast.makeText(context, result, Toast.LENGTH_LONG).show();
            } else {

                notifyDataSetChanged();
            }
            dialog.dismiss();
        }

        @Override
        protected void onCancelled() {
            // TODO Auto-generated method stub
        }

    }


}

