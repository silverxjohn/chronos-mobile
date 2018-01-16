package com.platacode.chronos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btnSignIn = findViewById(R.id.btnSignIn);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView username = (TextView) findViewById(R.id.txtIdNumber);

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);

                if (username.getText().toString().equals("teacher")) {
                    intent.putExtra(MainActivity.EXTRA_ACCESS_LEVEL, 2);
                } else {
                    // student
                    intent.putExtra(MainActivity.EXTRA_ACCESS_LEVEL, 1);
                }

                startActivity(intent);
            }
        });
    }
}
