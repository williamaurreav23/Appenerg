package co.tysa;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;

import androidx.appcompat.widget.Toolbar;

import co.tysa.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;

import adapter.TeacherAdapter;

public class PrediosActivity extends CommonAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.color_51));
        }

        ListView listview = (ListView)findViewById(R.id.listview);
        TeacherAdapter adapter = new TeacherAdapter(this, new JSONArray());
        listview.setAdapter(adapter);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PrediosActivity.this, EnquiryActivity.class);
                startActivity(intent);
            }
        });
    }

}
