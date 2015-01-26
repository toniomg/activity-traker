package com.amartinez.activitytracker.model;

import java.util.ArrayList;

/**
 * Created by amartinez on 23/01/15.
 */
public interface DataHandler<T> {
    public void storeNewElement(T pT);
    public ArrayList<T> getElementList();
}
