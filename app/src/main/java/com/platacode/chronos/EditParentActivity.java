package com.platacode.chronos;

import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.platacode.chronos.Models.Parent;
import com.platacode.chronos.Models.Role;
import com.platacode.chronos.Models.Teacher;

public class EditParentActivity extends AppCompatActivity {

    public static final String EXTRA_STUDENT_ID = "student_id";
    private boolean hasParent;
    private Parent parent;
    private String studentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_parent);

        initializeComponents();
        getParentData();
    }

    private void initializeComponents() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void getParentData() {
        studentId = getIntent().getExtras().getString(EXTRA_STUDENT_ID);

        FirebaseDatabase.getInstance().getReference()
                .child(getString(R.string.node_parents))
                .child(studentId)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        hasParent = dataSnapshot.exists();

                        if (hasParent) {
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                parent = snapshot.getValue(Parent.class);

                                renderParentData();

                                break;
                            }
                        } else {
                            TextView txtViewPassword = (TextView) findViewById(R.id.txtViewPassword);
                            txtViewPassword.setVisibility(View.VISIBLE);

                            EditText txtPassword = (EditText) findViewById(R.id.txtPassword);
                            txtPassword.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }

    private void renderParentData() {
        final EditText email = (EditText) findViewById(R.id.txtEmail);
        email.setText(parent.getEmail());

        final EditText firstName = (EditText) findViewById(R.id.txtFirstName);
        firstName.setText(parent.getFirst_name());

        final EditText lastName = (EditText) findViewById(R.id.txtLastName);
        lastName.setText(parent.getLast_name());

        final EditText phone = (EditText) findViewById(R.id.txtPhone);
        phone.setText(parent.getPhone());
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
                if (hasParent)
                    editParent();
                else
                    createParent();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void createParent() {
        final EditText email = (EditText) findViewById(R.id.txtEmail);
        final EditText firstName = (EditText) findViewById(R.id.txtFirstName);
        final EditText lastName = (EditText) findViewById(R.id.txtLastName);
        final EditText phone = (EditText) findViewById(R.id.txtPhone);
        final EditText password = (EditText) findViewById(R.id.txtPassword);

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setDatabaseUrl(App.getContext().getString(R.string.firebase_db_url))
                .setApiKey(App.getContext().getString(R.string.firebase_api_key))
                .setApplicationId(App.getContext().getString(R.string.firebase_api_id))
                .build();

        FirebaseApp app = FirebaseApp.initializeApp(EditParentActivity.this, options, "Chronos");
        final FirebaseAuth auth = FirebaseAuth.getInstance(app);

        auth.createUserWithEmailAndPassword(
                email.getText().toString(),
                password.getText().toString()
        ).addOnCompleteListener(EditParentActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Parent parent = new Parent(
                        auth.getCurrentUser().getUid(),
                        firstName.getText().toString(),
                        lastName.getText().toString(),
                        phone.getText().toString(),
                        email.getText().toString(),
                        studentId
                );

                parent.create();

                Role.getRoleInstance().createRole(parent);

                auth.signOut();

                Toast.makeText(EditParentActivity.this, R.string.parent_created, Toast.LENGTH_SHORT).show();

                finish();
            }
        });
    }

    private void editParent() {
        final EditText email = (EditText) findViewById(R.id.txtEmail);
        parent.setEmail(email.getText().toString());

        final EditText firstName = (EditText) findViewById(R.id.txtFirstName);
        parent.setFirst_name(firstName.getText().toString());

        final EditText lastName = (EditText) findViewById(R.id.txtLastName);
        parent.setLast_name(lastName.getText().toString());

        final EditText phone = (EditText) findViewById(R.id.txtPhone);
        parent.setPhone(phone.getText().toString());

        parent.setStudent_id(studentId);

        parent.saveChanges();

        Toast.makeText(this, getString(R.string.parent_updated), Toast.LENGTH_SHORT).show();
        finish();
    }
}
