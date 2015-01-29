package com.amartinez.activitytracker;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

import com.amartinez.activitytracker.model.SQLStorageHelper;


import java.util.ArrayList;


public class MainActivity extends ActionBarActivity implements ActivityListFragment.OnFragmentInteractionListener{

    public static ArrayList<String> userActivities = new ArrayList<>();
    private SimpleCursorAdapter activitiesArrayAdapter;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
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
//            clearAll();
            return true;
        }
        else if (id == R.id.show_entries) {
            Intent i = new Intent(context, ActivityEntries.class);
            context.startActivity(i);
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
                        activitiesArrayAdapter.changeCursor(SQLStorageHelper.getInstance(context).getActivitiesCursor());
                    }
                })
                .setNegativeButton(R.string.cancel_activity_dialog_title, null)
                .setView(alertDialogView).create().show();
    }

    @Override
    public void onFragmentInteraction(final String id) {

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
