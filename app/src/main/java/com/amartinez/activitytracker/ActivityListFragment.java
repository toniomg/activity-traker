package com.amartinez.activitytracker;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.app.ListFragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;


import com.amartinez.activitytracker.model.SQLStorageHelper;

import java.util.Calendar;

/**
 * A fragment representing a list of Items.
 * <p/>
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnFragmentInteractionListener}
 * interface.
 */
public class ActivityListFragment extends ListFragment {

    private OnFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ActivityListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        SimpleCursorAdapter sca = new SimpleCursorAdapter(getActivity(),
                android.R.layout.simple_list_item_1,
                SQLStorageHelper.getInstance(getActivity()).getActivitiesCursor(),
                new String[]{SQLStorageHelper.ACTIVITY_TITLE_COLUMN},
                new int[]{android.R.id.text1},
                0);

//            @Override
//            public void onItemClick(final AdapterView<?> parent, final View view, final int position, final long id) {
//                final TextView activityTitleTextView = (TextView) view.findViewById(android.R.id.text1);
//
//                Calendar calendar = Calendar.getInstance();
//                new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(final DatePicker view, final int year, final int monthOfYear, final int dayOfMonth) {
//                        //Add new entry
//                        //Fixes a bug with date listener called twice
//                        if (view.isShown()){
//                            if (activityTitleTextView != null) {
//                                SQLStorageHelper.getInstance(context).addEntryForDate(activityTitleTextView.getText().toString(), new Date());
//                            }
//                        }
//                    }
//                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
//            }
//        });

        setListAdapter(sca);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        if (null != mListener) {
            // Notify the active callbacks interface (the activity, if the
            // fragment is attached to one) that an item has been selected.
            //mListener.onFragmentInteraction(DummyContent.ITEMS.get(position).id);
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(String id);
    }

}
