package com.a1kesamose.bunksta.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.a1kesamose.bunksta.Object.Day;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CalendarDatabaseSource
{
    private CalendarDatabaseHelper helper;
    private SQLiteDatabase database;
    private final String allColumns[] = {CalendarDatabaseHelper.COLUMN_ID,
                                            CalendarDatabaseHelper.COLUMN_TIMESTAMP,
                                            CalendarDatabaseHelper.COLUMN_TIMETABLE_DAY,
                                            CalendarDatabaseHelper.COLUMN_IS_HOLIDAY};
    private SimpleDateFormat sdf;
    private Calendar calendar;
    private Day day;

    public CalendarDatabaseSource(Context context)
    {
        helper = new CalendarDatabaseHelper(context, CalendarDatabaseHelper.DATABASE_NAME, null, CalendarDatabaseHelper.DATABASE_VERSION);
        sdf = new SimpleDateFormat("dd.MM.yyyy");
        calendar = Calendar.getInstance();
        day = new Day();
    }

    public void openDatabase()
    {
        if(database == null)
        {
            database = helper.getWritableDatabase();
        }
    }

    public void closeDatabase()
    {
        if(database.isOpen())
        {
            database.close();
        }
    }

    public void setupCalendar(Calendar startDate, Calendar endDate)
    {
        Day day = new Day();

        while(startDate.getTimeInMillis() <= endDate.getTimeInMillis())
        {
            day.TIMESTAMP = sdf.format(startDate);
            day.TIMETABLE_DAY = startDate.get(Calendar.DAY_OF_WEEK);
            day.IS_HOLIDAY = false;
            createDay(day);
            startDate.add(Calendar.DAY_OF_YEAR, 1);
        }
    }

    public void createDay(Day day)
    {
        ContentValues values = new ContentValues();
        values.put(CalendarDatabaseHelper.COLUMN_TIMESTAMP, day.TIMESTAMP);
        values.put(CalendarDatabaseHelper.COLUMN_TIMETABLE_DAY, day.TIMETABLE_DAY);
        values.put(CalendarDatabaseHelper.COLUMN_IS_HOLIDAY, day.IS_HOLIDAY?1:0);
        database.insert(CalendarDatabaseHelper.TABLE_NAME, null, values);
    }

    public void updateDay(Day day)
    {
        ContentValues values = new ContentValues();
        values.put(CalendarDatabaseHelper.COLUMN_TIMESTAMP, day.TIMESTAMP);
        values.put(CalendarDatabaseHelper.COLUMN_TIMETABLE_DAY, day.TIMETABLE_DAY);
        values.put(CalendarDatabaseHelper.COLUMN_IS_HOLIDAY, day.IS_HOLIDAY?1:0);
        database.update(CalendarDatabaseHelper.TABLE_NAME, values, CalendarDatabaseHelper.COLUMN_ID + " = " + day.ID, null);
    }

    public void deleteAll()
    {
        database.delete(CalendarDatabaseHelper.TABLE_NAME, null, null);
    }

    public void deleteDay(long id)
    {
        database.delete(CalendarDatabaseHelper.TABLE_NAME, CalendarDatabaseHelper.COLUMN_ID + " = " + id, null);
    }

    public Day getDay(long id)
    {
        Cursor cursor = database.query(CalendarDatabaseHelper.TABLE_NAME, allColumns, CalendarDatabaseHelper.COLUMN_ID + " = " + id, null, null, null, null, null);

        if(cursor.moveToFirst())
        {
            day = cursorToDay(cursor);
            cursor.close();

            return day;
        }
        else
        {
            return null;
        }
    }

    public List<Day> getDaysOfMonth(int month)
    {
        List<Day> list = new ArrayList<Day>();
        Cursor cursor = database.query(CalendarDatabaseHelper.TABLE_NAME, allColumns, null, null, null, null, null, null);

        if(cursor.moveToFirst())
        {
            while (!cursor.isAfterLast())
            {
                day = cursorToDay(cursor);
                cursor.moveToNext();

                try
                {
                    calendar.setTime(sdf.parse(day.TIMESTAMP));
                }
                catch(ParseException pe)
                {

                }

                if(calendar.get(Calendar.MONTH) == month)
                {
                    list.add(day);
                }
            }

            cursor.close();
        }

        return list;
    }

    public List<Day> getDaysList()
    {
        List<Day> list = new ArrayList<Day>();
        Cursor cursor = database.query(CalendarDatabaseHelper.TABLE_NAME, allColumns, null, null, null, null, null, null);

        if(cursor.moveToFirst())
        {
            while (!cursor.isAfterLast())
            {
                list.add(cursorToDay(cursor));
                cursor.moveToNext();
            }

            cursor.close();
        }

        return list;
    }

    private Day cursorToDay(Cursor cursor)
    {
        Day day = new Day();
        day.ID = cursor.getLong(0);
        day.TIMESTAMP = cursor.getString(1);
        day.TIMETABLE_DAY = cursor.getInt(2);
        day.IS_HOLIDAY = cursor.getInt(3)==1?true:false;

        return day;
    }
}
