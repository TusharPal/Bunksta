package com.a1kesamose.bunksta.Object;

public class Schedule
{
    public long SCHEDULE_ID;
    public long SCHEDULE_COURSE_ID;
    public int SCHEDULE_WEEKDAY;
    public int SCHEDULE_START_HOUR;
    public int SCHEDULE_START_MINUTE;
    public int SCHEDULE_END_HOUR;
    public int SCHEDULE_END_MINUTE;
    public String SCHEDULE_NOTE;
    public float SCHEDULE_LATITUDE;
    public float SCHEDULE_LONGITUDE;

    public Schedule()
    {
        this.SCHEDULE_ID = 0;
        this.SCHEDULE_COURSE_ID = 0;
        this.SCHEDULE_WEEKDAY = 0;
        this.SCHEDULE_START_HOUR = 0;
        this.SCHEDULE_START_MINUTE = 0;
        this.SCHEDULE_END_HOUR = 0;
        this.SCHEDULE_END_MINUTE = 0;
        this.SCHEDULE_NOTE = "";
        this.SCHEDULE_LATITUDE = 0;
        this.SCHEDULE_LONGITUDE = 0;
    }

    public Schedule(long SCHEDULE_ID, long SCHEDULE_COURSE_ID, int SCHEDULE_WEEKDAY, int SCHEDULE_START_HOUR, int SCHEDULE_START_MINUTE, int SCHEDULE_END_HOUR, int SCHEDULE_END_MINUTE, String SCHEDULE_NOTE, float SCHEDULE_LATITUDE, float SCHEDULE_LONGITUDE)
    {
        this.SCHEDULE_ID = SCHEDULE_ID;
        this.SCHEDULE_COURSE_ID = SCHEDULE_COURSE_ID;
        this.SCHEDULE_WEEKDAY = SCHEDULE_WEEKDAY;
        this.SCHEDULE_START_HOUR = SCHEDULE_START_HOUR;
        this.SCHEDULE_START_MINUTE = SCHEDULE_START_MINUTE;
        this.SCHEDULE_END_HOUR = SCHEDULE_END_HOUR;
        this.SCHEDULE_END_MINUTE = SCHEDULE_END_MINUTE;
        this.SCHEDULE_NOTE = SCHEDULE_NOTE;
        this.SCHEDULE_LATITUDE = SCHEDULE_LATITUDE;
        this.SCHEDULE_LONGITUDE = SCHEDULE_LONGITUDE;
    }
}
