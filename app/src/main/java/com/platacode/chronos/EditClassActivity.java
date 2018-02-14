package com.platacode.chronos;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.platacode.chronos.Adapters.StudentAdapter;
import com.platacode.chronos.Adapters.StudentRecyclerAdapter;
import com.platacode.chronos.Interfaces.Collector;
import com.platacode.chronos.Interfaces.SingleCollector;
import com.platacode.chronos.Models.Class;
import com.platacode.chronos.Models.Student;
import com.platacode.chronos.Models.Subject;

import java.util.List;

public class EditClassActivity extends AppCompatActivity {

    public static final String EXTRA_CLASS_ID = "class_id";
    private Class mClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_class);

        initializeComponents();
        getClassData();
        setupFab();
    }

    private void initializeComponents() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void getClassData() {
        Intent intent = getIntent();
        String classId = intent.getStringExtra(EXTRA_CLASS_ID);

        new Class().find(classId, new SingleCollector<Class>() {
            @Override
            public void collect(Class classItem) {
                mClass = classItem;

                mClass.getSubject(new SingleCollector<Subject>() {
                    @Override
                    public void collect(Subject subject) {
                        EditClassActivity.this.getSupportActionBar().setTitle(subject.getName());
                    }
                });

                renderClassData();
            }
        });
    }

    private void renderClassData() {
        mClass.getStudents(new Collector<Student>() {
            @Override
            public void collect(List<Student> students) {
                ListView studentRecycler = (ListView) findViewById(R.id.student_recyler);
//                studentRecycler.setLayoutManager(new LinearLayoutManager(EditClassActivity.this));

//                StudentRecyclerAdapter adapter = new StudentRecyclerAdapter(students, EditClassActivity.this);
                StudentAdapter adapter = new StudentAdapter(EditClassActivity.this, students);
                studentRecycler.setAdapter(adapter);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_class_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.edit_class_menu:
                editClass();
                return true;
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void editClass() {
        Toast.makeText(this, getString(R.string.class_updated), Toast.LENGTH_SHORT).show();
        finish();
    }

    private void setupFab() {
        FloatingActionButton fab = (FloatingActionButton) this.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditClassActivity.this, SelectStudentActivity.class);
                intent.putExtra(SelectStudentActivity.EXTRA_CLASS_ID, mClass.getClass_id());

                startActivity(intent);
            }
        });
    }
}
