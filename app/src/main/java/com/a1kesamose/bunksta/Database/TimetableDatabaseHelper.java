package com.a1kesamose.bunksta.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TimetableDatabaseHelper extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME = "timetable.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "timetable";

    public static final String COLUMN_TIMETABLE_ID = "_id";
    public static final String COLUMN_TIMETABLE_COURSE_ID = "course_id";
    public static final String COLUMN_TIMETABLE_COURSE_NAME = "course_name";
    public static final String COLUMN_TIMETABLE_COURSE_COLOR = "course_color";
    public static final String COLUMN_TIMETABLE_DAY_OF_WEEK = "day_of_week";
    public static final String COLUMN_TIMETABLE_START_HOUR = "start_hour";
    public static final String COLUMN_TIMETABLE_START_MINUTE = "start_minute";
    public static final String COLUMN_TIMETABLE_END_HOUR = "end_hour";
    public static final String COLUMN_TIMETABLE_END_MINUTE = "end_minute";

    private static final String DATABASE_CREATE = "CREATE TABLE " + TABLE_NAME + "("
                                                    + COLUMN_TIMETABLE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                                                    + COLUMN_TIMETABLE_COURSE_ID + " INTEGER NOT NULL, "
                                                    + COLUMN_TIMETABLE_COURSE_NAME + " TEXT NOT NULL, "
                                                    + COLUMN_TIMETABLE_COURSE_COLOR + " TEXT NOT NULL, "
                                                    + COLUMN_TIMETABLE_DAY_OF_WEEK + " INTEGER NOT NULL, "
                                                    + COLUMN_TIMETABLE_START_HOUR + " INTEGER NOT NULL, "
                                                    + COLUMN_TIMETABLE_START_MINUTE + " INTEGER NOT NULL, "
                                                    + COLUMN_TIMETABLE_END_HOUR + " INTEGER NOT NULL, "
                                                    + COLUMN_TIMETABLE_END_MINUTE + " INTEGER NOT NULL);";

    public TimetableDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        sqLiteDatabase.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion)
    {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
