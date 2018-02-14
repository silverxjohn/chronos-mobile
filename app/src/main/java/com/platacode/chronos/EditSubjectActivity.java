package com.platacode.chronos;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.platacode.chronos.Models.Subject;

public class EditSubjectActivity extends AppCompatActivity {

    public final static String EXTRA_SUBJECT_ID = "subject_id";
    private Subject subject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_subject);

        initializeComponents();
        getSubjectData();
    }

    private void initializeComponents() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void getSubjectData() {
        String subjectId = getIntent().getExtras().getString(EXTRA_SUBJECT_ID);

        FirebaseDatabase.getInstance().getReference()
                .child(getString(R.string.node_subjects))
                .child(subjectId)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        subject = dataSnapshot.getValue(Subject.class);

                        renderSubjectData();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }

    private void renderSubjectData() {
        final EditText txtSubjectName = (EditText) findViewById(R.id.txtSubjectName);
        txtSubjectName.setText(subject.getName());

        final EditText txtSubjectDescription = (EditText) findViewById(R.id.txtSubjectDescription);
        txtSubjectDescription.setText(subject.getDescription());

        final SeekBar unitSeekbar = (SeekBar) findViewById(R.id.unitSeekbar);
        unitSeekbar.setProgress(Integer.valueOf(subject.getUnits()));
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
                editSubject();
                return true;
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void editSubject() {
        final EditText txtSubjectName = (EditText) findViewById(R.id.txtSubjectName);
        subject.setName(txtSubjectName.getText().toString());

        final EditText txtSubjectDescription = (EditText) findViewById(R.id.txtSubjectDescription);
        subject.setDescription(txtSubjectDescription.getText().toString());

        final SeekBar unitSeekbar = (SeekBar) findViewById(R.id.unitSeekbar);
        subject.setUnits(Integer.toString(unitSeekbar.getProgress()));

        subject.saveChanges();

        Toast.makeText(this, getString(R.string.subject_updated), Toast.LENGTH_SHORT).show();

        finish();
    }
}
