package com.platacode.chronos;

import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.platacode.chronos.Adapters.CardClassAdapter;
import com.platacode.chronos.Interfaces.Collector;
import com.platacode.chronos.Models.Class;

import java.util.List;

public class TeacherMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_main);

        initializeComponents();

        final RecyclerView recyclerView = findViewById(R.id.recylerView);

        new Class().get(new Collector<Class>() {
            @Override
            public void collect(List<Class> classes) {
                CardClassAdapter adapter = new CardClassAdapter(TeacherMainActivity.this, classes);

                recyclerView.setLayoutManager(new LinearLayoutManager(TeacherMainActivity.this));
                recyclerView.setAdapter(adapter);
            }
        });
    }

    private void initializeComponents() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
}
