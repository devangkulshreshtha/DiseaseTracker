package devang.developer.com.diseasetracker;


import android.annotation.TargetApi;
import android.database.Cursor;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import java.security.acl.Group;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hp1 on 21-01-2015.
 */
public class Tab3 extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{
    private ExpandListAdapter ExpAdapter;
    private ArrayList<devang.developer.com.diseasetracker.Group> ExpListItemsactual;
    private ExpandableListView ExpandList;
    CursorLoader cursorLoader;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_3, container, false);
        ExpandList = (ExpandableListView)v.findViewById(R.id.lvExp);
        final int version = android.os.Build.VERSION.SDK_INT;
        final int width;
        if (version >= 13)
        {
            Display display = getActivity().getWindowManager().getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            width = size.x;
        }
        else
        {
            Display display = getActivity().getWindowManager().getDefaultDisplay();
            width = display.getWidth();
        }
        if(version >= 18)
        {
            ExpandList.setIndicatorBoundsRelative(width-50, width);
        }
        getActivity().getSupportLoaderManager().initLoader(1, null, this);
        return v;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        cursorLoader = new CursorLoader(getActivity(), Uri.parse("content://com.developer.devang.ReportListProvider/cte"), null, null, null, null);
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> arg0, Cursor cursor) {
        if (cursor != null)
        {
            cursor.moveToFirst();
            ExpListItemsactual = SetStandardGroups_actual();
            ExpAdapter = new ExpandListAdapter(getActivity(), ExpListItemsactual);
            ExpandList.setAdapter(ExpAdapter);
        }
    }

    private ArrayList<devang.developer.com.diseasetracker.Group> SetStandardGroups_actual() {
        Cursor cursor = getActivity().getContentResolver().query(ReportListProvider.CONTENT_URI, new String[]{
                "_id", "disease1", "disease2", "disease3", "disease4", "disease5", "disease6", "disease7", "disease8", "disease9",
                "disease10", "value1", "value2", "value3", "value4", "value5", "value6", "value7", "value8", "value9",
                "value10", "rating", "note", "date", "year", "month"
        }, null, null, null);
        int records = cursor.getCount();
        String[] static_months = {"Januray", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        int[] months_frequency = {0,0,0,0,0,0,0,0,0,0,0,0};
        String[] diseases = new String[records];
        int[] scores = new int[records];
        int[] months = new int[records];
        String[] date = new String[records];
        int i=0;
        while (cursor.moveToNext())
        {
            diseases[i] = cursor.getString(1);
            months[i] = cursor.getInt(25);
            date[i] = cursor.getString(23);
            scores[i] = cursor.getInt(11);
            months_frequency[months[i]] ++ ;
            i ++ ;
        }
        ArrayList<devang.developer.com.diseasetracker.Group> result = new ArrayList<devang.developer.com.diseasetracker.Group>();
        ArrayList<Child> ch_list;
        for(i=0;i<12;i++)
        {
            if(months_frequency[i] != 0)
            {
                devang.developer.com.diseasetracker.Group group = new devang.developer.com.diseasetracker.Group();
                group.setName(static_months[i] + " 2015");
                group.setRecords(months_frequency[i]);
                ch_list = new ArrayList<Child>();
                for(int j=0;j<records;j++)
                {
                    if(months[j] == i)
                    {
                        Child child = new Child();
                        child.setName(diseases[j]);
                        child.setDate(date[j]);
                        child.setId(j + 1);
                        child.setPercent(String.valueOf(scores[j]));
                        child.setProgress(scores[j]);
                        ch_list.add(child);
                    }
                }
                group.setItems(ch_list);
                result.add(group);
            }
        }
        return result;
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}