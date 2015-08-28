package com.a1kesamose.bunksta.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CalendarDatabaseHelper extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME = "calendar.db";
    public static final int DATABASE_VERSION = 4;
    public static final String TABLE_NAME = "calendar";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TIMESTAMP = "timestamp";
    public static final String COLUMN_TIMETABLE_DAY = "timetable_day";
    public static final String COLUMN_IS_HOLIDAY = "is_holiday";

    private static final String DATABASE_CREATE = "CREATE TABLE " + TABLE_NAME + "("
                                                    + COLUMN_ID + " PRIMARY KEY AUTOINCREMENT, "
                                                    + COLUMN_TIMESTAMP + " STRING NOT NULL, "
                                                    + COLUMN_TIMETABLE_DAY + " INTEGER NOT NULL, "
                                                    + COLUMN_IS_HOLIDAY + " INTEGER NOT NULL);";

    public CalendarDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
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
