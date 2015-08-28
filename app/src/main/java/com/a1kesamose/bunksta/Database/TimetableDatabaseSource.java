package com.a1kesamose.bunksta.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.a1kesamose.bunksta.Object.Timetable;

import java.util.ArrayList;
import java.util.List;

public class TimetableDatabaseSource
{
    private TimetableDatabaseHelper helper;
    private SQLiteDatabase database;
    private final String allColumns[] = {TimetableDatabaseHelper.COLUMN_TIMETABLE_ID,
                                            TimetableDatabaseHelper.COLUMN_TIMETABLE_COURSE_ID,
                                            TimetableDatabaseHelper.COLUMN_TIMETABLE_COURSE_NAME,
                                            TimetableDatabaseHelper.COLUMN_TIMETABLE_COURSE_COLOR,
                                            TimetableDatabaseHelper.COLUMN_TIMETABLE_DAY_OF_WEEK,
                                            TimetableDatabaseHelper.COLUMN_TIMETABLE_START_HOUR,
                                            TimetableDatabaseHelper.COLUMN_TIMETABLE_START_MINUTE,
                                            TimetableDatabaseHelper.COLUMN_TIMETABLE_END_HOUR,
                                            TimetableDatabaseHelper.COLUMN_TIMETABLE_END_MINUTE};

    public TimetableDatabaseSource(Context context)
    {
        helper = new TimetableDatabaseHelper(context, TimetableDatabaseHelper.DATABASE_NAME, null, TimetableDatabaseHelper.DATABASE_VERSION);
    }

    public void openDatabase()
    {
        database = helper.getWritableDatabase();
    }

    public void closeDatabase()
    {
        if(database.isOpen())
        {
            database.close();
        }
    }

    public void createTimetable(Timetable timetable)
    {
        ContentValues values = new ContentValues();
        values.put(TimetableDatabaseHelper.COLUMN_TIMETABLE_ID, timetable.TIMETABLE_COURSE_ID);
        values.put(TimetableDatabaseHelper.COLUMN_TIMETABLE_COURSE_ID, timetable.TIMETABLE_COURSE_ID);
        values.put(TimetableDatabaseHelper.COLUMN_TIMETABLE_COURSE_NAME, timetable.TIMETABLE_COURSE_NAME);
        values.put(TimetableDatabaseHelper.COLUMN_TIMETABLE_COURSE_COLOR, timetable.TIMETABLE_COURSE_COLOR);
        values.put(TimetableDatabaseHelper.COLUMN_TIMETABLE_DAY_OF_WEEK, timetable.TIMETABLE_DAY_OF_WEEK);
        values.put(TimetableDatabaseHelper.COLUMN_TIMETABLE_START_HOUR, timetable.TIMETABLE_START_HOUR);
        values.put(TimetableDatabaseHelper.COLUMN_TIMETABLE_START_MINUTE, timetable.TIMETABLE_START_MINUTE);
        values.put(TimetableDatabaseHelper.COLUMN_TIMETABLE_END_HOUR, timetable.TIMETABLE_END_HOUR);
        values.put(TimetableDatabaseHelper.COLUMN_TIMETABLE_END_MINUTE, timetable.TIMETABLE_END_MINUTE);
        database.insert(TimetableDatabaseHelper.TABLE_NAME, null, values);
    }

    public void updateTimetable(Timetable timetable)
    {
        ContentValues values = new ContentValues();
        values.put(TimetableDatabaseHelper.COLUMN_TIMETABLE_ID, timetable.TIMETABLE_COURSE_ID);
        values.put(TimetableDatabaseHelper.COLUMN_TIMETABLE_COURSE_ID, timetable.TIMETABLE_COURSE_ID);
        values.put(TimetableDatabaseHelper.COLUMN_TIMETABLE_COURSE_NAME, timetable.TIMETABLE_COURSE_NAME);
        values.put(TimetableDatabaseHelper.COLUMN_TIMETABLE_COURSE_COLOR, timetable.TIMETABLE_COURSE_COLOR);
        values.put(TimetableDatabaseHelper.COLUMN_TIMETABLE_DAY_OF_WEEK, timetable.TIMETABLE_DAY_OF_WEEK);
        values.put(TimetableDatabaseHelper.COLUMN_TIMETABLE_START_HOUR, timetable.TIMETABLE_START_HOUR);
        values.put(TimetableDatabaseHelper.COLUMN_TIMETABLE_START_MINUTE, timetable.TIMETABLE_START_MINUTE);
        values.put(TimetableDatabaseHelper.COLUMN_TIMETABLE_END_HOUR, timetable.TIMETABLE_END_HOUR);
        values.put(TimetableDatabaseHelper.COLUMN_TIMETABLE_END_MINUTE, timetable.TIMETABLE_END_MINUTE);
        database.update(TimetableDatabaseHelper.TABLE_NAME, values, TimetableDatabaseHelper.COLUMN_TIMETABLE_ID + " = " + timetable.TIMETABLE_ID, null);
    }

