package devang.developer.com.diseasetracker;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


public class RiskFactors extends ActionBarActivity {
    Button button;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_risk_factors);
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
        lv = (ListView)findViewById(R.id.listView2);
        lv.setAdapter(new CustomAdapterRisk(this, values.riskFactors));
        button = (Button)findViewById(R.id.button4);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate_diseases_scores();
                values.toBeAskedSymptoms = getUnansweredSymptoms();
                if (values.toBeAskedSymptoms != null)
                {
                    startActivity(new Intent(RiskFactors.this, Questions.class));
                }
                else
                {
                    int i,j;
                    for(i=1;i<values.selectedsymptoms.length;i++)
                    {
                        if(values.selectedsymptoms[i] == 0)
                            break;
                    }
                    if(i == values.selectedsymptoms.length)
                    {
                        values.finalScores = new int[values.diseases.length];
                        values.finalDiseases = new String[values.diseases.length];
                        for (j=0;j<values.diseases.length;j++)
                        {
                            values.finalDiseases[j] = values.diseases[j];
                            values.finalScores[j] = 100;
                        }
                        values.direct = 1;
                        startActivity(new Intent(RiskFactors.this, Results.class));
                    }
                }

            }
        });
    }

    private int[] getUnansweredSymptoms()
    {
        int i1 = 0;
        for (int i = 0; i < values.disease_score.length;)
        {
            int k1 = i1;
            if (values.disease_score[i] >= 0.5D)
            {
                k1 = i1 + 1;
            }
            i++;
            i1 = k1;
        }

        for (int j = 0; j < values.disease_score.length; j++)
        {
            if (values.disease_score[j] < 0.5D)
            {
                continue;
            }
            for (i1 = 0; i1 < values.disease_info[j].length; i1++)
            {
                if (values.selectedsymptoms[values.disease_info[j][i1]] == 0)
                {
                    values.selectesSymptoms2[values.disease_info[j][i1]] = 1;
                }
            }

        }

        i1 = 0;
        boolean flag = false;
        for (int k = 0; k < values.selectesSymptoms2.length;)
        {
            int l1 = i1;
            if (values.selectesSymptoms2[k] == 1)
            {
                l1 = i1 + 1;
            }
            k++;
            i1 = l1;
        }

        if (i1 > 0)
        {
            int ai[] = new int[i1];
            int l = 0;
            int i2;
            for (int j1 = ((flag) ? 1 : 0); l < values.selectesSymptoms2.length; j1 = i2)
            {
                i2 = j1;
                if (values.selectesSymptoms2[l] == 1)
                {
                    ai[j1] = l;
                    i2 = j1 + 1;
                }
                l++;
            }

            return ai;
        } else
        {
            return null;
        }
    }
    private void calculate_diseases_scores()
    {
        for (int i = 0; i < values.disease_score.length; i++)
        {
            for (int j = 0; j < values.disease_info[i].length; j++)
            {
                if (values.selectedsymptoms[values.disease_info[i][j]] == 1)
                {
                    double ad[] = values.disease_score;
                    ad[i] = ad[i] + values.symptom_score[i][j];
                }
            }

        }

    }
}
