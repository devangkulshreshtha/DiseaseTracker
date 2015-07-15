package devang.developer.com.diseasetracker;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;


public class Symptoms extends ActionBarActivity implements View.OnClickListener{

    public static int prgmImages[] = {R.drawable.ic_action_done,R.drawable.ic_action_done , R.drawable.ic_action_done, R.drawable.ic_action_done, R.drawable.ic_action_done, R.drawable.ic_action_done, R.drawable.ic_action_done, R.drawable.ic_action_done};
    public static String prgmNameList[];
    Button button;
    ListView lv;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(Symptoms.this,MainActivity.class));
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptoms);
        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(this);
        prgmNameList = values.problems;
        if(getIntent().getIntExtra("verify", 0) == 1)
        {
            for (int i = 0; i < values.selected_risk_factors.length; i++) {
                values.selected_risk_factors[i] = 0;
            }

            for (int j = 0; j < values.selectedsymptoms.length; j++) {
                values.selectedsymptoms[j] = 0;
            }

            for (int k = 0; k < values.selectesSymptoms2.length; k++) {
                values.selectesSymptoms2[k] = 0;
            }

            for (int l = 0; l < values.disease_score.length; l++) {
                values.disease_score[l] = 0.0;
            }
            values.x = 0;
            values.direct = 0;
            values.index = 0;
            values.counter = 0;
        }
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
        lv = (ListView)findViewById(R.id.listView);
        Intent bundle = getIntent();
        if (bundle != null)
        {
            if (bundle.getIntExtra("problemindex", 942) != 942)
            {
                lv.setAdapter(new CustomAdapter3(this, prgmNameList, prgmImages));
            } else
            {
                lv.setAdapter(new CustomAdapter(this, prgmNameList, prgmImages));
            }
        } else
        {
            lv.setAdapter(new CustomAdapter(this, prgmNameList, prgmImages));
        }
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(Symptoms.this, Symptoms2.class));
    }
}
