package com.a1kesamose.bunksta.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CourseDatabaseHelper extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME = "courses.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "courses";

    public static final String COLUMN_COURSE_ID = "_id";
    public static final String COLUMN_COURSE_NAME = "name";
    public static final String COLUMN_COURSE_COLOR = "color";
    public static final String COLUMN_COURSE_CODE = "code";
    public static final String COLUMN_INSTRUCTOR_NAME = "instructorname";
    public static final String COLUMN_MINIMUM_ATTENDANCE_PERCENTAGE = "percentage";

    private static final String DATABASE_CREATE = "CREATE TABLE " + TABLE_NAME + "("
                                                    + COLUMN_COURSE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                                                    + COLUMN_COURSE_NAME + " TEXT NOT NULL, "
                                                    + COLUMN_COURSE_COLOR + " TEXT NOT NULL, "
                                                    + COLUMN_COURSE_CODE + " TEXT NOT NULL, "
                                                    + COLUMN_INSTRUCTOR_NAME + " TEXT NOT NULL, "
                                                    + COLUMN_MINIMUM_ATTENDANCE_PERCENTAGE + " INTEGER NOT NULL);";

    public CourseDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
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
