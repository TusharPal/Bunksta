package com.a1kesamose.bunksta.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.a1kesamose.bunksta.Fragment.FragmentTimetableDay;

public class TimetableViewPagerAdapter extends FragmentPagerAdapter
{
    private final String dayNames[] = {"Sunday",
                                        "Monday",
                                        "Tuesday",
                                        "Wednesday",
                                        "Thursday",
                                        "Friday",
                                        "Saturday"};

    public TimetableViewPagerAdapter(FragmentManager fragmentManager)
    {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position)
    {
        FragmentTimetableDay fragment = FragmentTimetableDay.newInstance(position);

        return fragment;
    }

    @Override
    public int getCount()
    {
        return 7;
    }

    public CharSequence getPageTitle(int position)
    {
        return dayNames[position];
    }
}
