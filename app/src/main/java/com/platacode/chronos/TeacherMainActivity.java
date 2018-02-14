package com.platacode.chronos;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.platacode.chronos.Adapters.CardClassAdapter;
import com.platacode.chronos.Interfaces.Collector;
import com.platacode.chronos.Interfaces.SingleCollector;
import com.platacode.chronos.Models.Class;
import com.platacode.chronos.Models.Role;
import com.platacode.chronos.Models.Teacher;

import java.util.List;

public class TeacherMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_main);

        initializeComponents();

        final RecyclerView recyclerView = findViewById(R.id.recylerView);

        new Teacher().find(FirebaseAuth.getInstance().getCurrentUser().getUid(), new SingleCollector<Teacher>() {
            @Override
            public void collect(Teacher teacher) {
                teacher.getClasses(new Collector<Class>() {
                    @Override
                    public void collect(List<Class> classes) {
                        CardClassAdapter adapter = new CardClassAdapter(TeacherMainActivity.this, classes);

                        recyclerView.setLayoutManager(new LinearLayoutManager(TeacherMainActivity.this));
                        recyclerView.setAdapter(adapter);
                    }
                });
            }
        });
    }

    private void initializeComponents() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.logout_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout_menu:
                FirebaseAuth.getInstance().signOut();
                Role.getRoleInstance().resetRole();

                Intent intent = new Intent(TeacherMainActivity.this, LoginActivity.class);
                startActivity(intent);

                finish();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
