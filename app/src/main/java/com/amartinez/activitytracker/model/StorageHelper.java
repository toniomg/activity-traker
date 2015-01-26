package com.amartinez.activitytracker.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by amartinez on 23/01/15.
 * Manage the storage of elements in the app, so the activities are unaware of the storage method.
 * Using an interface for store and retrieve elements allow the app to change the model without the other activities having to be aware of this.
 */
public class StorageHelper extends SQLiteOpenHelper implements DataHandler<String>{

    public static StorageHelper storageHelper;
    public static String DATABASE_NAME = "activity_db";
    public static int DATABASE_VERSION = 1;
    public static String ACTIVITY_TABLE_NAME = "activity_table";
    public static String ACTIVITY_TITLE = "title";

    public StorageHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Get the singleton instance of this class.
     * No need to double-check lock or synchronize as no different threads will access it.
     * @param context
     * @return
     */
    public static StorageHelper getInstance(Context context) {
        if (storageHelper == null) {
            storageHelper = new StorageHelper(context.getApplicationContext());
        }

        return storageHelper;
    }

    /**
     * Public methods, entry point to this class
     */


    @Override
    public void storeNewElement(final String s) {
        ContentValues cv = new ContentValues();
        cv.put(ACTIVITY_TITLE, s);

        getWritableDatabase().insert(ACTIVITY_TABLE_NAME, null , cv);
    }

    @Override
    public ArrayList<String> getElementList() {
        return null;
    }


    /**
     * SQL Methods
     */

    @Override
    public void onCreate(final SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + ACTIVITY_TABLE_NAME + " (" + ACTIVITY_TITLE + " TEXT);");
    }

    @Override
    public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {
        throw new RuntimeException("Not implemented yet");
    }
}
