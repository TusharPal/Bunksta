package com.a1kesamose.bunksta.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.a1kesamose.bunksta.Object.Course;
import com.a1kesamose.bunksta.R;

import java.util.List;

public class CourseSpinnerBaseAdapter extends BaseAdapter
{
    private List<Course> list;
    private LayoutInflater inflater;

    public CourseSpinnerBaseAdapter(Context context, List<Course> list)
    {
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.list = list;
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
        view = inflater.inflate(R.layout.spinner_item_course, viewGroup, false);
        View viewColor = view.findViewById(R.id.spinner_item_course_view_color);
        viewColor.setBackgroundColor(Color.parseColor(list.get(position).COURSE_COLOR));
        TextView textViewCourseName = (TextView)view.findViewById(R.id.spinner_item_course_textView_name);
        textViewCourseName.setHorizontallyScrolling(true);
        textViewCourseName.setHorizontalScrollBarEnabled(false);
        textViewCourseName.setHorizontalFadingEdgeEnabled(false);
        textViewCourseName.setText(list.get(position).COURSE_NAME);

        return view;
    }
}
