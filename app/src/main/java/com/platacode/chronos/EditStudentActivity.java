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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.platacode.chronos.Models.Student;

import java.util.HashMap;
import java.util.Map;

public class EditStudentActivity extends AppCompatActivity {

    public static final String EXTRA_STUDENT_ID = "student_id";
    private Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);
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
        String studentId = intent.getStringExtra(EXTRA_STUDENT_ID);
        FirebaseDatabase.getInstance().getReference()
                .child(getString(R.string.node_students))
                .child(studentId)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                            student = dataSnapshot.getValue(Student.class);

                            renderStudentData();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }

    private void renderStudentData() {
        final EditText email = (EditText) findViewById(R.id.txtEmail);
        email.setText(student.getEmail());

        final EditText firstName = (EditText) findViewById(R.id.txtFirstName);
        firstName.setText(student.getFirst_name());

        final EditText middleName = (EditText) findViewById(R.id.txtMiddleName);
        middleName.setText(student.getMiddle_name());

        final EditText lastName = (EditText) findViewById(R.id.txtLastName);
        lastName.setText(student.getLast_name());

        final EditText phone = (EditText) findViewById(R.id.txtPhone);
        phone.setText(student.getPhone());

        final EditText idNumber = (EditText) findViewById(R.id.txtIdNumber);
        idNumber.setText(student.getId_number());
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
                editStudent();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void editStudent() {
        final EditText email = (EditText) findViewById(R.id.txtEmail);
        final EditText firstName = (EditText) findViewById(R.id.txtFirstName);
        final EditText middleName = (EditText) findViewById(R.id.txtMiddleName);
        final EditText lastName = (EditText) findViewById(R.id.txtLastName);
        final EditText phone = (EditText) findViewById(R.id.txtPhone);
        final EditText idNumber = (EditText) findViewById(R.id.txtIdNumber);

        Map<String, Object> updates = new HashMap<String, Object>();
        updates.put("email", email.getText().toString());
        updates.put("first_name", firstName.getText().toString());
        updates.put("middle_name", middleName.getText().toString());
        updates.put("last_name", lastName.getText().toString());
        updates.put("id_number", idNumber.getText().toString());
        updates.put("phone", phone.getText().toString());

        FirebaseDatabase.getInstance().getReference()
                .child(getString(R.string.node_students))
                .child(student.getStudent_id())
                .updateChildren(updates);

        Toast.makeText(this, getString(R.string.student_updated), Toast.LENGTH_SHORT).show();
        finish();
    }
}
