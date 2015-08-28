package com.a1kesamose.bunksta.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.a1kesamose.bunksta.Activity.ActivityMain;
import com.a1kesamose.bunksta.R;

public class FragmentAttendance extends Fragment
{
    private static final String ARG_SECTION_NUMBER = "section_number";

    private ListView listView;

    public static FragmentAttendance newInstance(int sectionNumber)
    {
        FragmentAttendance fragment = new FragmentAttendance();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);

        return fragment;
    }

    public FragmentAttendance()
    {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_calendar, container, false);
//        listView = (ListView)rootView.findViewById(R.id.fragment_attendance_listView);

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

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        ((ActivityMain) activity).onSectionAttached(getArguments().getInt(ARG_SECTION_NUMBER));
    }
}