    public void deleteTimetable(long id)
    {
        database.delete(TimetableDatabaseHelper.TABLE_NAME, TimetableDatabaseHelper.COLUMN_TIMETABLE_ID + " = " + id, null);
    }

    public void deleteTimetableByCourse(long id)
    {
        database.delete(TimetableDatabaseHelper.TABLE_NAME, TimetableDatabaseHelper.COLUMN_TIMETABLE_COURSE_ID + " = " + id, null);
    }

    public Timetable getTimetable(long id)
    {
        Cursor cursor = database.query(TimetableDatabaseHelper.TABLE_NAME, allColumns, TimetableDatabaseHelper.COLUMN_TIMETABLE_ID + " = " + id, null, null, null, null, null);

        if(cursor.moveToFirst())
        {
            Timetable timetable = cursorToTimetable(cursor);
            cursor.close();

            return timetable;
        }
        else
        {
            return null;
        }
    }

    public List<Timetable> getTimetableList(int dayOfWeek)
    {
        List<Timetable> list = new ArrayList<Timetable>();
        Cursor cursor = database.query(TimetableDatabaseHelper.TABLE_NAME, allColumns, TimetableDatabaseHelper.COLUMN_TIMETABLE_DAY_OF_WEEK + " = " + dayOfWeek, null, null, null, TimetableDatabaseHelper.COLUMN_TIMETABLE_START_HOUR + " ASC, " + TimetableDatabaseHelper.COLUMN_TIMETABLE_START_MINUTE + " ASC", null);

        if(cursor.moveToFirst())
        {
            while (!cursor.isAfterLast()) {
                list.add(cursorToTimetable(cursor));
                cursor.moveToNext();
            }
            cursor.close();
        }

        return list;
    }

    public int isValidTimetable(int startHour, int startMinute, int endHour, int endMinute, int dayOfWeek)
    {
        List<Timetable> list = getTimetableList(dayOfWeek);
        int listStartTime;
        int listEndTime;
        int startTime = (startHour * 100) + startMinute;
        int endTime = (endHour * 100) + endMinute;

        if(startTime >= endTime )
        {
            return 1;
        }
        
        for(Timetable timetable: list)
        {
            listStartTime = (timetable.TIMETABLE_START_HOUR * 100) + timetable.TIMETABLE_START_MINUTE;
            listEndTime = (timetable.TIMETABLE_END_HOUR * 100) + timetable.TIMETABLE_END_MINUTE;

            if((startTime <= listStartTime) && (endTime >= listEndTime))
            {
                return 2;
            }
            else if((startTime <= listStartTime) && (endTime > listStartTime) && (endTime <= listEndTime))
            {
                return 2;
            }
            else if((startTime >= listStartTime) && (startTime < listEndTime) && (endTime >= listEndTime))
            {
                return 2;
            }
            else if((startTime >= listStartTime) && (endTime <= listEndTime))
            {
                return 2;
            }
            else
            {
                continue;
            }
        }

        return 3;
    }

    private Timetable cursorToTimetable(Cursor cursor)
    {
        Timetable timetable = new Timetable();
        timetable.TIMETABLE_ID = cursor.getLong(0);
        timetable.TIMETABLE_COURSE_ID = cursor.getLong(1);
        timetable.TIMETABLE_COURSE_NAME = cursor.getString(2);
        timetable.TIMETABLE_COURSE_COLOR = cursor.getString(3);
        timetable.TIMETABLE_DAY_OF_WEEK = cursor.getInt(4);
        timetable.TIMETABLE_START_HOUR = cursor.getInt(5);
        timetable.TIMETABLE_START_MINUTE = cursor.getInt(6);
        timetable.TIMETABLE_END_HOUR = cursor.getInt(7);
        timetable.TIMETABLE_END_MINUTE = cursor.getInt(8);

        return timetable;
    }
}
