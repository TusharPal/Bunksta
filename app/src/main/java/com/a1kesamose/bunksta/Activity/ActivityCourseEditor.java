package com.a1kesamose.bunksta.Activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.a1kesamose.bunksta.Database.CourseDatabaseSource;
import com.a1kesamose.bunksta.Object.Course;
import com.a1kesamose.bunksta.R;

public class ActivityCourseEditor extends AppCompatActivity implements View.OnClickListener
{
    private CourseDatabaseSource databaseSource;
    private Course newCourse;
    private LayoutInflater inflater;

    private View viewCourseColor;
    private EditText editTextCourseName;
    private EditText editTextCourseCode;
    private EditText editTextCourseInstructorName;
    private EditText editTextCourseMinimumAttendancePercentage;
    private AlertDialog alertDialogColorPicker;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_editor);

        viewCourseColor = findViewById(R.id.activity_course_editor_view_course_color);
        viewCourseColor.setOnClickListener(this);
        editTextCourseName = (EditText)findViewById(R.id.activity_course_editor_editText_course_name);
        editTextCourseCode = (EditText)findViewById(R.id.activity_course_editor_editText_course_code);
        editTextCourseInstructorName = (EditText)findViewById(R.id.activity_course_editor_editText_course_instructor_name);
        editTextCourseMinimumAttendancePercentage = (EditText)findViewById(R.id.activity_course_editor_editText_course_minimum_attendance_percentage);

        inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        alertDialogColorPicker = createAlertDialogColorPicker();

        databaseSource = new CourseDatabaseSource(this);
    }

    protected void onResume()
    {
        super.onResume();

        databaseSource.openDatabase();

        if(getIntent().getExtras().getBoolean("new_course"))
        {
            newCourse = new Course();
        }
        else
        {
            newCourse = databaseSource.getCourse(getIntent().getExtras().getLong("course_id"));
            viewCourseColor.setBackgroundColor(Color.parseColor(newCourse.COURSE_COLOR));
            editTextCourseName.setText(newCourse.COURSE_NAME);
            editTextCourseCode.setText(newCourse.COURSE_CODE);
            editTextCourseInstructorName.setText(newCourse.COURSE_INSTRUCTOR_NAME);
            editTextCourseMinimumAttendancePercentage.setText(Integer.toString(newCourse.COURSE_MINIMUM_ATTENDANCE_PERCENTAGE));
        }
    }

    protected void onPause()
    {
        super.onPause();

        databaseSource.closeDatabase();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_activity_course_editor, menu);

        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.activity_course_editor_action_save:
            {
                saveCourse();

                return true;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view)
    {
        switch(view.getId())
        {
            case R.id.activity_course_editor_view_course_color:
            {
                alertDialogColorPicker.show();

                break;
            }
            case R.id.alertDialog_color_picker_button_00:
            {
                newCourse.COURSE_COLOR = "#ffff0000";
                viewCourseColor.setBackgroundColor(Color.parseColor(newCourse.COURSE_COLOR));
                alertDialogColorPicker.dismiss();

                break;
            }
            case R.id.alertDialog_color_picker_button_01:
            {
                newCourse.COURSE_COLOR = "#ffcc99ff";
                viewCourseColor.setBackgroundColor(Color.parseColor(newCourse.COURSE_COLOR));
                alertDialogColorPicker.dismiss();

                break;
            }
            case R.id.alertDialog_color_picker_button_02:
            {
                newCourse.COURSE_COLOR = "#ff9999ff";
                viewCourseColor.setBackgroundColor(Color.parseColor(newCourse.COURSE_COLOR));
                alertDialogColorPicker.dismiss();

                break;
            }
            case R.id.alertDialog_color_picker_button_03:
            {
                newCourse.COURSE_COLOR = "#ff0080ff";
                viewCourseColor.setBackgroundColor(Color.parseColor(newCourse.COURSE_COLOR));
                alertDialogColorPicker.dismiss();

                break;
            }
            case R.id.alertDialog_color_picker_button_04:
            {
                newCourse.COURSE_COLOR = "#ffffff00";
                viewCourseColor.setBackgroundColor(Color.parseColor(newCourse.COURSE_COLOR));
                alertDialogColorPicker.dismiss();

                break;
            }
            case R.id.alertDialog_color_picker_button_05:
            {
                newCourse.COURSE_COLOR = "#ff00cc00";
                viewCourseColor.setBackgroundColor(Color.parseColor(newCourse.COURSE_COLOR));
                alertDialogColorPicker.dismiss();

                break;
            }
            case R.id.alertDialog_color_picker_button_06:
            {
                newCourse.COURSE_COLOR = "#ff00cccc";
                viewCourseColor.setBackgroundColor(Color.parseColor(newCourse.COURSE_COLOR));
                alertDialogColorPicker.dismiss();

                break;
            }
            case R.id.alertDialog_color_picker_button_07:
            {
                newCourse.COURSE_COLOR = "#ff00ffff";
                viewCourseColor.setBackgroundColor(Color.parseColor(newCourse.COURSE_COLOR));
                alertDialogColorPicker.dismiss();

                break;
            }
            case R.id.alertDialog_color_picker_button_08:
            {
                newCourse.COURSE_COLOR = "#ffff8000";
                viewCourseColor.setBackgroundColor(Color.parseColor(newCourse.COURSE_COLOR));
                alertDialogColorPicker.dismiss();

                break;
            }
            case R.id.alertDialog_color_picker_button_09:
            {
                newCourse.COURSE_COLOR = "#ffff6666";
                viewCourseColor.setBackgroundColor(Color.parseColor(newCourse.COURSE_COLOR));
                alertDialogColorPicker.dismiss();

                break;
            }
            case R.id.alertDialog_color_picker_button_10:
            {
                newCourse.COURSE_COLOR = "#ffa0a0a0";
                viewCourseColor.setBackgroundColor(Color.parseColor(newCourse.COURSE_COLOR));
                alertDialogColorPicker.dismiss();

                break;
            }
            case R.id.alertDialog_color_picker_button_11:
            {
                newCourse.COURSE_COLOR = "#ffffffff";
                viewCourseColor.setBackgroundColor(Color.parseColor(newCourse.COURSE_COLOR));
                alertDialogColorPicker.dismiss();

                break;
            }
        }
    }

    public void saveCourse()
    {
        if(getIntent().getExtras().getBoolean("new_course"))
        {
            newCourse.COURSE_NAME = editTextCourseName.getText().toString();
            newCourse.COURSE_CODE = editTextCourseCode.getText().toString();
            newCourse.COURSE_INSTRUCTOR_NAME = editTextCourseInstructorName.getText().toString();
            newCourse.COURSE_MINIMUM_ATTENDANCE_PERCENTAGE = Integer.parseInt(editTextCourseMinimumAttendancePercentage.getText().toString());

            databaseSource.createCourse(newCourse);
        }
        else
        {
            newCourse.COURSE_NAME = editTextCourseName.getText().toString();
            newCourse.COURSE_CODE = editTextCourseCode.getText().toString();
            newCourse.COURSE_INSTRUCTOR_NAME = editTextCourseInstructorName.getText().toString();
            newCourse.COURSE_MINIMUM_ATTENDANCE_PERCENTAGE = Integer.parseInt(editTextCourseMinimumAttendancePercentage.getText().toString());

            databaseSource.updateCourse(newCourse);
        }

        Toast.makeText(this, "Course details saved", Toast.LENGTH_SHORT).show();
    }

    public AlertDialog createAlertDialogColorPicker()
    {
        int buttonID[] = {R.id.alertDialog_color_picker_button_00,
                            R.id.alertDialog_color_picker_button_01,
                            R.id.alertDialog_color_picker_button_02,
                            R.id.alertDialog_color_picker_button_03,
                            R.id.alertDialog_color_picker_button_04,
                            R.id.alertDialog_color_picker_button_05,
                            R.id.alertDialog_color_picker_button_06,
                            R.id.alertDialog_color_picker_button_07,
                            R.id.alertDialog_color_picker_button_08,
                            R.id.alertDialog_color_picker_button_09,
                            R.id.alertDialog_color_picker_button_10,
                            R.id.alertDialog_color_picker_button_11};
        View view = inflater.inflate(R.layout.alert_dialog_color_picker, null, false);
        Button button[] = new Button[12];
        for(int i=0; i<12; i++)
        {
            button[i] = (Button)view.findViewById(buttonID[i]);
            button[i].setOnClickListener(this);
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view);
        builder.setTitle("Select color");

        return builder.create();
    }
}
