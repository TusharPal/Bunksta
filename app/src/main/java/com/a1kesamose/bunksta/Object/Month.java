package com.a1kesamose.bunksta.Object;

public class Month
{
    public String MONTH_NAME;
    public int MONTH_POSITION;
    public int MONTH_INDEX;
    public int MONTH_START_DAY;
    public int MONTH_START_DAY_OF_WEEK;
    public int MONTH_END_DAY;
    public int START_DAY;
    public int END_DAY;

    public Month()
    {
        this.MONTH_NAME = "";
        this.MONTH_POSITION = 0;
        this.MONTH_INDEX = 0;
        this.MONTH_START_DAY = 0;
        this.MONTH_START_DAY_OF_WEEK = 0;
        this.MONTH_END_DAY = 0;
        this.START_DAY = 0;
        this.END_DAY = 0;
    }

    public Month(String MONTH_NAME,  int MONTH_INDEX, int MONTH_POSITION, int MONTH_START_DAY, int MONTH_START_DAY_OF_WEEK, int MONTH_END_DAY, int START_DAY, int END_DAY)
    {
        this.MONTH_NAME = MONTH_NAME;
        this.MONTH_POSITION = MONTH_POSITION;
        this.MONTH_INDEX = MONTH_INDEX;
        this.MONTH_START_DAY = MONTH_START_DAY;
        this.MONTH_START_DAY_OF_WEEK = MONTH_START_DAY_OF_WEEK;
        this.MONTH_END_DAY = MONTH_END_DAY;
        this.START_DAY = START_DAY;
        this.END_DAY = END_DAY;
    }
}
