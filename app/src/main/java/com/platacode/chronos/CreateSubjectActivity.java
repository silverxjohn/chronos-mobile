package com.platacode.chronos;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.platacode.chronos.Models.Subject;

public class CreateSubjectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_subject);

        initializeComponents();
    }

    private void initializeComponents() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        SeekBar seekbar = (SeekBar) findViewById(R.id.unitSeekbar);
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                TextView txtUnits = (TextView) findViewById(R.id.txtUnits);
                txtUnits.setText("Units: " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
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
                createSubject();
                return true;
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void createSubject() {
        EditText txtSubjectName = (EditText) findViewById(R.id.txtSubjectName);
        EditText txtSubjectDescription = (EditText) findViewById(R.id.txtSubjectDescription);
        SeekBar unitSeekbar = (SeekBar) findViewById(R.id.unitSeekbar);

        Subject subject = new Subject(
            txtSubjectName.getText().toString(),
            txtSubjectDescription.getText().toString(),
            Integer.toString(unitSeekbar.getProgress())
        );

        subject.create();

        Toast.makeText(this, getString(R.string.subject_created), Toast.LENGTH_SHORT).show();

        finish();
    }
}
