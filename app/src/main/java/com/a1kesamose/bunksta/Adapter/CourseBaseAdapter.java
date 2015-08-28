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

public class CourseBaseAdapter extends BaseAdapter
{
    private List<Course> list;
    private LayoutInflater inflater;

    public CourseBaseAdapter(Context context, List<Course> list)
    {
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
        view = inflater.inflate(R.layout.list_item_course, viewGroup, false);
        View viewCourseColor = view.findViewById(R.id.list_item_course_view_color);
        TextView textViewCourseName = (TextView)view.findViewById(R.id.list_item_course_textView_name);
        TextView textViewCourseCode = (TextView)view.findViewById(R.id.list_item_course_textView_code);
        TextView textViewCourseInstructorName = (TextView)view.findViewById(R.id.list_item_course_textView_instructor_name);
        TextView textViewCoursePercentage = (TextView)view.findViewById(R.id.list_item_course_textView_minimum_attendance_percentage);
        viewCourseColor.setBackgroundColor(Color.parseColor(list.get(position).COURSE_COLOR));
        textViewCourseName.setText(list.get(position).COURSE_NAME);
        textViewCourseCode.setText(list.get(position).COURSE_CODE);
        textViewCourseInstructorName.setText(list.get(position).COURSE_INSTRUCTOR_NAME);
        textViewCoursePercentage.setText(Integer.toString(list.get(position).COURSE_MINIMUM_ATTENDANCE_PERCENTAGE));

        return view;
    }
}
