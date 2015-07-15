package devang.developer.com.diseasetracker;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class Results extends ActionBarActivity implements View.OnClickListener{
    private static String LOG_TAG = "CardViewActivity";
    public static int dp;
    public static int px = 0;
    public ContentValues contentValues;
    ImageButton ibSave;
    android.support.v7.widget.RecyclerView.Adapter mAdapter;
    android.support.v7.widget.RecyclerView.LayoutManager mLayoutManager;
    RecyclerView mRecyclerView;
    ArrayList results;
    ImageButton i1,i2,i3,i4,i5;
    EditText note;
    int rating = 0;
    int isSaved = 0;
    String Note = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        Toolbar toolbar = (Toolbar)findViewById(R.id.tool_bar2);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_action_discard);
        toolbar.hideOverflowMenu();
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.action_restart) {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                            Results.this);
                    alertDialogBuilder.setTitle("Confirm restart");
                    alertDialogBuilder
                            .setMessage("Do you want to restart your analysis?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Intent i = new Intent();
                                    i.setClassName("devang.developer.com.diseasetracker","devang.developer.com.diseasetracker.Symptoms");
                                    i.putExtra("verify", 1);
                                    startActivity(i);
                                    Results.this.finish();
                                }
                            })
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }
                return true;
            }
        });
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        Results.this);
                alertDialogBuilder.setTitle("Confirm exit");
                alertDialogBuilder
                        .setMessage("Do you want to end your analysis?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                startActivity(new Intent(Results.this, MainActivity.class));
                                Results.this.finish();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

        mRecyclerView = (RecyclerView)findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MyRecyclerViewAdapter(getDataSet());
        mRecyclerView.setAdapter(mAdapter);
        dp = 105;
        DisplayMetrics bundle = getResources().getDisplayMetrics();
        setRecycleViewHeightBasedOnChildren(mRecyclerView, getApplicationContext());
        i1=(ImageButton)findViewById(R.id.iv1);
        i2=(ImageButton)findViewById(R.id.iv2);
        i3=(ImageButton)findViewById(R.id.iv3);
        i4=(ImageButton)findViewById(R.id.iv4);
        i5=(ImageButton)findViewById(R.id.iv5);
        note = (EditText)findViewById(R.id.note);
        ibSave=(ImageButton)findViewById(R.id.saveInDatabase);
        ibSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(note.getText().toString() != null)
                    Note = note.getText().toString();
                sort_final_scores();
                put_values_in_content_values();
                Toast.makeText(getApplicationContext(), "New record inserted", Toast.LENGTH_SHORT).show();
            }
        });
        i1.setOnClickListener(this);
        i2.setOnClickListener(this);
        i3.setOnClickListener(this);
        i4.setOnClickListener(this);
        i5.setOnClickListener(this);
    }
    @Override
    protected void onResume(){
        super.onResume();
        ((MyRecyclerViewAdapter) mAdapter).setOnItemClickListener(new MyRecyclerViewAdapter
                .MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Intent i = new Intent();
                i.setClassName("devang.developer.com.diseasetracker", "devang.developer.com.diseasetracker.ResultDetails");
                TextView t = (TextView)v.findViewById(R.id.textView24);
                TextView t1 = (TextView)v.findViewById(R.id.textView28);
                TextView t2 = (TextView)v.findViewById(R.id.textView25);
                RoundCornerProgressBar p = (RoundCornerProgressBar)v.findViewById(R.id.progressBar6);
                i.putExtra("name", t.getText().toString());
                i.putExtra("score", t1.getText().toString());
                i.putExtra("risk", t2.getText().toString());
                i.putExtra("progress", (int)p.getProgress());
                startActivity(i);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(isSaved == 0)
        {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    this);

            // set title
            alertDialogBuilder.setTitle("Confirm exit");

            // set dialog message
            alertDialogBuilder
                    .setMessage("Your report is not saved yet..")
                    .setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            startActivity(new Intent(Results.this, MainActivity.class));
                            Results.this.finish();
                        }
                    })
                    .setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {
                            // if this button is clicked, just close
                            // the dialog box and do nothing
                            dialog.cancel();
                        }
                    });

            // create alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();

            // show it
            alertDialog.show();
        }
        else
        {
            startActivity(new Intent(Results.this, MainActivity.class));
            Results.this.finish();
        }
    }

    public static void setRecycleViewHeightBasedOnChildren(RecyclerView recyclerview, Context context)
    {
        android.support.v7.widget.RecyclerView.Adapter adapter = recyclerview.getAdapter();
        if (adapter == null)
        {
            return;
        } else
        {
            android.view.ViewGroup.LayoutParams layoutparams = recyclerview.getLayoutParams();
            layoutparams.height = 202 * adapter.getItemCount() ;
            recyclerview.setLayoutParams(layoutparams);
            recyclerview.requestLayout();
            return;
        }
    }
    private void sort_final_scores()
    {
        for (int i = 0; i < values.finalScores.length; i++)
        {
            for (int j = i + 1; j < values.finalScores.length; j++)
            {
                if (values.finalScores[i] < values.finalScores[j])
                {
                    int k = values.finalScores[i];
                    values.finalScores[i] = values.finalScores[j];
                    values.finalScores[j] = k;
                    String s = values.finalDiseases[i];
                    values.finalDiseases[i] = values.finalDiseases[j];
                    values.finalDiseases[j] = s;
                }
            }

        }

    }

    public ArrayList getDataSet()
    {
        results = new ArrayList();
        int i = 0;
        while (i < values.finalScores.length)
        {
            Object obj;
            if (values.finalScores[i] <= 50)
            {
                obj = "Low Risk";
            } else
            if (values.finalScores[i] <= 75)
            {
                obj = "Medium Risk";
            } else
            {
                obj = "High Risk";
            }
            obj = new DataObject(values.finalDiseases[i], ((String) (obj)), values.finalScores[i]);
            if (values.finalScores[i] >= 25)
            {
                results.add(obj);
            }
            i++;
        }
        return results;
    }
    private void put_values_in_content_values()
    {
        contentValues = new ContentValues();
        contentValues.put(ReportListProvider.KEY_DISEASE1, values.finalDiseases[0]);
        contentValues.put(ReportListProvider.KEY_VALUE1, values.finalScores[0]);
        if (values.finalScores.length >= 2)
        {
            contentValues.put(ReportListProvider.KEY_DISEASE2, values.finalDiseases[1]);
            contentValues.put(ReportListProvider.KEY_VALUE2, values.finalScores[1]);
        }
        if (values.finalScores.length >= 3)
        {
            contentValues.put(ReportListProvider.KEY_DISEASE3, values.finalDiseases[2]);
            contentValues.put(ReportListProvider.KEY_VALUE3, values.finalScores[2]);
        }
        if (values.finalScores.length >= 4)
        {
            contentValues.put(ReportListProvider.KEY_DISEASE4, values.finalDiseases[3]);
            contentValues.put(ReportListProvider.KEY_VALUE4, values.finalScores[3]);
        }
        if (values.finalScores.length >= 5)
        {
            contentValues.put(ReportListProvider.KEY_DISEASE5, values.finalDiseases[4]);
            contentValues.put(ReportListProvider.KEY_VALUE5, values.finalScores[4]);
        }
        if (values.finalScores.length >= 6)
        {
            contentValues.put(ReportListProvider.KEY_DISEASE6, values.finalDiseases[5]);
            contentValues.put(ReportListProvider.KEY_VALUE6, values.finalScores[5]);
        }
        if (values.finalScores.length >= 7)
        {
            contentValues.put(ReportListProvider.KEY_DISEASE7, values.finalDiseases[6]);
            contentValues.put(ReportListProvider.KEY_VALUE7, values.finalScores[6]);
        }
        if (values.finalScores.length >= 8)
        {
            contentValues.put(ReportListProvider.KEY_DISEASE8, values.finalDiseases[7]);
            contentValues.put(ReportListProvider.KEY_VALUE8, values.finalScores[7]);
        }
        if (values.finalScores.length >= 9)
        {
            contentValues.put(ReportListProvider.KEY_DISEASE9, values.finalDiseases[8]);
            contentValues.put(ReportListProvider.KEY_VALUE9, values.finalScores[8]);
        }
        if (values.finalScores.length >= 10)
        {
            contentValues.put(ReportListProvider.KEY_DISEASE10, values.finalDiseases[9]);
            contentValues.put(ReportListProvider.KEY_VALUE10, values.finalScores[9]);
        }
        String s = (new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")).format(Calendar.getInstance().getTime());
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
        contentValues.put(ReportListProvider.KEY_DATE, s);
        int i = Calendar.getInstance().get(Calendar.MONTH);
        int j = Calendar.getInstance().get(Calendar.YEAR);
        contentValues.put(ReportListProvider.KEY_YEAR, Integer.valueOf(j));
        contentValues.put(ReportListProvider.KEY_MONTH, Integer.valueOf(i));
        contentValues.put(ReportListProvider.KEY_NOTE, Note);
        contentValues.put(ReportListProvider.KEY_RATING, rating);
        Uri uri = getContentResolver().insert(ReportListProvider.CONTENT_URI, contentValues);
        isSaved = 1;
    }

    @Override
    public void onClick(View v) {
        if(v == i1)
            rating = 1;
        else if(v == i2)
            rating= 2;
        else if(v==i3)
            rating=3;
        else  if(v==i4)
            rating=4;
        else
            rating=5;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_results, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
