package com.a1kesamose.bunksta.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.a1kesamose.bunksta.R;

public class DayOfWeekSpinnerBaseAdapter extends BaseAdapter
{
    private String daysOfWeek[] = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    private LayoutInflater inflater;

    public DayOfWeekSpinnerBaseAdapter(Context context)
    {
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount()
    {
        return daysOfWeek.length;
    }

    @Override
    public Object getItem(int position)
    {
        return daysOfWeek[position];
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup)
    {
        view = inflater.inflate(R.layout.spinner_item_day_of_week, viewGroup, false);
        TextView textViewDayName = (TextView)view.findViewById(R.id.spinner_item_day_of_week);
        textViewDayName.setHorizontallyScrolling(true);
        textViewDayName.setHorizontalScrollBarEnabled(false);
        textViewDayName.setHorizontalFadingEdgeEnabled(false);
        textViewDayName.setText(daysOfWeek[position]);

        return view;
    }
}
