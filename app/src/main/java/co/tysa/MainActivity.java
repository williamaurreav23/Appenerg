package co.tysa;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import co.tysa.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import Config.ConstValue;
import adapter.ExamAdapter;
import adapter.ManuAdapter;
import adapter.PlanoAdapter;
import dialog.ChooseLanguageDialog;
import fcm.MyFirebaseRegister;
import util.CommonClass;
import util.ContextWrapper;
import util.DbHelper;
import util.JSONReader;
import util.LanguagePrefs;
import util.Queries;
import util.RecyclerTouchListener;


public class MainActivity extends AppCompatActivity implements GridView.OnItemClickListener {

    AsyncTask<Void, Void, Void> mRegisterTask;

    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private static final String TAG = "MainActivity";

    private BroadcastReceiver mRegistrationBroadcastReceiver;
    private static SQLiteDatabase db;
    private static DbHelper dbHelper;
    private static Queries q;

    ImageView logout;
    List<String> menu_name;
    List<Integer> menu_icon;
    CommonClass common;

    private String school_website = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = new DbHelper(this);
        q = new Queries(db, dbHelper);
        LanguagePrefs.initRTL(this, LanguagePrefs.getLang(this));
        setContentView(R.layout.activity_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//getSupportActionBar().hide();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#CC000000"));
        }



        ListView listView = (ListView)findViewById(R.id.listView);
        PlanoAdapter adapter1 = new PlanoAdapter(this, new JSONArray());
        listView.setAdapter(adapter1);
        //FloatingActionButton fab1 = (FloatingActionButton) findViewById(R.id.fab);
        //fab1.setOnClickListener(new View.OnClickListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                startActivity(intent);
            }
        });

        if (getIntent().getStringExtra("notification") != null) {
            Intent startmainIntent = new Intent(this, NotificationActivity.class);
            startActivity(startmainIntent);
        }

        common = new CommonClass(this);

        ImageView iv_language = (ImageView) findViewById(R.id.imglanguage);
        iv_language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChooseLanguageDialog chooseLanguageDialog = new ChooseLanguageDialog(MainActivity.this);
                chooseLanguageDialog.show();
            }
        });


        logout = (ImageView) findViewById(R.id.imglogout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  SharedPreferences myPrefs = getSharedPreferences("MY_PREF",
                        MODE_PRIVATE);
                SharedPreferences.Editor editor = myPrefs.edit();
                editor.clear();
                editor.commit();
                Toast.makeText(getApplicationContext(),getString(R.string.main_activity_logout), Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(MainActivity.this, LoginActivity.class);
                intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent1); */

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);

                // Setting Dialog Title
                alertDialog.setTitle(R.string.logout);

                // Setting Dialog Message
                alertDialog.setMessage(R.string.are_you_sure_you_want_to_logout_this_app);


                // Setting Positive "Yes" Button
                alertDialog.setPositiveButton(getResources().getText(android.R.string.yes), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // User pressed YES button. Write Logic Here
                        SharedPreferences myPrefs = getSharedPreferences(ConstValue.PREF_NAME,
                                MODE_PRIVATE);
                        SharedPreferences.Editor editor = myPrefs.edit();
                        editor.clear();
                        editor.commit();
                        Toast.makeText(getApplicationContext(), getString(R.string.main_activity_logout), Toast.LENGTH_SHORT).show();
                        Intent intent1 = new Intent(MainActivity.this, LoginActivity.class);
                        intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent1);
                        finish();
                    }
                });

                // Setting Negative "NO" Button
                alertDialog.setNegativeButton(getResources().getText(android.R.string.no), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // User pressed No button. Write Logic Here
                    }
                });

                // Showing Alert Message
                alertDialog.show();
            }
        });


        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent intent = new Intent(MainActivity.this, EnquiryActivity.class);
                startActivity(intent);
            }
        });
        final FloatingActionButton fab_web = (FloatingActionButton) findViewById(R.id.fab_web);

        try {
            school_website = new JSONReader(this).getJSONObject("employee_data").getString("school_website");
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (school_website.isEmpty()) {
            fab_web.setVisibility(View.GONE);
        } else {
            fab_web.setVisibility(View.VISIBLE);
        }

        fab_web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent intent = new Intent(MainActivity.this, ViewBookActivity.class);
                intent.putExtra("title", getResources().getString(R.string.app_name));
                intent.putExtra("book_pdf", school_website);
                startActivity(intent);
            }
        });
        final FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent intent = new Intent(MainActivity.this, TopStudentActivity.class);
                startActivity(intent);
            }
        });


        menu_icon = new ArrayList<Integer>();
        menu_name = new ArrayList<String>();

        menu_icon.add(R.drawable.ic_menu_01);
        menu_icon.add(R.drawable.ic_menu_02);
        menu_icon.add(R.drawable.ic_menu_03);
        menu_icon.add(R.drawable.ic_menu_04);
        menu_icon.add(R.drawable.ic_menu_05);


        menu_name.add(getString(R.string.menu_profile));
        menu_name.add(getString(R.string.menu_attendence));
        menu_name.add(getString(R.string.menu_exam));
        menu_name.add(getString(R.string.menu_result));
        menu_name.add(getString(R.string.menu_teacher));


        GridView gridview = (GridView) findViewById(R.id.gridView);
        ManuAdapter adapter = new ManuAdapter(getApplicationContext(), menu_name, menu_icon);
        gridview.setAdapter(adapter);
        gridview.setOnItemClickListener(this);

        gridview.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                /*if(scrollState == SCROLL_STATE_TOUCH_SCROLL){
                    fab.setVisibility(View.INVISIBLE);
                    fab2.setVisibility(View.INVISIBLE);
                }else{
                    fab.setVisibility(View.VISIBLE);
                    fab2.setVisibility(View.VISIBLE);
                }*/
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                /*int lastItem = firstVisibleItem + visibleItemCount;
                if (lastItem == totalItemCount) {
                    fab.setVisibility(View.INVISIBLE);
                }else {
                    fab.setVisibility(View.VISIBLE);
                }*/
            }
        });

        MyFirebaseRegister myFirebaseRegister = new MyFirebaseRegister(this);
        myFirebaseRegister.RegisterUser();

    }

    public Queries getQueries() {
        return q;
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        common.open_screen(position);
    }

    @Override
    protected void onResume() {
        super.onResume();
        /*LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(QuickstartPreferences.REGISTRATION_COMPLETE));*/

        // register GCM registration complete receiver
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(ConstValue.REGISTRATION_COMPLETE));

        // register new push message receiver
        // by doing this, the activity will be notified each time a new message arrives
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(ConstValue.PUSH_NOTIFICATION));

        // clear the notification area when the app is opened
        //NotificationUtils.clearNotifications(getApplicationContext());
    }

    @Override
    protected void onPause() {
        //LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
        super.onPause();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        Locale newLocale = new Locale(LanguagePrefs.getLang(newBase));
        // .. create or get your new Locale object here.
        Context context = ContextWrapper.wrap(newBase, newLocale);
        super.attachBaseContext(context);
    }

}
