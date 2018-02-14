package com.platacode.chronos;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.platacode.chronos.Models.Role;
import com.platacode.chronos.Models.Student;
import com.platacode.chronos.Models.UserRole;

public class CreateStudentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_student);
        initializeComponents();
    }

    private void initializeComponents() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
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
                createStudent();
                return true;
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    
    private void createStudent() {
        final EditText email = (EditText) findViewById(R.id.txtEmail);
        EditText password = (EditText) findViewById(R.id.txtPassword);
        final EditText firstName = (EditText) findViewById(R.id.txtFirstName);
        final EditText middleName = (EditText) findViewById(R.id.txtMiddleName);
        final EditText lastName = (EditText) findViewById(R.id.txtLastName);
        final EditText phone = (EditText) findViewById(R.id.txtPhone);
        final EditText idNumber = (EditText) findViewById(R.id.txtIdNumber);

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setDatabaseUrl(App.getContext().getString(R.string.firebase_db_url))
                .setApiKey(App.getContext().getString(R.string.firebase_api_key))
                .setApplicationId(App.getContext().getString(R.string.firebase_api_id))
                .build();

        FirebaseApp app = FirebaseApp.initializeApp(CreateStudentActivity.this, options, "Chronos");
        final FirebaseAuth auth = FirebaseAuth.getInstance(app);

        auth.createUserWithEmailAndPassword(
                email.getText().toString(),
                password.getText().toString()
        ).addOnCompleteListener(CreateStudentActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Student student = new Student(
                        auth.getCurrentUser().getUid(),
                        idNumber.getText().toString(),
                        firstName.getText().toString(),
                        middleName.getText().toString(),
                        lastName.getText().toString(),
                        email.getText().toString(),
                        phone.getText().toString()
                );

                student.create();

                Role.getRoleInstance().createRole(student);

                auth.signOut();

                Toast.makeText(CreateStudentActivity.this, R.string.student_created, Toast.LENGTH_SHORT).show();

                finish();
            }
        });
    }
}
