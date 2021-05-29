package co.tysa;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;

import co.tysa.R;

import java.util.Locale;

import util.ContextWrapper;
import util.LanguagePrefs;


public class CommonAppCompatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LanguagePrefs.initRTL(this, LanguagePrefs.getLang(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        Intent intent = null;
        //noinspection SimplifiableIfStatement
        switch (id) {
            case android.R.id.home:
                finish();
                break;
            case R.id.action_profile:
            //    intent = new Intent(this, ProfileActivity.class);
            //    break;
            //case R.id.action_result:
            //    intent = new Intent(this, ResultActivity.class);
            //    break;
            case R.id.action_exam:
                intent = new Intent(this, ExamActivity.class);
                break;
            case R.id.action_teacher:
                intent = new Intent(this, PosteActivity.class);
                break;
            //case R.id.action_growth:
            //    intent = new Intent(this, GrowthActivity.class);
            //    break;
            //case R.id.action_holiday:
            //    intent = new Intent(this, HolidaysActivity.class);
            //    break;
            //case R.id.action_news:
            //    intent = new Intent(this, NewsActivity.class);
            //    break;
            //case R.id.action_notice:
            //    intent = new Intent(this, NoticeActivity.class);
            //    break;
            case R.id.action_school:
                intent = new Intent(this, SchoolProfileActivity.class);
                break;
            //case R.id.action_time_table:
            //    intent = new Intent(this, TimetableActivity.class);
             //   break;
            //case R.id.action_quiz:
            //    intent = new Intent(this, Quiz_subjectActivity.class);
            //    break;
            //case R.id.action_fees:
             //   intent = new Intent(this, FeesActivity.class);
             //   break;
            //case R.id.action_book:
            //    intent = new Intent(this, BookActivity.class);
            //    break;
            //case R.id.action_notification:
              //  intent = new Intent(this, NotificationActivity.class);
               // break;
        }
        if (intent != null) {
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        Locale newLocale = new Locale(LanguagePrefs.getLang(newBase));
        // .. create or get your new Locale object here.
        Context context = ContextWrapper.wrap(newBase, newLocale);
        super.attachBaseContext(context);
    }

}
