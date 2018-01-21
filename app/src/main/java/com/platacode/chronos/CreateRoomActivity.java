package com.platacode.chronos;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.platacode.chronos.Interfaces.Collector;
import com.platacode.chronos.Models.College;
import com.platacode.chronos.Models.Room;

import java.util.List;

public class CreateRoomActivity extends AppCompatActivity {

    private List<College> colleges;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_room);

        initializeComponents();
        populateCollegeSpinner();
    }

    private void initializeComponents() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void populateCollegeSpinner() {
        new College().get(new Collector<College>() {
            @Override
            public void collect(List<College> items) {
                colleges = items;

                ArrayAdapter adapter = new ArrayAdapter(CreateRoomActivity.this, android.R.layout.simple_spinner_dropdown_item, colleges);

                Spinner collegeSpinner = (Spinner) findViewById(R.id.collegeSpinner);
                collegeSpinner.setAdapter(adapter);
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
                createRoom();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void createRoom() {
        EditText roomNumber = (EditText) findViewById(R.id.txtRoomNumber);

        Spinner collegeSpinner = (Spinner) findViewById(R.id.collegeSpinner);
        College college = (College) collegeSpinner.getSelectedItem();

        Room room = new Room(roomNumber.getText().toString(), college.getCollege_id());
        room.create();

        Toast.makeText(this, getString(R.string.room_created), Toast.LENGTH_SHORT).show();

        finish();
    }
}
