package co.tysa;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import co.tysa.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import Config.ConstValue;
import util.CommonClass;
import util.JSONParser;
import util.JSONReader;

public class AttendenceActivity extends CommonAppCompatActivity {
    ArrayList<String> month_array;
    ArrayList<String> year_array;
    ArrayList<String> note_array;
    ArrayList<String> day_array;
    int current_year;
    int current_month;
    int max_days;
    int current_date;

    CommonClass common;
    JSONReader j_reader;
    JSONArray objAttendenceData;
    AttendanceAdapter adapter;

    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendence);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.color_21));
        }

        j_reader = new JSONReader(this);
        common = new CommonClass(this);
        note_array = new ArrayList<String>();

        month_array = new ArrayList<>();

        int[] months = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        for (int month : months) {
            Calendar cal = Calendar.getInstance(ConstValue.LOCALE);
            cal.set(Calendar.MONTH, month);
            String month_name = CommonClass.convetDateToString(cal.getTime(), 1);

            month_array.add(month_name);
        }

        year_array = new ArrayList<String>();
        day_array = new ArrayList<String>();
        day_array.add("Sun");
        day_array.add("Mon");
        day_array.add("Tue");
        day_array.add("Wed");
        day_array.add("Thu");
        day_array.add("Fri");
        day_array.add("Sat");

        Calendar c = Calendar.getInstance(ConstValue.LOCALE);
        int year = c.get(Calendar.YEAR);
        current_year = c.get(Calendar.YEAR);
        current_month = Integer.parseInt(CommonClass.convetDateToString(c.getTime(), 2)); // c.get(Calendar.MONTH);

        for (int i = 2016; i <= year; i++) {
            year_array.add(String.valueOf(i));
        }

        max_days = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        current_date = c.get(Calendar.DATE);

        Spinner spinnermonth = (Spinner) findViewById(R.id.spinnermonth);
        Spinner spinneryear = (Spinner) findViewById(R.id.spinneryear);

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, month_array); //selected item will look like a spinner set from XML
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnermonth.setAdapter(spinnerArrayAdapter);
        spinnermonth.setSelection(current_month - 1);
        spinnermonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                current_month = position + 1;
                // new getAttendenceTask().execute();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<String> spinnerArrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, year_array); //selected item will look like a spinner set from XML
        spinnerArrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinneryear.setAdapter(spinnerArrayAdapter2);
        spinneryear.setSelection(year_array.indexOf(String.valueOf(current_year)));
        spinneryear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                current_year = Integer.parseInt(year_array.get(position));
                //   new getAttendenceTask().execute();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent intent = new Intent(AttendenceActivity.this, EnquiryActivity.class);
                startActivity(intent);
            }
        });

        Button btnShow = (Button) findViewById(R.id.btnshow);
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Create a calendar object and set year and month
                Calendar mycal = new GregorianCalendar(current_year, current_month - 1, 1);

                // Get the number of days in that month
                int daysInMonth = mycal.getActualMaximum(Calendar.DAY_OF_MONTH);
                max_days = daysInMonth;

                bindDates(max_days);
            }
        });


        gridView = (GridView) findViewById(R.id.gridView2);
        adapter = new AttendanceAdapter();

        //gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showNoticePopup(position);
            }
        });

        bindDates(max_days);

    }

    ArrayList<Integer> days = new ArrayList<>();
    ArrayList<Integer> months = new ArrayList<>();

    private void bindDates(int selectedMaxDays) {

        days.clear();
        months.clear();
        for (int i = 1; i <= selectedMaxDays; i++) {
            days.add(i);
            months.add(current_month);
        }

        Log.e("Holiday", days.toString());

        Calendar currentMonth = Calendar.getInstance(ConstValue.LOCALE);
        currentMonth.set(Calendar.MONTH, current_month - 1);
        int totalDayCurrent = currentMonth.getActualMaximum(Calendar.DAY_OF_MONTH);

        Calendar pastMonth = Calendar.getInstance(ConstValue.LOCALE);
        pastMonth.set(Calendar.MONTH, (current_month - 1) - 1);
        int totalDay = pastMonth.getActualMaximum(Calendar.DAY_OF_MONTH);
        Log.e("Holiday", "past total days:" + totalDay);

        Date dateFirst = CommonClass.convetStringToDate(String.valueOf(current_year) + "-" + String.valueOf(current_month) + "-01", 1);
        Date dateLast = CommonClass.convetStringToDate(String.valueOf(current_year) + "-" + String.valueOf(current_month) + "-" + totalDayCurrent, 1);

        int firstAddCell = 0;
        int lastAddCell = 0;

        int equldate = 0;
        if (ConstValue.HOLIDAY_START_DAY.equalsIgnoreCase("Sun")) {
            equldate = 0;

            if (dateFirst.getDay() == 1) {
                firstAddCell = 1;
            } else if (dateFirst.getDay() == 2) {
                firstAddCell = 2;
            } else if (dateFirst.getDay() == 3) {
                firstAddCell = 3;
            } else if (dateFirst.getDay() == 4) {
                firstAddCell = 4;
            } else if (dateFirst.getDay() == 5) {
                firstAddCell = 5;
            } else if (dateFirst.getDay() == 6) {
                firstAddCell = 6;
            }

            if (dateLast.getDay() == 0) {
                lastAddCell = 6;
            } else if (dateLast.getDay() == 1) {
                lastAddCell = 5;
            } else if (dateLast.getDay() == 2) {
                lastAddCell = 4;
            } else if (dateLast.getDay() == 3) {
                lastAddCell = 3;
            } else if (dateLast.getDay() == 4) {
                lastAddCell = 2;
            } else if (dateLast.getDay() == 5) {
                lastAddCell = 1;
            }

        } else if (ConstValue.HOLIDAY_START_DAY.equalsIgnoreCase("Mon")) {
            equldate = 1;

            if (dateFirst.getDay() == 0) {
                firstAddCell = 6;
            } else if (dateFirst.getDay() == 2) {
                firstAddCell = 1;
            } else if (dateFirst.getDay() == 3) {
                firstAddCell = 2;
            } else if (dateFirst.getDay() == 4) {
                firstAddCell = 3;
            } else if (dateFirst.getDay() == 5) {
                firstAddCell = 4;
            } else if (dateFirst.getDay() == 6) {
                firstAddCell = 5;
            }

            if (dateLast.getDay() == 1) {
                lastAddCell = 6;
            } else if (dateLast.getDay() == 2) {
                lastAddCell = 5;
            } else if (dateLast.getDay() == 3) {
                lastAddCell = 4;
            } else if (dateLast.getDay() == 4) {
                lastAddCell = 3;
            } else if (dateLast.getDay() == 5) {
                lastAddCell = 2;
            } else if (dateLast.getDay() == 6) {
                lastAddCell = 1;
            }

        } else if (ConstValue.HOLIDAY_START_DAY.equalsIgnoreCase("Tue")) {
            equldate = 2;

            if (dateFirst.getDay() == 0) {
                firstAddCell = 5;
            } else if (dateFirst.getDay() == 1) {
                firstAddCell = 6;
            } else if (dateFirst.getDay() == 3) {
                firstAddCell = 2;
            } else if (dateFirst.getDay() == 4) {
                firstAddCell = 3;
            } else if (dateFirst.getDay() == 5) {
                firstAddCell = 4;
            } else if (dateFirst.getDay() == 6) {
                firstAddCell = 5;
            }

            if (dateLast.getDay() == 2) {
                lastAddCell = 6;
            } else if (dateLast.getDay() == 3) {
                lastAddCell = 5;
            } else if (dateLast.getDay() == 4) {
                lastAddCell = 4;
            } else if (dateLast.getDay() == 5) {
                lastAddCell = 3;
            } else if (dateLast.getDay() == 6) {
                lastAddCell = 2;
            } else if (dateLast.getDay() == 0) {
                lastAddCell = 1;
            }

        } else if (ConstValue.HOLIDAY_START_DAY.equalsIgnoreCase("Wed")) {
            equldate = 3;

            if (dateFirst.getDay() == 0) {
                firstAddCell = 4;
            } else if (dateFirst.getDay() == 1) {
                firstAddCell = 5;
            } else if (dateFirst.getDay() == 2) {
                firstAddCell = 6;
            } else if (dateFirst.getDay() == 4) {
                firstAddCell = 1;
            } else if (dateFirst.getDay() == 5) {
                firstAddCell = 2;
            } else if (dateFirst.getDay() == 6) {
                firstAddCell = 3;
            }

            if (dateLast.getDay() == 3) {
                lastAddCell = 6;
            } else if (dateLast.getDay() == 4) {
                lastAddCell = 5;
            } else if (dateLast.getDay() == 5) {
                lastAddCell = 4;
            } else if (dateLast.getDay() == 6) {
                lastAddCell = 3;
            } else if (dateLast.getDay() == 1) {
                lastAddCell = 2;
            } else if (dateLast.getDay() == 1) {
                lastAddCell = 1;
            }

        } else if (ConstValue.HOLIDAY_START_DAY.equalsIgnoreCase("Thu")) {
            equldate = 4;

            if (dateFirst.getDay() == 0) {
                firstAddCell = 3;
            } else if (dateFirst.getDay() == 1) {
                firstAddCell = 4;
            } else if (dateFirst.getDay() == 2) {
                firstAddCell = 5;
            } else if (dateFirst.getDay() == 3) {
                firstAddCell = 6;
            } else if (dateFirst.getDay() == 5) {
                firstAddCell = 1;
            } else if (dateFirst.getDay() == 6) {
                firstAddCell = 2;
            }

            if (dateLast.getDay() == 4) {
                lastAddCell = 6;
            } else if (dateLast.getDay() == 5) {
                lastAddCell = 5;
            } else if (dateLast.getDay() == 6) {
                lastAddCell = 4;
            } else if (dateLast.getDay() == 0) {
                lastAddCell = 3;
            } else if (dateLast.getDay() == 1) {
                lastAddCell = 2;
            } else if (dateLast.getDay() == 2) {
                lastAddCell = 1;
            }

        } else if (ConstValue.HOLIDAY_START_DAY.equalsIgnoreCase("Fri")) {
            equldate = 5;

            if (dateFirst.getDay() == 0) {
                firstAddCell = 2;
            } else if (dateFirst.getDay() == 1) {
                firstAddCell = 3;
            } else if (dateFirst.getDay() == 2) {
                firstAddCell = 4;
            } else if (dateFirst.getDay() == 3) {
                firstAddCell = 5;
            } else if (dateFirst.getDay() == 4) {
                firstAddCell = 6;
            } else if (dateFirst.getDay() == 6) {
                firstAddCell = 1;
            }

            if (dateLast.getDay() == 5) {
                lastAddCell = 6;
            } else if (dateLast.getDay() == 6) {
                lastAddCell = 5;
            } else if (dateLast.getDay() == 0) {
                lastAddCell = 4;
            } else if (dateLast.getDay() == 1) {
                lastAddCell = 3;
            } else if (dateLast.getDay() == 2) {
                lastAddCell = 2;
            } else if (dateLast.getDay() == 3) {
                lastAddCell = 1;
            }
        } else if (ConstValue.HOLIDAY_START_DAY.equalsIgnoreCase("Sat")) {
            equldate = 6;

            if (dateFirst.getDay() == 0) {
                firstAddCell = 1;
            } else if (dateFirst.getDay() == 1) {
                firstAddCell = 2;
            } else if (dateFirst.getDay() == 2) {
                firstAddCell = 3;
            } else if (dateFirst.getDay() == 3) {
                firstAddCell = 4;
            } else if (dateFirst.getDay() == 4) {
                firstAddCell = 5;
            } else if (dateFirst.getDay() == 5) {
                firstAddCell = 6;
            }

            if (dateLast.getDay() == 6) {
                lastAddCell = 6;
            } else if (dateLast.getDay() == 0) {
                lastAddCell = 5;
            } else if (dateLast.getDay() == 1) {
                lastAddCell = 4;
            } else if (dateLast.getDay() == 2) {
                lastAddCell = 3;
            } else if (dateLast.getDay() == 3) {
                lastAddCell = 2;
            } else if (dateLast.getDay() == 4) {
                lastAddCell = 1;
            }

        }

        for (int i = totalDay; i > totalDay - firstAddCell; i--) {
            days.add(0, i);
            months.add(0, current_month - 1);
        }

        for (int i = 1; i <= lastAddCell; i++) {
            days.add(days.size(), i);
            months.add(months.size(), current_month + 1);
        }

        Log.e("Holiday", "finals" + days.toString());
        Log.e("Holiday", "finals Month" + months.toString());

        max_days = days.size();

        new getAttendenceTask().execute();

    }

    public void showNoticePopup(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Get the layout inflater
        LayoutInflater inflater = this.getLayoutInflater();

        View dview = inflater.inflate(R.layout.dialog_attend_note, null);
        TextView txtnote = (TextView) dview.findViewById(R.id.note);
        //try {
        //JSONObject obj = objAttendenceData.getJSONObject(position);
        txtnote.setText(note_array.get(position));
        //} catch (JSONException e) {
        //    e.printStackTrace();
        //}

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(dview)
                // Add action buttons
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // sign in the user ...
                    }
                });
        builder.show();
    }

    public class AttendanceAdapter extends BaseAdapter {

        Date todayDate = CommonClass.getTodayDate();

        @Override
        public int getCount() {
            return max_days;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
            convertView = inflater.inflate(R.layout.row_of_day, null);
            TextView present = (TextView) convertView.findViewById(R.id.present);
            TextView date = (TextView) convertView.findViewById(R.id.date);
            TextView month = (TextView) convertView.findViewById(R.id.month);

            //ate.setText(String.valueOf(position + 1));
            Calendar calendarMonthName = Calendar.getInstance();
            calendarMonthName.set(current_year, current_month - 1, position + 1);
            //month.setText(CommonClass.convetDateToString(calendarMonthName.getTime(), 3));

            int dates = days.get(position);
            int monthss = months.get(position);

            try {
                Date a_date = CommonClass.convetStringToDate(String.valueOf(current_year) + "-" + String.valueOf(monthss) + "-" + String.valueOf(dates), 1);
                month.setText(day_array.get(a_date.getDay()));

                date.setText(String.valueOf(dates));

                if (monthss == current_month) {
                    /*if (day_array.get(a_date.getDay()).equalsIgnoreCase(ConstValue.HOLIDAY_END_DAY)) {
                        convertView.setBackgroundColor(getResources().getColor(R.color.color_3));
                    }*/
                } else {
                    date.setTextColor(Color.parseColor("#e5e5e5"));
                    month.setTextColor(Color.parseColor("#e5e5e5"));
                }

                boolean is_attend = false;
                for (int i = 0; i < objAttendenceData.length(); i++) {
                    JSONObject obj = objAttendenceData.getJSONObject(i);
                    Date b_date = CommonClass.convetStringToDate(obj.getString("attendence_date"), 1);
                    if (a_date.equals(b_date)) {
                        is_attend = true;
                        if (obj.getString("attended").equalsIgnoreCase("1")) {
                            present.setText(getResources().getString(R.string.p));
                            present.setTextColor(Color.GREEN);
                            note_array.add(position, obj.getString("attendence_reason"));
                        } else if (obj.getString("attended").equalsIgnoreCase("0")) {
                            present.setText(getResources().getString(R.string.a1));
                            present.setTextColor(Color.RED);
                            note_array.add(position, obj.getString("attendence_reason"));
                        }

                    }
                }

                if (a_date.equals(todayDate)) {
                    date.setTextColor(getResources().getColor(R.color.color_3));
                    month.setTextColor(getResources().getColor(R.color.color_3));
                }

                if (!is_attend) {
                    present.setText(getResources().getString(R.string.n));
                    note_array.add(position, "");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return convertView;
        }
    }

    public class getAttendenceTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.
            String responseString = null;

            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);

            nameValuePairs.add(new BasicNameValuePair("student_id", common.getSession(ConstValue.COMMON_KEY)));
            nameValuePairs.add(new BasicNameValuePair("year", String.valueOf(current_year)));
            nameValuePairs.add(new BasicNameValuePair("month", String.valueOf(current_month)));

            JSONParser jsonParser = new JSONParser();

            try {
                String json_responce = jsonParser.makeHttpRequest(ConstValue.EMPLOYEE_PLANO_URL, "POST", nameValuePairs);
                Log.e("responce", json_responce);
                JSONObject jObj = new JSONObject(json_responce);
                if (jObj.has("responce") && !jObj.getBoolean("responce")) {
                    responseString = jObj.getString("error");
                } else {
                    if (jObj.has("data")) {
                        objAttendenceData = jObj.getJSONArray("data");

                    } else {
                        responseString = "User not found";
                    }
                }


            } catch (JSONException e) {
                // TODO Auto-generated catch block
                responseString = e.getMessage();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                responseString = e.getMessage();
                e.printStackTrace();
            }

            // TODO: register the new account here.
            return responseString;
        }

        @Override
        protected void onPostExecute(final String success) {

            if (success == null) {
                if (objAttendenceData != null) {
                    note_array.clear();
                    gridView.setAdapter(adapter);
                }
            } else {
                Toast.makeText(getApplicationContext(), success, Toast.LENGTH_LONG).show();
            }
        }

        @Override
        protected void onCancelled() {
        }
    }

}
