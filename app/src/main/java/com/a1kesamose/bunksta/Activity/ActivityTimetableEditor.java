package com.a1kesamose.bunksta.Activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.a1kesamose.bunksta.Adapter.CourseSpinnerBaseAdapter;
import com.a1kesamose.bunksta.Adapter.DayOfWeekSpinnerBaseAdapter;
import com.a1kesamose.bunksta.Database.CourseDatabaseSource;
import com.a1kesamose.bunksta.Database.TimetableDatabaseSource;
import com.a1kesamose.bunksta.Object.Timetable;
import com.a1kesamose.bunksta.R;

public class ActivityTimetableEditor extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener
{
    private boolean FLAG_COURSE_SET = false;
    private boolean FLAG_DAY_OF_WEEK_SET = false;
    private boolean FLAG_START_TIME_SET = false;
    private boolean FLAG_END_TIME_SET = false;
    private boolean FLAG_LOCATION_SET = false;
    private CourseDatabaseSource courseDatabaseSource;
    private TimetableDatabaseSource timetableDatabaseSource;
    private Timetable timetable;
    private LayoutInflater inflater;

    private Spinner spinnerCourses;
    private Spinner spinnerDayOfWeek;
    private EditText editTextStartTime;
    private EditText editTextEndTime;
    private EditText editTextLocation;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable_editor);

        spinnerCourses = (Spinner)findViewById(R.id.activity_timetable_editor_spinner_courses);
        spinnerCourses.setOnItemSelectedListener(this);
        spinnerDayOfWeek = (Spinner)findViewById(R.id.activity_timetable_editor_spinner_day_of_week);
        spinnerDayOfWeek.setOnItemSelectedListener(this);
        editTextStartTime = (EditText)findViewById(R.id.activity_timetable_editor_editText_startTime);
        editTextStartTime.setOnClickListener(this);
        editTextEndTime = (EditText)findViewById(R.id.activity_timetable_editor_editText_endTime);
        editTextEndTime.setOnClickListener(this);
        editTextLocation = (EditText)findViewById(R.id.activity_timetable_editor_editText_location);
        editTextLocation.setOnClickListener(this);

        courseDatabaseSource = new CourseDatabaseSource(this);
        timetableDatabaseSource = new TimetableDatabaseSource(this);
        inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    protected void onResume()
    {
        super.onResume();

        courseDatabaseSource.openDatabase();
        timetableDatabaseSource.openDatabase();
        spinnerCourses.setAdapter(new CourseSpinnerBaseAdapter(this, courseDatabaseSource.getCourseList()));
        spinnerDayOfWeek.setAdapter(new DayOfWeekSpinnerBaseAdapter(this));

        if(getIntent().getExtras().getBoolean("new_timetable"))
        {
            timetable = new Timetable();
            spinnerCourses.setSelection(0);
            spinnerDayOfWeek.setSelection(getIntent().getExtras().getInt("day_of_week"));
        }
        else
        {
            timetable = timetableDatabaseSource.getTimetable(getIntent().getExtras().getLong("timetable_id"));
            spinnerCourses.setSelection(courseDatabaseSource.getCoursePosition(timetable.TIMETABLE_COURSE_ID));
            spinnerDayOfWeek.setSelection(timetable.TIMETABLE_DAY_OF_WEEK);
            editTextStartTime.setText(getTimeStamp(timetable.TIMETABLE_START_HOUR, timetable.TIMETABLE_START_MINUTE));
            editTextEndTime.setText(getTimeStamp(timetable.TIMETABLE_END_HOUR, timetable.TIMETABLE_END_MINUTE));
            FLAG_COURSE_SET = true;
            FLAG_DAY_OF_WEEK_SET = true;
            FLAG_START_TIME_SET = true;
            FLAG_END_TIME_SET = true;
        }
    }

    protected void onPause()
    {
        super.onPause();

        courseDatabaseSource.closeDatabase();
        timetableDatabaseSource.closeDatabase();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_activity_timetable_editor, menu);

        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.activity_timetable_editor_action_save:
            {
                saveTimetable();

                return true;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id)
    {
        switch(adapterView.getId())
        {
            case R.id.activity_timetable_editor_spinner_courses:
            {
                timetable.TIMETABLE_COURSE_ID = courseDatabaseSource.getCourseList().get(position).COURSE_ID;
                timetable.TIMETABLE_COURSE_NAME = courseDatabaseSource.getCourseList().get(position).COURSE_NAME;
                timetable.TIMETABLE_COURSE_COLOR = courseDatabaseSource.getCourseList().get(position).COURSE_COLOR;
                FLAG_COURSE_SET = true;

                break;
            }
            case R.id.activity_timetable_editor_spinner_day_of_week:
            {
                timetable.TIMETABLE_DAY_OF_WEEK = position;
                FLAG_DAY_OF_WEEK_SET = true;

                break;
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView)
    {

    }

    @Override
    public void onClick(View view)
    {
        switch(view.getId())
        {
            case R.id.activity_timetable_editor_editText_startTime:
            {
                alertDialogTimePicker(true);

                break;
            }
            case R.id.activity_timetable_editor_editText_endTime:
            {
                alertDialogTimePicker(false);

                break;
            }
            case R.id.activity_timetable_editor_editText_location:
            {
                break;
            }
        }
    }

    private void saveTimetable()
    {
        int flag = timetableDatabaseSource.isValidTimetable(timetable.TIMETABLE_START_HOUR, timetable.TIMETABLE_START_MINUTE, timetable.TIMETABLE_END_HOUR, timetable.TIMETABLE_END_MINUTE, timetable.TIMETABLE_DAY_OF_WEEK);

        if(!FLAG_COURSE_SET)
        {
            Toast.makeText(this, "Select course!", Toast.LENGTH_SHORT).show();
        }
        else if(!FLAG_DAY_OF_WEEK_SET)
        {
            Toast.makeText(this, "Select the day!", Toast.LENGTH_SHORT).show();
        }
        else if(!FLAG_START_TIME_SET)
        {
            Toast.makeText(this, "Set start time!", Toast.LENGTH_SHORT).show();
        }
        else if(!FLAG_END_TIME_SET)
        {
            Toast.makeText(this, "Set end time!", Toast.LENGTH_SHORT).show();
        }
        else if(flag == 1)
        {
            Toast.makeText(this, "Start and end times cannot be the same!", Toast.LENGTH_SHORT).show();
        }
        else if(flag == 2)
        {
            Toast.makeText(this, "This slot overlaps with an existing slot!", Toast.LENGTH_SHORT).show();
        }
        else if(getIntent().getExtras().getBoolean("new_timetable"))
        {
            timetableDatabaseSource.createTimetable(timetable);

            Toast.makeText(this, "Timetable slot saved.", Toast.LENGTH_SHORT).show();
        }
        else
        {
            timetableDatabaseSource.updateTimetable(timetable);

            Toast.makeText(this, "Timetable slot updated.", Toast.LENGTH_SHORT).show();
        }
    }

    private String getTimeStamp(int hour, int minute)
    {
        String timestamp = "";

        if(DateFormat.is24HourFormat(this))
        {
            timestamp = hour<10? timestamp + "0" + hour + ":": timestamp + hour + ":";
            timestamp = minute<10 ? timestamp + "0" + minute: timestamp + minute;
        }
        else
        {
            if(hour>12)
            {
                timestamp = (hour-12)<10? timestamp + "0" + (hour-12) + ":": timestamp + (hour-12) + ":";
                timestamp = minute<10 ? timestamp + "0" + minute + " PM": timestamp + minute + " PM";
            }
            else
            {
                timestamp = hour<10? timestamp + "0" + hour + ":": timestamp + hour + ":";
                timestamp = minute<10 ? timestamp + "0" + minute + " AM": timestamp + minute + " AM";
            }
        }

        return timestamp;
    }

    private void alertDialogTimePicker(final boolean startTimestamp)
    {
        View view = inflater.inflate(R.layout.alert_dialog_time_picker, null, false);
        final TimePicker timePicker = (TimePicker)view.findViewById(R.id.alert_dialog_time_timePicker);
        timePicker.setIs24HourView(DateFormat.is24HourFormat(this));
        if(startTimestamp)
        {
            timePicker.setCurrentHour(timetable.TIMETABLE_START_HOUR);
            timePicker.setCurrentMinute(timetable.TIMETABLE_START_MINUTE);
        }
        else
        {
            timePicker.setCurrentHour(timetable.TIMETABLE_END_HOUR);
            timePicker.setCurrentMinute(timetable.TIMETABLE_END_MINUTE);
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                if(startTimestamp)
                {
                    timetable.TIMETABLE_START_HOUR = timePicker.getCurrentHour();
                    timetable.TIMETABLE_START_MINUTE = timePicker.getCurrentMinute();
                    editTextStartTime.setText(getTimeStamp(timePicker.getCurrentHour(), timePicker.getCurrentMinute()));
                    FLAG_START_TIME_SET = true;
                }
                else
                {
                    timetable.TIMETABLE_END_HOUR = timePicker.getCurrentHour();
                    timetable.TIMETABLE_END_MINUTE = timePicker.getCurrentMinute();
                    editTextEndTime.setText(getTimeStamp(timePicker.getCurrentHour(), timePicker.getCurrentMinute()));
                    FLAG_END_TIME_SET = true;
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                dialogInterface.dismiss();
            }
        });
        builder.create().show();
    }
}
