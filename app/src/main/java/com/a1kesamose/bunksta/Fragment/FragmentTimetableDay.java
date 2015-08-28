package com.a1kesamose.bunksta.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.a1kesamose.bunksta.Activity.ActivityTimetableEditor;
import com.a1kesamose.bunksta.Adapter.TimetableBaseAdapter;
import com.a1kesamose.bunksta.Database.TimetableDatabaseSource;
import com.a1kesamose.bunksta.R;

public class FragmentTimetableDay extends Fragment implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener
{
    private static final String ARG_DAY_OF_WEEK = "day_of_week";
    private TimetableDatabaseSource databaseSource;

    private ListView listView;

    public static FragmentTimetableDay newInstance(int dayOfWeek)
    {
        FragmentTimetableDay fragment = new FragmentTimetableDay();
        Bundle args = new Bundle();
        args.putInt(ARG_DAY_OF_WEEK, dayOfWeek);
        fragment.setArguments(args);

        return fragment;
    }

    public FragmentTimetableDay()
    {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        databaseSource = new TimetableDatabaseSource(getActivity().getApplicationContext());

        View rootView = inflater.inflate(R.layout.fragment_timetable_day, container, false);
        listView = (ListView)rootView.findViewById(R.id.fragment_timetable_day_listView);

        return rootView;
    }

    @Override
    public void onResume()
    {
        super.onResume();

        databaseSource.openDatabase();
        listView.setAdapter(new TimetableBaseAdapter(getActivity().getApplicationContext(), databaseSource.getTimetableList(getArguments().getInt(ARG_DAY_OF_WEEK))));
        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);
    }

    @Override
    public void onPause()
    {
        super.onPause();

        databaseSource.closeDatabase();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id)
    {
        Intent intent = new Intent(getActivity().getApplicationContext(), ActivityTimetableEditor.class);
        intent.putExtra("new_timetable", false);
        intent.putExtra("timetable_id", databaseSource.getTimetableList(getArguments().getInt(ARG_DAY_OF_WEEK)).get(position).TIMETABLE_ID);
        startActivity(intent);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id)
    {
        final long timetableId = databaseSource.getTimetableList(getArguments().getInt(ARG_DAY_OF_WEEK)).get(position).TIMETABLE_ID;
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Delete timetable slot?");
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                databaseSource.deleteTimetable(timetableId);
                listView.setAdapter(new TimetableBaseAdapter(getActivity().getApplicationContext(), databaseSource.getTimetableList(getArguments().getInt(ARG_DAY_OF_WEEK))));
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {

            }
        });
        builder.create().show();

        return true;
    }
}
