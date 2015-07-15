package devang.developer.com.diseasetracker;

import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;


public class ResultDetails extends ActionBarActivity {
    TextView name, riskstring,percent;
    RoundCornerProgressBar progressBar;
    ImageView iv1;
    ImageView iv2;
    ImageView iv3;
    ImageView iv4;
    ImageView iv5;
    ImageView iv6;
    ImageView iv7;
    ImageView tva3;
    RelativeLayout relativeLayout;
    TextView tv1;
    TextView tv10;
    TextView tv11;
    TextView tv12;
    TextView tv13;
    TextView tv14;
    TextView tv2;
    TextView tv3;
    TextView tv4;
    TextView tv5;
    TextView tv6;
    TextView tv7;
    TextView tv8;
    TextView tv9;
    TextView tva1;
    TextView tva2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_details);
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
        initializeViews();
        makeViewsInvisible();
        name=(TextView)findViewById(R.id.textView24);
        riskstring=(TextView)findViewById(R.id.textView25);
        percent=(TextView)findViewById(R.id.textView28);
        progressBar=(RoundCornerProgressBar)findViewById(R.id.progressBar6);
        relativeLayout = (RelativeLayout)findViewById(R.id.relativeLayout);
        String disease = getIntent().getStringExtra("name");
        int disease_index = get_index_of_disease(disease);
        int progress = getIntent().getIntExtra("progress", 0);
        name.setText(values.diseases[disease_index]);
        progressBar.setProgress(progress);
        String x;
        if(progress < 50)
            x = "Low risk";
        else if(progress < 75)
            x = "Medium risk";
        else
            x = "High risk";
        riskstring.setText(x);
        percent.setText("(" + String.valueOf(progress) + "%)");
        int bundle[] = values.disease_info[disease_index];
        int i = 1,count=0;
        while (i <= bundle.length)
        {
            TextView tv = (TextView)relativeLayout.findViewWithTag("tvindex" + String.valueOf(i));
            tv.setVisibility(View.VISIBLE);
            tv.setText(String.valueOf(i));
            TextView tv2 = (TextView)relativeLayout.findViewWithTag("tvdisease" + String.valueOf(i));
            tv2.setVisibility(View.VISIBLE);
            tv2.setText(values.symptoms[bundle[i - 1]]);
            ImageView iv = (ImageView)relativeLayout.findViewWithTag("iv" + String.valueOf(i));
            iv.setVisibility(View.VISIBLE);
            if (values.selectedsymptoms[bundle[i - 1]] == 1)
            {
                count ++ ;
                iv.setImageResource(R.drawable.ic_check_circle_black_24dp);
            } else
            {
                iv.setImageResource(R.drawable.ic_cancel_black_24dp);
            }
            i++;
        }
        TextView t = (TextView)findViewById(R.id.tvno);
        t.setText(String.valueOf(count) + "/");
        TextView t1 = (TextView)findViewById(R.id.tvtotal);
        t1.setText(String.valueOf(bundle.length));
        if(progress >= 75)
        {
            progressBar.setProgressColor(Color.parseColor("#B71C1C"));
            name.setTextColor(Color.parseColor("#B71C1C"));
            riskstring.setTextColor(Color.parseColor("#B71C1C"));
            percent.setTextColor(Color.parseColor("#B71C1C"));
        }
        else if(progress >= 50)
        {
            progressBar.setProgressColor(Color.parseColor("#ff9800"));
            name.setTextColor(Color.parseColor("#ff9800"));
            riskstring.setTextColor(Color.parseColor("#ff9800"));
            percent.setTextColor(Color.parseColor("#ff9800"));
        }
        else
        {
            progressBar.setProgressColor(Color.parseColor("#8bc34a"));
            name.setTextColor(Color.parseColor("#8bc34a"));
            riskstring.setTextColor(Color.parseColor("#8bc34a"));
            percent.setTextColor(Color.parseColor("#8bc34a"));
        }
    }
    private void initializeViews()
    {
        tv1 = (TextView)findViewById(R.id.textView30);
        tv2 = (TextView)findViewById(R.id.textView31);
        tv3 = (TextView)findViewById(R.id.textView32);
        tv4 = (TextView)findViewById(R.id.textView33);
        tv5 = (TextView)findViewById(R.id.textView34);
        tv6 = (TextView)findViewById(R.id.textView35);
        tv7 = (TextView)findViewById(R.id.textView36);
        tv8 = (TextView)findViewById(R.id.textView37);
        tv9 = (TextView)findViewById(R.id.textView38);
        tv10 = (TextView)findViewById(R.id.textView39);
        tv11 = (TextView)findViewById(R.id.textView40);
        tv12 = (TextView)findViewById(R.id.textView41);
        tv13 = (TextView)findViewById(R.id.textView42);
        tv14 = (TextView)findViewById(R.id.textView43);
        iv1 = (ImageView)findViewById(R.id.imageView2);
        iv2 = (ImageView)findViewById(R.id.imageView3);
        iv3 = (ImageView)findViewById(R.id.imageView4);
        iv4 = (ImageView)findViewById(R.id.imageView5);
        iv5 = (ImageView)findViewById(R.id.imageView6);
        iv6 = (ImageView)findViewById(R.id.imageView7);
        iv7 = (ImageView)findViewById(R.id.imageView8);
        tva1 = (TextView)findViewById(R.id.tva1);
        tva2 = (TextView)findViewById(R.id.tva2);
        tva3 = (ImageView)findViewById(R.id.tva3);
    }

    private void makeViewsInvisible()
    {
        tv1.setVisibility(View.INVISIBLE);
        tv2.setVisibility(View.INVISIBLE);
        tv3.setVisibility(View.INVISIBLE);
        tv4.setVisibility(View.INVISIBLE);
        tv5.setVisibility(View.INVISIBLE);
        tv6.setVisibility(View.INVISIBLE);
        tv7.setVisibility(View.INVISIBLE);
        tv8.setVisibility(View.INVISIBLE);
        tv9.setVisibility(View.INVISIBLE);
        tv10.setVisibility(View.INVISIBLE);
        tv11.setVisibility(View.INVISIBLE);
        tv12.setVisibility(View.INVISIBLE);
        tv13.setVisibility(View.INVISIBLE);
        tv14.setVisibility(View.INVISIBLE);
        tva1.setVisibility(View.INVISIBLE);
        tva2.setVisibility(View.INVISIBLE);
        tva3.setVisibility(View.INVISIBLE);
        iv1.setVisibility(View.INVISIBLE);
        iv2.setVisibility(View.INVISIBLE);
        iv3.setVisibility(View.INVISIBLE);
        iv4.setVisibility(View.INVISIBLE);
        iv5.setVisibility(View.INVISIBLE);
        iv6.setVisibility(View.INVISIBLE);
        iv7.setVisibility(View.INVISIBLE);
    }

    private void setTagstoViews()
    {
        tv1.setTag("tvindex1");
        tv2.setTag("tvindex2");
        tv3.setTag("tvindex3");
        tv4.setTag("tvindex4");
        tv5.setTag("tvindex5");
        tv6.setTag("tvindex6");
        tv7.setTag("tvindex7");
        tv8.setTag("tvdisease1");
        tv9.setTag("tvdisease2");
        tv10.setTag("tvdisease3");
        tv11.setTag("tvdisease4");
        tv12.setTag("tvdisease5");
        tv13.setTag("tvdisease6");
        tv14.setTag("tvdisease7");
        tva1.setTag("tvindex8");
        tva2.setTag("tvdisease8");
        tva3.setTag("iv8");
        iv1.setTag("iv1");
        iv2.setTag("iv2");
        iv3.setTag("iv3");
        iv4.setTag("iv4");
        iv5.setTag("iv5");
        iv6.setTag("iv6");
        iv7.setTag("iv7");

    }
    private int get_index_of_disease(String s)
    {
        for (int i = 0; i < values.diseases.length; i++)
        {
            if (values.diseases[i].equalsIgnoreCase(s))
            {
                return i;
            }
        }

        return 0;
    }
}
