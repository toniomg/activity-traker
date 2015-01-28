package com.amartinez.activitytracker;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.Date;

import com.amartinez.activitytracker.model.SQLStorageHelper;


import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    public static ArrayList<String> userActivities = new ArrayList<>();
    private SimpleCursorAdapter activitiesArrayAdapter;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        //Fill the main list view with the stored values
        //TODO: Move to background thread
        userActivities = SQLStorageHelper.getInstance(this).getActivityList();

        ListView activitiesListView = (ListView) findViewById(R.id.activitiesListView);
        //activitiesArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, userActivities);
        activitiesArrayAdapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_1,
                SQLStorageHelper.getInstance(this).getActivitiesCursor(),
                new String[]{SQLStorageHelper.ACTIVITY_TITLE_COLUMN},
                new int[]{android.R.id.text1},
                0);
        activitiesListView.setAdapter(activitiesArrayAdapter);
        activitiesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> parent, final View view, final int position, final long id) {
//                TextView tv = (TextView) view.findViewById(android.R.id.text1);
//
//                Calendar calendar = Calendar.getInstance();
//                new DatePickerDialog(context, null, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
//
//                //Add new entry
//                StorageHelper.getInstance(context).addEntryForDate("TEST", new Date());

                Intent i = new Intent(context, ActivityEntries.class);
                context.startActivity(i);

            }
        });

    }


//    public void addNewEntry(String title, Date date) {
//
//    }


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
//            clearAll();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * Add a new activity to the main list trough a dialog
     */
    private void addNewActivity() {

        final View alertDialogView = this.getLayoutInflater().inflate(R.layout.new_activity_dialog, null);

        new AlertDialog.Builder(this)
                .setMessage(R.string.new_activity_dialog_title)
                .setPositiveButton(R.string.add_activity_dialog_title, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialog, final int which) {
                        EditText activityNameText = (EditText)alertDialogView.findViewById(R.id.activityName);
                        SQLStorageHelper.getInstance(context).storeNewActivity(activityNameText.getText().toString());
                        reloadActivities();
                    }
                })
                .setNegativeButton(R.string.cancel_activity_dialog_title, null)
                .setView(alertDialogView).create().show();
    }


//    /**
//     * Reload the list of activities in the main list
//     */
    private void reloadActivities() {
        activitiesArrayAdapter.changeCursor(SQLStorageHelper.getInstance(this).getActivitiesCursor());
    }

//    /**
//     * Remove all user activities
//     */
//    private void clearAll() {
//       // activitiesArrayAdapter.clear();
//        StorageHelper.getInstance(this).removeAllElements();
//        activitiesArrayAdapter.notifyDataSetChanged();
//    }
}
