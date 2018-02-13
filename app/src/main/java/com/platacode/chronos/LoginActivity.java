package com.platacode.chronos;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.platacode.chronos.Models.Role;
import com.platacode.chronos.Models.UserRole;
import com.platacode.chronos.Utils.Mac;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        FirebaseAuth.getInstance().signOut();

        Button btnSignIn = findViewById(R.id.btnSignIn);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText username = (EditText) findViewById(R.id.txtIdNumber);
                EditText password = (EditText) findViewById(R.id.txtPassword);

                FirebaseAuth.getInstance().signInWithEmailAndPassword(
                    username.getText().toString(),
                    password.getText().toString()
                ).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
//                            checkMacAddress();
                            getUserRole();
                        } else {
                            Toast.makeText(LoginActivity.this, "Incorrect credentials", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    private void checkMacAddress() {

        FirebaseDatabase.getInstance().getReference().child("mac_addresses").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild(Mac.get())) {
                    Toast.makeText(LoginActivity.this, "Invalid Device", Toast.LENGTH_SHORT).show();

                    FirebaseAuth.getInstance().signOut();
                } else {
                    FirebaseDatabase.getInstance().getReference()
                            .child(getString(R.string.node_mac_addresses))
                            .child(Mac.get())
                            .setValue(FirebaseAuth.getInstance().getCurrentUser().getUid());

                    getUserRole();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void getUserRole() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

        ref.child(App.getContext().getString(R.string.node_roles)).orderByKey()
                .equalTo(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                            if (snapshot.getValue().toString().equals("1"))
                                Role.getRoleInstance().setRole(UserRole.student);
                            else if (snapshot.getValue().toString().equals("2"))
                                Role.getRoleInstance().setRole(UserRole.teacher);
                            else if (snapshot.getValue().toString().equals("3"))
                                Role.getRoleInstance().setRole(UserRole.admin);

                            break;
                        }

                        performRedirect();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }

    private void performRedirect() {
        if (Role.getRoleInstance().getAuthUserRole() == UserRole.teacher) {
            Intent intent = new Intent(LoginActivity.this, TeacherMainActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }

        finish();
    }
}
