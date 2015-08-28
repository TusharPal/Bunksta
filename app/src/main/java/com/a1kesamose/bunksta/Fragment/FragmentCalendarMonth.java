package com.a1kesamose.bunksta.Fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.a1kesamose.bunksta.Database.CalendarDatabaseSource;
import com.a1kesamose.bunksta.Object.Day;
import com.a1kesamose.bunksta.R;

import java.util.List;

public class FragmentCalendarMonth extends Fragment
{
    private static final String ARG_MONTH = "month";
    private CalendarDatabaseSource calendarDatabaseSource;
    private List<Day> listDay;

    private ListView listView;

    public static FragmentCalendarMonth newInstance(int monthOfYear)
    {
        FragmentCalendarMonth fragment = new FragmentCalendarMonth();
        Bundle args = new Bundle();
        args.putInt(ARG_MONTH, monthOfYear);
        fragment.setArguments(args);

        return fragment;
    }

    public FragmentCalendarMonth()
    {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        calendarDatabaseSource = new CalendarDatabaseSource(getActivity());
        calendarDatabaseSource.openDatabase();
        listDay = calendarDatabaseSource.getDaysOfMonth(getArguments().getInt(ARG_MONTH));

        View rootView = inflater.inflate(R.layout.fragment_calendar_month, container, false);
        listView = (ListView)rootView.findViewById(R.id.fragment_calendar_month_listView);

        return rootView;
    }

    @Override
    public void onResume()
    {
        super.onResume();
    }

    @Override
    public void onPause()
    {
        super.onPause();
    }

    private void alertDialogSelectTimetable()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Select day");
        builder.setItems(new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "No classes"}, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                Toast.makeText(getActivity(), Integer.toString(i), Toast.LENGTH_LONG).show();
            }
        });
        builder.create().show();
    }
}
