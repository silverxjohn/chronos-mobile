package com.platacode.chronos;

import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
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
import com.platacode.chronos.Models.Teacher;
import com.platacode.chronos.Models.UserRole;

public class CreateTeacherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_teacher);
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
                createTeacher();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void createTeacher() {
        final EditText email = (EditText) findViewById(R.id.txtEmail);
        EditText password = (EditText) findViewById(R.id.txtPassword);
        final EditText firstName = (EditText) findViewById(R.id.txtFirstName);
        final EditText lastName = (EditText) findViewById(R.id.txtLastName);
        final EditText phone = (EditText) findViewById(R.id.txtPhone);

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setDatabaseUrl(App.getContext().getString(R.string.firebase_db_url))
                .setApiKey(App.getContext().getString(R.string.firebase_api_key))
                .setApplicationId(App.getContext().getString(R.string.firebase_api_id))
                .build();

        FirebaseApp app = FirebaseApp.initializeApp(CreateTeacherActivity.this, options, "Chronos");
        final FirebaseAuth auth = FirebaseAuth.getInstance(app);

        auth.createUserWithEmailAndPassword(
                email.getText().toString(),
                password.getText().toString()
        ).addOnCompleteListener(CreateTeacherActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Teacher teacher = new Teacher(
                        auth.getCurrentUser().getUid(),
                        firstName.getText().toString(),
                        lastName.getText().toString(),
                        phone.getText().toString(),
                        email.getText().toString()
                    );

                teacher.create();

                Role.getRoleInstance().createRole(teacher);

                auth.signOut();

                Toast.makeText(CreateTeacherActivity.this, R.string.teacher_created, Toast.LENGTH_SHORT).show();

                finish();
            }
        });
    }
}
