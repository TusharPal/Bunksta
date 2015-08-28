package com.a1kesamose.bunksta.Fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.a1kesamose.bunksta.Activity.ActivityCourseEditor;
import com.a1kesamose.bunksta.Activity.ActivityMain;
import com.a1kesamose.bunksta.Adapter.CourseBaseAdapter;
import com.a1kesamose.bunksta.Database.CourseDatabaseSource;
import com.a1kesamose.bunksta.R;

public class FragmentCourses extends Fragment implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener
{
    private static final String ARG_SECTION_NUMBER = "section_number";
    private SharedPreferences sharedPreferences;
    private CourseDatabaseSource databaseSource;

    private ListView listView;

    public static FragmentCourses newInstance(int sectionNumber)
    {
        FragmentCourses fragment = new FragmentCourses();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);

        return fragment;
    }

    public FragmentCourses()
    {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        setHasOptionsMenu(true);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        databaseSource = new CourseDatabaseSource(getActivity().getApplicationContext());

        View rootView = inflater.inflate(R.layout.fragment_courses, container, false);
        listView = (ListView)rootView.findViewById(R.id.fragment_courses_listView);

        return rootView;
    }

    @Override
    public void onResume()
    {
        super.onResume();

        databaseSource.openDatabase();
        listView.setAdapter(new CourseBaseAdapter(getActivity().getApplicationContext(), databaseSource.getCourseList()));
        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);
    }

    @Override
    public void onPause()
    {
        super.onPause();

        databaseSource.closeDatabase();
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater)
    {
        menuInflater.inflate(R.menu.menu_fragment_courses, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.fragment_courses_action_new:
            {
                Intent intent = new Intent(getActivity().getApplicationContext(), ActivityCourseEditor.class);
                intent.putExtra("new_course", true);
                startActivity(intent);

                return true;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        ((ActivityMain) activity).onSectionAttached(getArguments().getInt(ARG_SECTION_NUMBER));
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id)
    {
        Intent intent = new Intent(getActivity().getApplicationContext(), ActivityCourseEditor.class);
        intent.putExtra("new_course", false);
        intent.putExtra("course_id", databaseSource.getCourseList().get(position).COURSE_ID);
        startActivity(intent);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id)
    {
        final long courseId = databaseSource.getCourseList().get(position).COURSE_ID;
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Delete course?");
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {

                databaseSource.deleteCourse(courseId);
                listView.setAdapter(new CourseBaseAdapter(getActivity().getApplicationContext(), databaseSource.getCourseList()));
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {

            }
        });
        builder.create().show();

        return true;
    }
}
