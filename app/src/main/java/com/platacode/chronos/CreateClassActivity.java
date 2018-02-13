package com.platacode.chronos;

import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;
import com.platacode.chronos.Interfaces.Collector;
import com.platacode.chronos.Models.Class;
import com.platacode.chronos.Models.Day;
import com.platacode.chronos.Models.Room;
import com.platacode.chronos.Models.Subject;
import com.platacode.chronos.Models.Teacher;

import java.util.List;
import java.util.stream.Collectors;

public class CreateClassActivity extends AppCompatActivity {

    private List<Subject> subjects;
    private List<Teacher> teachers;
    private List<Room> rooms;
    private List<Day> days;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_class);

        initializeComponents();
        initializeSpinnerData();
    }

    private void initializeComponents() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        final Button btn = (Button) findViewById(R.id.btnSetTime);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();

                DialogFragment fragment = new TimePickerFragment();
                fragment.setArguments(bundle);
            }
        });
    }

    private void initializeSpinnerData() {
        getSubjects();
        getTeachers();
        getRooms();
        getDays();
    }

    private void getSubjects() {
        new Subject().get(new Collector<Subject>() {
            @Override
            public void collect(List<Subject> items) {
                subjects = items;

                ArrayAdapter adapter = new ArrayAdapter(CreateClassActivity.this, android.R.layout.simple_spinner_dropdown_item, subjects);

                Spinner subjectSpinner = (Spinner) findViewById(R.id.subjectSpinner);
                subjectSpinner.setAdapter(adapter);
            }
        });
    }

    private void getTeachers() {
        new Teacher().get(new Collector<Teacher>() {
            @Override
            public void collect(List<Teacher> items) {
                teachers = items;

                ArrayAdapter adapter = new ArrayAdapter(CreateClassActivity.this, android.R.layout.simple_spinner_dropdown_item, teachers);

                Spinner teacherSpinner = (Spinner) findViewById(R.id.teacherSpinner);
                teacherSpinner.setAdapter(adapter);
            }
        });
    }

    private void getRooms() {
        new Room().get(new Collector<Room>() {
            @Override
            public void collect(List<Room> items) {
                rooms = items;

                ArrayAdapter adapter = new ArrayAdapter(CreateClassActivity.this, android.R.layout.simple_spinner_dropdown_item, rooms);

                Spinner roomSpinner = (Spinner) findViewById(R.id.roomSpinner);
                roomSpinner.setAdapter(adapter);
            }
        });
    }

    public void getDays() {
        new Day().get(new Collector<Day>() {
            @Override
            public void collect(List<Day> items) {
                days = items;

                ArrayAdapter adapter = new ArrayAdapter(CreateClassActivity.this, android.R.layout.simple_spinner_dropdown_item, days);

                Spinner daySpinner = (Spinner) findViewById(R.id.daySpinner);
                daySpinner.setAdapter(adapter);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.create_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.create_menu:
                createClass();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void createClass() {
        Spinner subjectSpinner = (Spinner) findViewById(R.id.subjectSpinner);
        Subject subject = (Subject) subjectSpinner.getSelectedItem();

        Spinner roomSpinner = (Spinner) findViewById(R.id.roomSpinner);
        Room room = (Room) roomSpinner.getSelectedItem();

        Spinner teacherSpinner = (Spinner) findViewById(R.id.teacherSpinner);
        Teacher teacher = (Teacher) teacherSpinner.getSelectedItem();

        Spinner daySpinner = (Spinner) findViewById(R.id.daySpinner);
        Day day = (Day) daySpinner.getSelectedItem();

        Class c = new Class(
            teacher.getTeacher_id(),
            room.getRoom_id(),
            day.getDay_id(),
            "",
            subject.getSubject_id(),
            subject.getName()
        );

        c.create();

        FirebaseDatabase.getInstance().getReference()
                .child(App.getContext().getString(R.string.node_teachers))
                .child(teacher.getTeacher_id())
                .child(App.getContext().getString(R.string.node_classes))
                .child(c.getClass_id())
                .setValue(c);

        Toast.makeText(this, getString(R.string.class_created), Toast.LENGTH_SHORT).show();

        finish();
    }
}
