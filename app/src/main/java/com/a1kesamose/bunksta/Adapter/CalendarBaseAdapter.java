package com.a1kesamose.bunksta.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.a1kesamose.bunksta.Object.Day;
import com.a1kesamose.bunksta.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class CalendarBaseAdapter extends BaseAdapter
{
    private LayoutInflater inflater;
    private List<Day> list;
    private ViewHolder viewHolder;
    private static final String timetableOptions[] = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "No classes"};
    private SimpleDateFormat sdfDate;
    private SimpleDateFormat sdfDay;

    private TextView textViewDay;
    private TextView textViewTimetable;

    public CalendarBaseAdapter(Context context, List<Day> list)
    {
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.list = list;
        this.sdfDate = new SimpleDateFormat("dd.MM.yyyy");
        this.sdfDay = new SimpleDateFormat("dd");
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
        if(view == null)
        {
            view = inflater.inflate(R.layout.list_item_calendar, viewGroup, false);
            viewHolder = new ViewHolder();

            try
            {
                viewHolder.textViewDay.setText(sdfDay.format(sdfDate.parse(list.get(position).TIMESTAMP)));
                viewHolder.textViewTimetable.setText(timetableOptions[list.get(position).IS_HOLIDAY?7:list.get(position).TIMETABLE_DAY]);
            }
            catch(ParseException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            viewHolder = (ViewHolder)view.getTag();
        }

        textViewDay = viewHolder.textViewDay;
        textViewTimetable = viewHolder.textViewTimetable;

        return view;
    }

    static class ViewHolder
    {
        TextView textViewDay;
        TextView textViewTimetable;
    }
}
