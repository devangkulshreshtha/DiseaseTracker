package devang.developer.com.diseasetracker;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;


public class SymptomsDetail extends ActionBarActivity {
    ImageButton button;
    ListView lv;
    int problem_index;
    public static String prgmNameList[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptoms_detail);
        Toolbar toolbar = (Toolbar)findViewById(R.id.tool_bar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        Intent i1 = getIntent();
        prgmNameList = i1.getStringArrayExtra("symptoms");
        problem_index = i1.getIntExtra("problem", 0);
        lv = (ListView)findViewById(R.id.listView);
        lv.setAdapter(new CustomAdapter2(this, prgmNameList, problem_index));

        button=(ImageButton)findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i  = new Intent();
                i.setClassName("devang.developer.com.diseasetracker", "devang.developer.com.diseasetracker.Symptoms");
                i.putExtra("problemindex", problem_index);
                startActivity(i);
                finish();
            }
        });
    }
}
