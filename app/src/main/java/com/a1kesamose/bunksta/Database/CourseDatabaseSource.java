package com.a1kesamose.bunksta.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.a1kesamose.bunksta.Object.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseDatabaseSource
{
    private CourseDatabaseHelper helper;
    private SQLiteDatabase database;
    private final String allColumns[] = {CourseDatabaseHelper.COLUMN_COURSE_ID,
                                        CourseDatabaseHelper.COLUMN_COURSE_NAME,
                                        CourseDatabaseHelper.COLUMN_COURSE_COLOR,
                                        CourseDatabaseHelper.COLUMN_COURSE_CODE,
                                        CourseDatabaseHelper.COLUMN_INSTRUCTOR_NAME,
                                        CourseDatabaseHelper.COLUMN_MINIMUM_ATTENDANCE_PERCENTAGE};

    public CourseDatabaseSource(Context context)
    {
        helper = new CourseDatabaseHelper(context, CourseDatabaseHelper.DATABASE_NAME, null, CourseDatabaseHelper.DATABASE_VERSION);
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

    public void createCourse(Course course)
    {
        ContentValues values = new ContentValues();
        values.put(CourseDatabaseHelper.COLUMN_COURSE_NAME, course.COURSE_NAME);
        values.put(CourseDatabaseHelper.COLUMN_COURSE_COLOR, course.COURSE_COLOR);
        values.put(CourseDatabaseHelper.COLUMN_COURSE_CODE, course.COURSE_CODE);
        values.put(CourseDatabaseHelper.COLUMN_INSTRUCTOR_NAME, course.COURSE_INSTRUCTOR_NAME);
        values.put(CourseDatabaseHelper.COLUMN_MINIMUM_ATTENDANCE_PERCENTAGE, course.COURSE_MINIMUM_ATTENDANCE_PERCENTAGE);
        database.insert(CourseDatabaseHelper.TABLE_NAME, null, values);
    }

    public void updateCourse(Course course)
    {
        ContentValues values = new ContentValues();
        values.put(CourseDatabaseHelper.COLUMN_COURSE_NAME, course.COURSE_NAME);
        values.put(CourseDatabaseHelper.COLUMN_COURSE_COLOR, course.COURSE_COLOR);
        values.put(CourseDatabaseHelper.COLUMN_COURSE_CODE, course.COURSE_CODE);
        values.put(CourseDatabaseHelper.COLUMN_INSTRUCTOR_NAME, course.COURSE_INSTRUCTOR_NAME);
        values.put(CourseDatabaseHelper.COLUMN_MINIMUM_ATTENDANCE_PERCENTAGE, course.COURSE_MINIMUM_ATTENDANCE_PERCENTAGE);
        database.update(CourseDatabaseHelper.TABLE_NAME, values, CourseDatabaseHelper.COLUMN_COURSE_ID + " = " + course.COURSE_ID, null);
    }

    public void deleteCourse(long id)
    {
        database.delete(CourseDatabaseHelper.TABLE_NAME, CourseDatabaseHelper.COLUMN_COURSE_ID + " = " + id, null);
    }

    public Course getCourse(long id)
    {
        Cursor cursor = database.query(CourseDatabaseHelper.TABLE_NAME, allColumns, CourseDatabaseHelper.COLUMN_COURSE_ID + " = " + id, null, null, null, null, null);

        if(cursor.moveToFirst())
        {
            Course course = cursorToCourse(cursor);
            cursor.close();

            return course;
        }
        else
        {
            return null;
        }
    }

    public List<Course> getCourseList()
    {
        List<Course> list = new ArrayList<Course>();
        Cursor cursor = database.query(CourseDatabaseHelper.TABLE_NAME, allColumns, null, null, null, null, null, null);

        if(cursor.moveToFirst())
        {
            while (!cursor.isAfterLast()) {
                list.add(cursorToCourse(cursor));
                cursor.moveToNext();
            }
            cursor.close();
        }

        return list;
    }

    public int getCoursePosition(long id)
    {
        List<Course> list = new ArrayList<Course>();
        Cursor cursor = database.query(CourseDatabaseHelper.TABLE_NAME, allColumns, null, null, null, null, null, null);

        if(cursor.moveToFirst())
        {
            while (!cursor.isAfterLast()) {
                list.add(cursorToCourse(cursor));
                cursor.moveToNext();
            }
            cursor.close();
        }

        int position = 0;
        for(; position<list.size(); position++)
        {
            if(list.get(position).COURSE_ID == id)
            {
                break;
            }
            else
            {
                continue;
            }
        }

        return position;
    }

    private Course cursorToCourse(Cursor cursor)
    {
        Course course = new Course();
        course.COURSE_ID = cursor.getLong(0);
        course.COURSE_NAME = cursor.getString(1);
        course.COURSE_COLOR = cursor.getString(2);
        course.COURSE_CODE = cursor.getString(3);
        course.COURSE_INSTRUCTOR_NAME = cursor.getString(4);
        course.COURSE_MINIMUM_ATTENDANCE_PERCENTAGE = cursor.getInt(5);

        return course;
    }
}
