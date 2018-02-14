package com.platacode.chronos;

import android.content.Intent;
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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.platacode.chronos.Interfaces.Collector;
import com.platacode.chronos.Models.College;
import com.platacode.chronos.Models.Room;

import java.util.List;

public class EditRoomActivity extends AppCompatActivity {

    public static final String EXTRA_ROOM_ID = "room_id";
    private List<College> colleges;
    private Room room;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_room);

        initializeComponents();
        getRoomData();
    }

    private void initializeComponents() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void getRoomData() {
        String roomId = getIntent().getExtras().getString(EXTRA_ROOM_ID);
        FirebaseDatabase.getInstance().getReference()
                .child(App.getContext().getString(R.string.node_rooms))
                .child(roomId)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        room = dataSnapshot.getValue(Room.class);

                        EditText txtRoomNumber = (EditText) findViewById(R.id.txtRoomNumber);
                        txtRoomNumber.setText(room.getNumber());

                        populateCollegeSpinner();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }

    private void populateCollegeSpinner() {
        new College().get(new Collector<College>() {
            @Override
            public void collect(List<College> items) {
                colleges = items;

                ArrayAdapter adapter = new ArrayAdapter(EditRoomActivity.this, android.R.layout.simple_spinner_dropdown_item, colleges);

                Spinner collegeSpinner = (Spinner) findViewById(R.id.collegeSpinner);
                collegeSpinner.setAdapter(adapter);

                College selectedCollege = null;
                for (College college : colleges) {
                    if (college.getCollege_id().equals(room.getCollege_id()))
                        selectedCollege = college;
                }

                if (selectedCollege != null)
                    collegeSpinner.setSelection(colleges.indexOf(selectedCollege));
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
                editRoom();
                return true;
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void editRoom() {
        EditText txtRoomNumber = (EditText) findViewById(R.id.txtRoomNumber);
        room.setNumber(txtRoomNumber.getText().toString());

        Spinner collegeSpinner = (Spinner) findViewById(R.id.collegeSpinner);
        College college = (College) collegeSpinner.getSelectedItem();
        room.setCollege_id(college.getCollege_id());

        room.saveChanges();

        Toast.makeText(this, getString(R.string.room_updated), Toast.LENGTH_SHORT).show();

        finish();
    }
}
