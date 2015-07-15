package devang.developer.com.diseasetracker;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import lt.lemonlabs.android.expandablebuttonmenu.ExpandableButtonMenu;
import lt.lemonlabs.android.expandablebuttonmenu.ExpandableMenuOverlay;


public class History extends ActionBarActivity {
    TextView  tvDate,tvTime;
    MyViewPagerAdapter adapter;
    ViewPager pager;
    TextView tvrating,tvnote;
    ImageView ivrate,ivnote;
    String both;
    int operation = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history) ;
        Toolbar toolbar = (Toolbar)findViewById(R.id.tool_bar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.hideOverflowMenu();
        Toast.makeText(this, "Swipe on the upper area to see other diseases", Toast.LENGTH_LONG).show();
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.action_delete) {


                    Snackbar.make(findViewById(android.R.id.content), "Report deleted",Snackbar.LENGTH_LONG)
                            .setAction("Undo", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    operation = 0;
                                }
                            })
                            .setActionTextColor(Color.parseColor("#ff4081"))
                            .show();

                    new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

                        @Override
                        public void run() {
                            if(operation == 1)
                            {
                                String mselectionClause = ReportListProvider.KEY_DATE + " = ?";
                                String[] mselectionArgs = {""};
                                mselectionArgs[0] = both;
                                int garbage = getContentResolver().delete(ReportListProvider.CONTENT_URI, mselectionClause, mselectionArgs);
                                startActivity(new Intent(History.this, MainActivity.class));
                                History.this.finish();
                            }
                        }
                    }, 6500);

                }
                else if(item.getItemId() == R.id.action_exit_to_app)
                {
                    startActivity(new Intent(History.this, MainActivity.class));
                    History.this.finish();
                }
                return true;
            }
        });
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        tvDate = (TextView)findViewById(R.id.textView5);
        tvTime = (TextView)findViewById(R.id.textView6);
        tvrating = (TextView)findViewById(R.id.tvrating);
        tvnote = (TextView)findViewById(R.id.tvnote);
        Intent intent = getIntent();
        String date="";
        String time="";
        ivnote=(ImageView)findViewById(R.id.ivnote);
        ivrate=(ImageView)findViewById(R.id.ivrate);
        int rating = intent.getIntExtra("rating",0);
        String note = intent.getStringExtra("note");
        if(rating!=0 && !note.equals(""))
        {
            String[] ratings = {"Poor", "Soso", "Good", "Excellent", "Outstanding"};
            tvrating.setText(ratings[rating - 1]);
            tvnote.setText(note);
        }
        else if(rating == 0 && !note.equals(""))
        {
            ivnote.setVisibility(View.INVISIBLE);
            tvnote.setVisibility(View.INVISIBLE);
            ivrate.setImageResource(R.drawable.note);
            tvrating.setText(note);
        }
        else if(rating!=0 && note.equals(""))
        {
            String[] ratings = {"Poor", "Soso", "Good", "Excellent", "Outstanding"};
            ivnote.setVisibility(View.INVISIBLE);
            tvnote.setVisibility(View.INVISIBLE);
            tvrating.setText(ratings[rating - 1]);
        }
        else
        {
            ivnote.setVisibility(View.INVISIBLE);
            tvnote.setVisibility(View.INVISIBLE);
            ivrate.setVisibility(View.INVISIBLE);
            tvrating.setVisibility(View.INVISIBLE);
        }

        both = intent.getStringExtra("date");
        for(int i=0;i<10;i++)
        {
            date += both.charAt(i);
        }
        for(int i=11;i<both.length();i++)
        {
            time += both.charAt(i);
        }
        tvDate.setText(date);
        tvTime.setText(time);
        String[] diseases = intent.getStringArrayExtra("diseases");
        int[] scores = intent.getIntArrayExtra("scores");
        adapter = new MyViewPagerAdapter(History.this, diseases, scores);
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_history, menu);
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
