package com.a1kesamose.bunksta.Object;

public class Course
{
    public long COURSE_ID;
    public String COURSE_NAME;
    public String COURSE_COLOR;
    public String COURSE_CODE;
    public String COURSE_INSTRUCTOR_NAME;
    public int COURSE_MINIMUM_ATTENDANCE_PERCENTAGE;

    public Course()
    {
        COURSE_ID = 0;
        COURSE_NAME = "";
        COURSE_COLOR = "#ff0080ff";
        COURSE_CODE = "";
        COURSE_INSTRUCTOR_NAME = "";
        COURSE_MINIMUM_ATTENDANCE_PERCENTAGE = 0;
    }

    public Course(long COURSE_ID, String COURSE_NAME, String COURSE_COLOR, String COURSE_CODE, String COURSE_INSTRUCTOR_NAME, int COURSE_MINIMUM_ATTENDANCE_PERCENTAGE)
    {
        this.COURSE_ID = COURSE_ID;
        this.COURSE_NAME = COURSE_NAME;
        this.COURSE_COLOR = COURSE_COLOR;
        this.COURSE_CODE = COURSE_CODE;
        this.COURSE_INSTRUCTOR_NAME = COURSE_INSTRUCTOR_NAME;
        this.COURSE_MINIMUM_ATTENDANCE_PERCENTAGE = COURSE_MINIMUM_ATTENDANCE_PERCENTAGE;
    }
}
