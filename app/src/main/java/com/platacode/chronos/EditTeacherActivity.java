package com.platacode.chronos;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.platacode.chronos.Interfaces.Collector;
import com.platacode.chronos.Models.Class;
import com.platacode.chronos.Models.Teacher;

import java.util.List;

public class EditTeacherActivity extends AppCompatActivity {

    public static final String EXTRA_TEACHER_ID = "teacher_id";
    private Teacher teacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_teacher);

        initializeComponents();
        getUserData();
    }

    private void initializeComponents() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void getUserData() {
        Intent intent = getIntent();
        final String teacherId = intent.getStringExtra(EXTRA_TEACHER_ID);
        FirebaseDatabase.getInstance().getReference()
                .child(getString(R.string.node_teachers))
                .child(teacherId)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        teacher = dataSnapshot.getValue(Teacher.class);

                        renderTeacherData();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }

    private void renderTeacherData() {
        final EditText email = (EditText) findViewById(R.id.txtEmail);
        email.setText(teacher.getEmail());

        final EditText firstName = (EditText) findViewById(R.id.txtFirstName);
        firstName.setText(teacher.getFirst_name());

        final EditText lastName = (EditText) findViewById(R.id.txtLastName);
        lastName.setText(teacher.getLast_name());

        final EditText phone = (EditText) findViewById(R.id.txtPhone);
        phone.setText(teacher.getPhone());
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
                editTeacher();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void editTeacher() {
        EditText email = (EditText) findViewById(R.id.txtEmail);
        teacher.setEmail(email.getText().toString());

        EditText firstName = (EditText) findViewById(R.id.txtFirstName);
        teacher.setFirst_name(firstName.getText().toString());

        EditText lastName = (EditText) findViewById(R.id.txtLastName);
        teacher.setLast_name(lastName.getText().toString());

        EditText phone = (EditText) findViewById(R.id.txtPhone);
        teacher.setPhone(phone.getText().toString());

        teacher.saveChanges();

        Toast.makeText(this, getString(R.string.teacher_updated), Toast.LENGTH_SHORT).show();
        finish();
    }
}
