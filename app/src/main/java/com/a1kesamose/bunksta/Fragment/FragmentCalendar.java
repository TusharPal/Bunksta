package com.a1kesamose.bunksta.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.a1kesamose.bunksta.Activity.ActivityMain;
import com.a1kesamose.bunksta.Adapter.CalendarViewPagerAdapter;
import com.a1kesamose.bunksta.Database.CalendarDatabaseSource;
import com.a1kesamose.bunksta.R;

import java.util.Calendar;

public class FragmentCalendar extends Fragment
{
    private static final String ARG_SECTION_NUMBER = "section_number";
    private CalendarViewPagerAdapter calendarViewPagerAdapter;
    private CalendarDatabaseSource calendarDatabaseSource;
    private SharedPreferences sharedPreferences;
    private LayoutInflater inflater;
    private Calendar calendarStartDate;
    private Calendar calendarEndDate;
    private ViewPager viewPager;

    public static FragmentCalendar newInstance(int sectionNumber)
    {
        FragmentCalendar fragment = new FragmentCalendar();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);

        return fragment;
    }

    public FragmentCalendar()
    {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        setHasOptionsMenu(true);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        calendarDatabaseSource = new CalendarDatabaseSource(getActivity());

        View rootView = inflater.inflate(R.layout.fragment_calendar, container, false);
        viewPager = (ViewPager)rootView.findViewById(R.id.fragment_calendar_viewPager);
        calendarViewPagerAdapter = new CalendarViewPagerAdapter(getChildFragmentManager(), 0, 11);

        return rootView;
    }

    @Override
    public void onResume()
    {
        super.onResume();

        viewPager.setAdapter(calendarViewPagerAdapter);
    }

    @Override
    public void onPause()
    {
        super.onPause();
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater)
    {
        menuInflater.inflate(R.menu.menu_fragment_calendar, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        ((ActivityMain) activity).onSectionAttached(getArguments().getInt(ARG_SECTION_NUMBER));
    }

    private void alertDialogDatePicker(boolean start)
    {
        inflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.alert_dialog_date_picker, null, false);
        DatePicker datePicker = (DatePicker)view.findViewById(R.id.datePicker);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setTitle(start ? "Select start date" : "Select end date")
                .setView(view)
                .setPositiveButton(start ? "Next" : "Ok", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {

                    }
                });
        builder.create().show();
    }
}
