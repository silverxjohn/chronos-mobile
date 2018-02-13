package com.platacode.chronos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.platacode.chronos.Adapters.StudentSelectionAdapter;
import com.platacode.chronos.Interfaces.Collector;
import com.platacode.chronos.Interfaces.SingleCollector;
import com.platacode.chronos.Models.Class;
import com.platacode.chronos.Models.Student;
import com.platacode.chronos.Models.StudentWrapper;

import java.util.ArrayList;
import java.util.List;

public class SelectStudentActivity extends AppCompatActivity {

    public static final String EXTRA_CLASS_ID = "class_id";
    private Class mClass;
    private List<Student> students;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_student);

        String classId = getIntent().getStringExtra(EXTRA_CLASS_ID);
        new Class().find(classId, new SingleCollector<Class>() {
            @Override
            public void collect(Class item) {
                mClass = item;

                mClass.getStudents(new Collector<Student>() {
                    @Override
                    public void collect(List<Student> items) {
                        students = items;
                        setUpListView();
                    }
                });
            }
        });
    }

    private void setUpListView() {
        final ListView listView = (ListView) findViewById(R.id.recylerView);
        final List<StudentWrapper> wrappedStudents = new ArrayList<>();

        new Student().get(new Collector<Student>() {
            @Override
            public void collect(List<Student> mStudents) {
                wrappedStudents.clear();

                for (Student student : mStudents) {
                    wrappedStudents.add(new StudentWrapper(student, isSelectedItem(student)));
                }

                StudentSelectionAdapter adapter = new StudentSelectionAdapter(wrappedStudents, SelectStudentActivity.this, mClass);
                listView.setAdapter(adapter);
            }
        });
    }

    private boolean isSelectedItem(Student student) {
        for (Student studentNeedle : students)
            return studentNeedle.getStudent_id().equals(student.getStudent_id());
        return false;
    }
}
