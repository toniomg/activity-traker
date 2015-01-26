package com.amartinez.activitytracker;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.amartinez.activitytracker.model.StorageHelper;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    public static ArrayList<String> userActivities = new ArrayList<>();
    private ArrayAdapter<String> activitiesArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Fill the main list view with the stored values
        //TODO: Move to background thread
        userActivities = StorageHelper.getInstance(this).getActivityList();

        ListView activitiesListView = (ListView) findViewById(R.id.activitiesListView);
        activitiesArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, userActivities);
        activitiesListView.setAdapter(activitiesArrayAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.add_activity) {
            addNewActivity();
            return true;
        }
        else if (id == R.id.remove_all) {
            clearAll();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * Add a new activity to the main list
     */
    private void addNewActivity() {
        StorageHelper.getInstance(this).storeNewActivity("TEST");
        reloadActivities();

    }


    /**
     * Reload the list of activities in the main list
     */
    private void reloadActivities() {
        activitiesArrayAdapter.clear();
        userActivities = StorageHelper.getInstance(this).getActivityList();
        activitiesArrayAdapter.addAll(userActivities);
        activitiesArrayAdapter.notifyDataSetChanged();
    }

    /**
     * Remove all user activities
     */
    private void clearAll() {
        activitiesArrayAdapter.clear();
        StorageHelper.getInstance(this).removeAllElements();
        activitiesArrayAdapter.notifyDataSetChanged();
    }
}
