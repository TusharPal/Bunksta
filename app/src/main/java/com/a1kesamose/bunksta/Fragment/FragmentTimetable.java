package com.a1kesamose.bunksta.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.a1kesamose.bunksta.Activity.ActivityMain;
import com.a1kesamose.bunksta.Activity.ActivityTimetableEditor;
import com.a1kesamose.bunksta.Adapter.TimetableViewPagerAdapter;
import com.a1kesamose.bunksta.R;

import java.util.Calendar;

public class FragmentTimetable extends Fragment
{
    private static final String ARG_SECTION_NUMBER = "section_number";
    private TimetableViewPagerAdapter pagerAdapter;

    private ViewPager viewPager;

    public static FragmentTimetable newInstance(int sectionNumber)
    {
        FragmentTimetable fragment = new FragmentTimetable();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);

        return fragment;
    }

    public FragmentTimetable()
    {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        setHasOptionsMenu(true);

        View rootView = inflater.inflate(R.layout.fragment_timetable, container, false);
        viewPager = (ViewPager)rootView.findViewById(R.id.fragment_timetable_viewPager);
        pagerAdapter = new TimetableViewPagerAdapter(getChildFragmentManager());

        return rootView;
    }

    @Override
    public void onResume()
    {
        super.onResume();

        viewPager.setAdapter(pagerAdapter);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        viewPager.setCurrentItem(calendar.get(Calendar.DAY_OF_WEEK), true);
    }

    @Override
    public void onPause()
    {
        super.onPause();
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater)
    {
        menuInflater.inflate(R.menu.menu_fragment_timetable, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.fragment_timetable_action_new:
            {
                Intent intent = new Intent(getActivity().getApplicationContext(), ActivityTimetableEditor.class);
                intent.putExtra("new_timetable", true);
                intent.putExtra("day_of_week", viewPager.getCurrentItem());
                startActivity(intent);

                return true;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        ((ActivityMain) activity).onSectionAttached(getArguments().getInt(ARG_SECTION_NUMBER));
    }
}
