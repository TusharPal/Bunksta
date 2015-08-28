package com.a1kesamose.bunksta.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.a1kesamose.bunksta.Fragment.FragmentCalendarMonth;
import com.a1kesamose.bunksta.Object.Day;
import com.a1kesamose.bunksta.Object.Month;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CalendarViewPagerAdapter extends FragmentPagerAdapter
{
    private static final String monthNames[] = {"January", "February", "March",
                                                "April", "May", "June",
                                                "July", "August", "September",
                                                "October", "November", "December"};
    private int monthStartIndex;
    private int monthEndIndex;

    public CalendarViewPagerAdapter(FragmentManager fragmentManager, int monthStartIndex, int monthEndIndex)
    {
        super(fragmentManager);
        this.monthStartIndex = monthStartIndex;
        this.monthEndIndex = monthEndIndex;
    }

    @Override
    public Fragment getItem(int position)
    {
        FragmentCalendarMonth fragment = FragmentCalendarMonth.newInstance(monthStartIndex + position);

        return fragment;
    }

    @Override
    public int getCount()
    {
        return (monthEndIndex - monthStartIndex);
    }

    public CharSequence getPageTitle(int position)
    {
        return monthNames[monthStartIndex + position];
    }
}
