package com.platacode.chronos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.platacode.chronos.Adapters.StudentTimeAdapter;
import com.platacode.chronos.Interfaces.Collector;
import com.platacode.chronos.Interfaces.SingleCollector;
import com.platacode.chronos.Models.Class;
import com.platacode.chronos.Models.Student;
import com.platacode.chronos.Models.StudentTime;
import com.platacode.chronos.Models.StudentTimeWrapper;
import com.platacode.chronos.Models.Subject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;

public class TeacherClassActivity extends AppCompatActivity {

    public static final String EXTRA_CLASS_ID = "class_id";
    private Class mClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_class);

        initializeComponents();
        getClassData();
    }

    private void initializeComponents() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void getClassData() {
        Intent intent = getIntent();
        String classId = intent.getStringExtra(EXTRA_CLASS_ID);

        new Class().find(classId, new SingleCollector<Class>() {
            @Override
            public void collect(Class classItem) {
                mClass = classItem;

                createCalendarView();
            }
        });
    }

    private void createCalendarView() {
        /* starts before 1 month from now */
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.MONTH, -1);

        /* ends after 1 month from now */
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 1);

        FrameLayout v = (FrameLayout) findViewById(R.id.content_frame);

        HorizontalCalendar horizontalCalendar = new HorizontalCalendar.Builder(this, R.id.calendarView)
                .range(startDate, endDate)
                .datesNumberOnScreen(5)
                .build();

        getStudentList(Calendar.getInstance());

        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Calendar date, int position) {
                getStudentList(date);
            }
        });
    }

    private void getStudentList(Calendar date) {
        SimpleDateFormat format = new SimpleDateFormat("MM-d-YYYY");
        String formattedDate = format.format(date.getTime());

        FirebaseDatabase.getInstance().getReference()
                .child(getString(R.string.node_classes))
                .child(mClass.getClass_id())
                .child(getString(R.string.node_timelogs))
                .child(formattedDate)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        final List<StudentTime> studentTimes = new ArrayList<>();
                        final List<StudentTimeWrapper> wrapped = new ArrayList<>();

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            StudentTime studentTime = snapshot.getValue(StudentTime.class);

                            studentTimes.add(studentTime);
                        }

                        mClass.getStudents(new Collector<Student>() {
                            @Override
                            public void collect(List<Student> students) {
                                for (Student student : students) {
                                    boolean hasFound = false;

                                    for (StudentTime studentTime : studentTimes) {
                                        if (studentTime.getStudent_id().equals(student.getStudent_id())) {
                                            wrapped.add(new StudentTimeWrapper(student, studentTime.getTime(), true));
                                            hasFound = true;

                                            break;
                                        }
                                    }

                                    if (!hasFound)
                                        wrapped.add(new StudentTimeWrapper(student, "", false));
                                }

                                StudentTimeAdapter adapter = new StudentTimeAdapter(wrapped);

                                RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recylerView);
                                recyclerView.setLayoutManager(new LinearLayoutManager(TeacherClassActivity.this));
                                recyclerView.setAdapter(adapter);
                            }
                        });
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }
}
