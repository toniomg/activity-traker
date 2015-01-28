package com.amartinez.activitytracker.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by amartinez on 23/01/15.
 * Manage the storage of elements in the app, so the activities are unaware of the storage method.
 * Using an interface for store and retrieve elements allow the app to change the model without the other activities having to be aware of this.
 * In the future, this storage may be done on the cloud.
 */
public class SQLStorageHelper extends SQLiteOpenHelper{

    public static SQLStorageHelper storageHelper;
    public static String DATABASE_NAME = "activity_db";
    public static int DATABASE_VERSION = 1;
    public static String ACTIVITY_TABLE_NAME = "activity_table";
    public static String ENTRIES_TABLE_NAME = "entries_table";
    public static String ACTIVITY_TITLE_COLUMN = "title";
    public static String ACTIVITY_DATE_COLUMN = "date";

    public SQLStorageHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Get the singleton instance of this class.
     * No need to double-check lock or synchronize as no different threads will access it.
     * @param context
     * @return
     */
    public static SQLStorageHelper getInstance(Context context) {
        if (storageHelper == null) {
            storageHelper = new SQLStorageHelper(context.getApplicationContext());
        }

        return storageHelper;
    }

    /**
     * Get the cursor linked to the entries of each activity
     * @return
     */
    public Cursor getEntriesCursor () {
        Cursor c = null;
        try {
            String query = "SELECT rowid as _id, " + ACTIVITY_TITLE_COLUMN + " , " + ACTIVITY_DATE_COLUMN + " from " + ENTRIES_TABLE_NAME;
            c = getWritableDatabase().rawQuery(query, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }

    /**
     * Get the cursor of the activities
     * @return
     */
    public Cursor getActivitiesCursor () {
        Cursor c = null;
        try {
            String query = "SELECT rowid as _id, " + ACTIVITY_TITLE_COLUMN  + " from " + ACTIVITY_TABLE_NAME;
            c = getWritableDatabase().rawQuery(query, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }

    /**
     * Public methods, entry point to this class
     */

    public void addEntryForDate(final String s, final Date date) {
        ContentValues cv = new ContentValues();
        cv.put(ACTIVITY_TITLE_COLUMN, s);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        cv.put(ACTIVITY_DATE_COLUMN, dateFormat.format(date));
        getWritableDatabase().insert(ENTRIES_TABLE_NAME, null,cv);
    }

    public void storeNewActivity(final String s) {
        ContentValues cv = new ContentValues();
        cv.put(ACTIVITY_TITLE_COLUMN, s);

        getWritableDatabase().insert(ACTIVITY_TABLE_NAME, null , cv);
    }

    public ArrayList<String> getActivityList() {

        ArrayList<String> activitiesList = new ArrayList<>();

        String[] columns = {ACTIVITY_TITLE_COLUMN};
        String selection = null; //null for all entries

        Cursor c = getWritableDatabase().query(ACTIVITY_TABLE_NAME, columns, selection, null, null, null, null, null);

        if (c.getCount() != 0) {
            int titleIndex = c.getColumnIndex(ACTIVITY_TITLE_COLUMN);
            while(c.moveToNext()) {
                activitiesList.add(c.getString(titleIndex));
            }

            c.close();
        }
        return activitiesList;
    }


    public void removeAllElements() {
        getWritableDatabase().delete(ACTIVITY_TABLE_NAME, null, null);
    }

    /**
     * SQL Methods
     */

    public void onCreate(final SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + ACTIVITY_TABLE_NAME + " (" + ACTIVITY_TITLE_COLUMN + " TEXT);");
        db.execSQL("CREATE TABLE " + ENTRIES_TABLE_NAME + " (" + ACTIVITY_TITLE_COLUMN + " TEXT, " + ACTIVITY_DATE_COLUMN + " DATE);");
    }

    public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {
        throw new RuntimeException("Not implemented yet");
    }
}
