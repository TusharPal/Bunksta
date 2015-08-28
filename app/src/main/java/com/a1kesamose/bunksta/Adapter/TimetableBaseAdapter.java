package com.a1kesamose.bunksta.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.a1kesamose.bunksta.Object.Timetable;
import com.a1kesamose.bunksta.R;

import java.util.List;

public class TimetableBaseAdapter extends BaseAdapter
{
    private Context context;
    private LayoutInflater inflater;
    private List<Timetable> list;

    public TimetableBaseAdapter(Context context, List<Timetable> list)
    {
        this.context = context;
        this.list = list;

        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount()
    {
        return list.size();
    }

    @Override
    public Object getItem(int position)
    {
        return list.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup)
    {
        view = inflater.inflate(R.layout.list_item_timetable, viewGroup, false);
        View viewCourseColor = view.findViewById(R.id.list_item_timetable_course_view_color);
        TextView textViewCourseName = (TextView)view.findViewById(R.id.list_item_timetable_course_textView_name);
        TextView textViewTimings = (TextView)view.findViewById(R.id.list_item_timetable_textView_timings);
        viewCourseColor.setBackgroundColor(Color.parseColor(list.get(position).TIMETABLE_COURSE_COLOR));
        textViewCourseName.setHorizontallyScrolling(true);
        textViewCourseName.setHorizontalFadingEdgeEnabled(false);
        textViewCourseName.setHorizontalScrollBarEnabled(false);
        textViewCourseName.setText(list.get(position).TIMETABLE_COURSE_NAME);
        textViewTimings.setText(getTimeStamp(list.get(position).TIMETABLE_START_HOUR, list.get(position).TIMETABLE_START_MINUTE, list.get(position).TIMETABLE_END_HOUR, list.get(position).TIMETABLE_END_MINUTE));

        return view;
    }

    private String getTimeStamp(int startHour, int startMinute, int endHour, int endMinute)
    {
        String timestamp = "";

        if(DateFormat.is24HourFormat(context))
        {
            timestamp = startHour<10? "0" + startHour + ":": startHour + ":";
            timestamp += startMinute<10? "0" + startMinute + " to ": startMinute + " to ";
            timestamp += endHour<10? "0" + endHour + ":": endHour + ":";
            timestamp += endMinute<10? "0" + endMinute: endMinute;
        }
        else
        {
            if(startHour <= 12)
            {
                timestamp = startHour<10? "0" + startHour + ":": startHour + ":";
                timestamp += startMinute<10? "0" + startMinute + " AM to ": startMinute + " AM to ";
            }
            else
            {
                startHour -= 12;
                timestamp = startHour<10? "0" + startHour + ":": startHour + ":";
                timestamp += startMinute<10? "0" + startMinute + " PM to ": startMinute + " PM to ";
            }

            if(endHour <= 12)
            {
                timestamp += endHour<10? "0" + endHour + ":": endHour + ":";
                timestamp += endMinute<10? "0" + endMinute + " AM to ": endMinute + " AM to ";
            }
            else
            {
                endHour -= 12;
                timestamp = endHour<10? "0" + endHour + ":": endHour + ":";
                timestamp += endMinute<10? "0" + endMinute + " PM to ": endMinute + " PM to ";
            }
        }

        return timestamp;
    }
}
