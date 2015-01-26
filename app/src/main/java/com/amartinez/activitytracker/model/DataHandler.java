package com.amartinez.activitytracker.model;

import java.util.ArrayList;

/**
 * Created by amartinez on 23/01/15.
 */
public interface DataHandler<T> {
    public void storeNewActivity(T pT);
    public ArrayList<T> getActivityList();
    public void removeAllElements();
}
