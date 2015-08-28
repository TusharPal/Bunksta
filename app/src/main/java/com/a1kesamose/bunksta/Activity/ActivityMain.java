package com.a1kesamose.bunksta.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

import com.a1kesamose.bunksta.Fragment.FragmentAttendance;
import com.a1kesamose.bunksta.Fragment.FragmentCalendar;
import com.a1kesamose.bunksta.Fragment.FragmentCourses;
import com.a1kesamose.bunksta.Fragment.FragmentNavigationDrawer;
import com.a1kesamose.bunksta.Fragment.FragmentTimetable;
import com.a1kesamose.bunksta.R;

public class ActivityMain extends AppCompatActivity implements FragmentNavigationDrawer.NavigationDrawerCallbacks
{
    private FragmentNavigationDrawer mFragmentNavigationDrawer;

    private CharSequence mTitle;
    private static final String navigationDrawerTitles[] = {"Attendance stats","Calendar", "Timetable", "Courses", "Settings", "About Bunksta"};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFragmentNavigationDrawer = (FragmentNavigationDrawer)getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();
        mFragmentNavigationDrawer.setUp(R.id.navigation_drawer, (DrawerLayout)findViewById(R.id.drawer_layout));
    }

    @Override
    protected void onResume()
    {
        super.onResume();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
    }

    @Override
    public void onNavigationDrawerItemSelected(int position)
    {
        FragmentManager fragmentManager = getSupportFragmentManager();

        switch(position)
        {
            case 0:
            {
                fragmentManager.beginTransaction().replace(R.id.container, FragmentAttendance.newInstance(position)).commit();

                break;
            }
            case 1:
            {
                fragmentManager.beginTransaction().replace(R.id.container, FragmentCalendar.newInstance(position)).commit();

                break;
            }
            case 2:
            {
                fragmentManager.beginTransaction().replace(R.id.container, FragmentTimetable.newInstance(position)).commit();

                break;
            }
            case 3:
            {
                fragmentManager.beginTransaction().replace(R.id.container, FragmentCourses.newInstance(position)).commit();

                break;
            }
            case 4:
            {
                startActivity(new Intent(this, ActivityMap.class));
            }
        }
    }

    public void onSectionAttached(int position)
    {
        mTitle = navigationDrawerTitles[position];
    }

    public void restoreActionBar()
    {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        if (!mFragmentNavigationDrawer.isDrawerOpen())
        {
            getMenuInflater().inflate(R.menu.menu_activity_main, menu);
            restoreActionBar();

            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }
/*
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
*/
}
