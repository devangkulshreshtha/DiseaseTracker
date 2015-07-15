
package devang.developer.com.diseasetracker;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class Tab1 extends Fragment implements android.view.View.OnClickListener {

    private static int SPLASH_TIME_OUT = 450, SPLASH_TIME_OUT2 = 300;
    TextView tvPercent, tvDisease, tvDate;
    ProgressBar progressBar;
    Animation animFadein;
    Animation animFadein1;
    int i;
    ImageButton img;
    ImageButton img1;
    ImageButton img2;
    ImageButton img3;
    ImageButton img4;
    ImageButton img5;
    ImageButton img6;
    ImageButton img7;
    ProgressBar imgdirection;
    TextView tvtrack;
    View v;

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle) {
        v = layoutinflater.inflate(R.layout.tab_1, viewgroup, false);
        tvtrack = (TextView) v.findViewById(R.id.tvtrack);
        Typeface type = Typeface.createFromAsset(getActivity().getAssets(), "Sansation-Light.ttf");
        tvtrack.setTypeface(type);
        progressBar = (ProgressBar) v.findViewById(R.id.progressBar);
        tvPercent = (TextView) v.findViewById(R.id.tvPercent);
        tvDisease = (TextView) v.findViewById(R.id.tvDisease);
        tvDate = (TextView) v.findViewById(R.id.tvDate);
        imgdirection = (ProgressBar) v.findViewById(R.id.circularProgressBar);
        tvDisease.setVisibility(View.INVISIBLE);
        tvDate.setVisibility(View.INVISIBLE);
        tvPercent.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
        animation();
        LinearLayout linearLayout = (LinearLayout) v.findViewById(R.id.linearLayout);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor cursor = getActivity().getContentResolver().query(ReportListProvider.CONTENT_URI, new String[]{
                        "_id", "disease1", "disease2", "disease3", "disease4", "disease5", "disease6", "disease7", "disease8", "disease9",
                        "disease10", "value1", "value2", "value3", "value4", "value5", "value6", "value7", "value8", "value9",
                        "value10", "rating", "note", "date", "year", "month"
                }, null, null, null);
                if(cursor.getCount()!=0)
                {
                    cursor.moveToLast();
                    Intent i = new Intent();
                    i.putExtra("date", cursor.getString(23));
                    i.putExtra("rating", cursor.getString(21));
                    i.putExtra("note", cursor.getString(22));
                    int count = 0;
                    for (int j = 0; j < 10; j++) {
                        if (cursor.getInt(j + 11) != 0)
                            count++;
                        else
                            break;
                    }
                    String[] diseases = new String[count];
                    int[] scores = new int[count];
                    for (int j = 0; j < count; j++) {
                        diseases[j] = cursor.getString(j + 1);
                        scores[j] = cursor.getInt(j + 11);
                    }
                    i.putExtra("diseases", diseases);
                    i.putExtra("scores", scores);
                    i.setClassName("devang.developer.com.diseasetracker", "devang.developer.com.diseasetracker.History");
                    startActivity(i);
                }

            }
        });
        imgdirection.setOnClickListener(this);
        img = (ImageButton) v.findViewById(R.id.img1);
        img.setVisibility(View.GONE);
        set_last_measurements();
        animFadein = AnimationUtils.loadAnimation(getActivity(), R.anim.move);
        animFadein.setAnimationListener(new android.view.animation.Animation.AnimationListener() {


            public void onAnimationEnd(Animation animation) {
                animation = AnimationUtils.loadAnimation(getActivity(), R.anim.move);
                animation.setAnimationListener(this);
                img.startAnimation(animation);
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
                (new Handler()).postDelayed(new Runnable() {


                    public void run() {
                        img2 = (ImageButton) v.findViewById(R.id.img2);
                        img2.setVisibility(View.GONE);
                        Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.move);
                        animation.setAnimationListener(new android.view.animation.Animation.AnimationListener() {
                            public void onAnimationEnd(Animation animation) {
                                animation = AnimationUtils.loadAnimation(getActivity(), R.anim.move);
                                animation.setAnimationListener(this);
                                img2.startAnimation(animation);
                            }

                            public void onAnimationRepeat(Animation animation) {
                            }

                            public void onAnimationStart(Animation animation) {
                                (new Handler()).postDelayed(new Runnable() {


                                    public void run() {
                                        img3 = (ImageButton) v.findViewById(R.id.img3);
                                        img3.setVisibility(View.GONE);
                                        Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.move);
                                        animation.setAnimationListener(new android.view.animation.Animation.AnimationListener() {

                                            public void onAnimationEnd(Animation animation) {
                                                animation = AnimationUtils.loadAnimation(getActivity(), R.anim.move);
                                                animation.setAnimationListener(this);
                                                img3.startAnimation(animation);
                                            }

                                            public void onAnimationRepeat(Animation animation) {
                                            }

                                            public void onAnimationStart(Animation animation) {
                                                (new Handler()).postDelayed(new Runnable() {

                                                    public void run() {
                                                        img4 = (ImageButton) v.findViewById(R.id.img4);
                                                        img4.setVisibility(View.GONE);
                                                        Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.move);
                                                        animation.setAnimationListener(new android.view.animation.Animation.AnimationListener() {

                                                            public void onAnimationEnd(Animation animation) {
                                                                animation = AnimationUtils.loadAnimation(getActivity(), R.anim.move);
                                                                animation.setAnimationListener(this);
                                                                img4.startAnimation(animation);
                                                            }

                                                            public void onAnimationRepeat(Animation animation) {
                                                            }

                                                            public void onAnimationStart(Animation animation) {
                                                                (new Handler()).postDelayed(new Runnable() {

                                                                    public void run() {
                                                                        img5 = (ImageButton) v.findViewById(R.id.img5);
                                                                        img5.setVisibility(View.GONE);
                                                                        Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.move);
                                                                        animation.setAnimationListener(new android.view.animation.Animation.AnimationListener() {

                                                                            public void onAnimationEnd(Animation animation) {
                                                                                animation = AnimationUtils.loadAnimation(getActivity(), R.anim.move);
                                                                                animation.setAnimationListener(this);
                                                                                img5.startAnimation(animation);
                                                                            }

                                                                            public void onAnimationRepeat(Animation animation) {
                                                                            }

                                                                            public void onAnimationStart(Animation animation) {
                                                                                (new Handler()).postDelayed(new Runnable() {

                                                                                    public void run() {
                                                                                        img6 = (ImageButton) v.findViewById(R.id.img6);
                                                                                        img6.setVisibility(View.GONE);
                                                                                        Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.move);
                                                                                        animation.setAnimationListener(new android.view.animation.Animation.AnimationListener() {

                                                                                            public void onAnimationEnd(Animation animation) {
                                                                                                animation = AnimationUtils.loadAnimation(getActivity(), R.anim.move);
                                                                                                animation.setAnimationListener(this);
                                                                                                img6.startAnimation(animation);
                                                                                            }

                                                                                            public void onAnimationRepeat(Animation animation) {
                                                                                            }

                                                                                            public void onAnimationStart(Animation animation) {
                                                                                                (new Handler()).postDelayed(new Runnable() {

                                                                                                    public void run() {
                                                                                                        img7 = (ImageButton) v.findViewById(R.id.img7);
                                                                                                        img7.setVisibility(View.GONE);
                                                                                                        Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.move);
                                                                                                        animation.setAnimationListener(new android.view.animation.Animation.AnimationListener() {
                                                                                                            public void onAnimationEnd(Animation animation) {
                                                                                                                animation = AnimationUtils.loadAnimation(getActivity(), R.anim.move);
                                                                                                                animation.setAnimationListener(this);
                                                                                                                img7.startAnimation(animation);
                                                                                                            }

                                                                                                            public void onAnimationRepeat(Animation animation) {
                                                                                                            }

                                                                                                            public void onAnimationStart(Animation animation) {
                                                                                                            }


                                                                                                        });
                                                                                                        img7.startAnimation(animation);
                                                                                                    }


                                                                                                }, Tab1.SPLASH_TIME_OUT);
                                                                                            }


                                                                                        });
                                                                                        img6.startAnimation(animation);
                                                                                    }


                                                                                }, Tab1.SPLASH_TIME_OUT);
                                                                            }


                                                                        });
                                                                        img5.startAnimation(animation);
                                                                    }


                                                                }, Tab1.SPLASH_TIME_OUT);
                                                            }


                                                        });
                                                        img4.startAnimation(animation);
                                                    }


                                                }, Tab1.SPLASH_TIME_OUT);
                                            }


                                        });
                                        img3.startAnimation(animation);
                                    }


                                }, Tab1.SPLASH_TIME_OUT);
                            }


                        });
                        img2.startAnimation(animation);
                    }

                }, Tab1.SPLASH_TIME_OUT);
            }
        });
        img.startAnimation(animFadein);
        return v;
    }

    private void animation() {
        imgdirection.setSecondaryProgress(99);
        for (int i = 99; i >= 1; i--) {
            imgdirection.setProgress(i);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    int x=1;
                }
            }, SPLASH_TIME_OUT2);

        }
        imgdirection.setSecondaryProgress(99);
    }


    private void set_last_measurements() {
        Cursor cursor = getActivity().getContentResolver().query(ReportListProvider.CONTENT_URI, new String[]{
                "disease1", "value1", "date"
        }, null, null, null);
        if(cursor.getCount()!=0)
        {
            cursor.moveToLast();
            tvDisease.setVisibility(View.VISIBLE);
            tvDate.setVisibility(View.VISIBLE);
            tvPercent.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.VISIBLE);
            tvDisease.setText(cursor.getString(0));
            tvDate.setText(cursor.getString(2));
            tvPercent.setText(String.valueOf(cursor.getInt(1)) + "%");
            progressBar.setProgress(cursor.getInt(1));
        }
    }


    @Override
    public void onClick(View v) {
        for (i = 1; i <= 100; i++) {
            imgdirection.setProgress(i);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    int x=1;
                }
            }, SPLASH_TIME_OUT2);
        }
        startActivity(new Intent(getActivity(), Symptoms.class));
    }
}
