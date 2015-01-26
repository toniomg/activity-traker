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

    public static ArrayList<String> demoActivities = new ArrayList<>();
    private ArrayAdapter<String> activitiesArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Fill with test values
        demoActivities.add("Yoga");
        demoActivities.add("Guitar");
        demoActivities.add("Meditation");
        demoActivities.add("Gym");

        //Fill the main list view with the given values
        ListView activitiesListView = (ListView) findViewById(R.id.activitiesListView);
        activitiesArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, demoActivities);
        activitiesListView.setAdapter(activitiesArrayAdapter);

        //Test the DB
        StorageHelper.getInstance(this).storeNewElement("Test");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.add_activity) {
            addNewActivity();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    //Menu selection
    private void addNewActivity() {
            demoActivities.add("Another");
            activitiesArrayAdapter.notifyDataSetChanged();
    }
}
