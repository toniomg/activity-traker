package com.amartinez.activitytracker;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.amartinez.activitytracker.model.SQLStorageHelper;


public class ActivityEntries extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_entries);

        ListView lv = (ListView) findViewById(R.id.entriesListView);
        lv.setAdapter(new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_2,
                SQLStorageHelper.getInstance(this).getEntriesCursor(),
                new String[]{SQLStorageHelper.ACTIVITY_TITLE_COLUMN, SQLStorageHelper.ACTIVITY_DATE_COLUMN},
                new int[]{android.R.id.text1, android.R.id.text2},
                0));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_acticity_entries, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
