package com.platacode.chronos;

import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.IsoDep;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.platacode.chronos.Adapters.SectionsPagerAdapter;
import com.platacode.chronos.IsoDep.Tranceiver;
import com.platacode.chronos.Models.Role;
import com.platacode.chronos.Models.Student;
import com.platacode.chronos.Models.Teacher;
import com.platacode.chronos.Models.UserRole;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, NfcAdapter.ReaderCallback, Tranceiver.OnMessageReceived {

    private boolean isStudent;
    private NfcAdapter nfcAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Role.getRoleInstance().getAuthUserRole() == UserRole.admin) {
            isStudent = false;
            setContentView(R.layout.activity_main);
        } else if (Role.getRoleInstance().getAuthUserRole() == UserRole.student) {
            setContentView(R.layout.student_activity_main);
            isStudent = true;
        }

        nfcAdapter = NfcAdapter.getDefaultAdapter(this);

        initializeComponents();
    }

    @Override
    public void onResume() {
        super.onResume();

        if (this.isStudent)
            nfcAdapter.disableReaderMode(this);
    }

    @Override
    public void onPause() {
        super.onPause();

        if (this.isStudent)
            nfcAdapter.disableReaderMode(this);
    }

    private void initializeComponents() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (Role.getRoleInstance().getAuthUserRole() == UserRole.admin) {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.nav_open_drawer, R.string.nav_close_drawer);
            drawer.addDrawerListener(toggle);
            toggle.syncState();

            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);

            Fragment fragment = new HistoryFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.content_frame, fragment, "general_fragment");
            transaction.commit();

            setUserDetailsInNavigationDrawer();
        } else if (Role.getRoleInstance().getAuthUserRole() == UserRole.student) {
            SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager(), this);
            ViewPager pager = (ViewPager) findViewById(R.id.pager);
            pager.setAdapter(adapter);

            TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
            tabs.setupWithViewPager(pager);
        }
    }

    private void setUserDetailsInNavigationDrawer() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

        ref.child(App.getContext().getString(R.string.node_teachers))
                .orderByKey()
                .equalTo(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Teacher teacher = snapshot.getValue(Teacher.class);

//                            TextView authName = (TextView) findViewById(R.id.authName);
//                            authName.setText(teacher.getFirst_name() + " " + teacher.getLast_name());
//
//                            TextView authNumber = (TextView) findViewById(R.id.authNumber);
//                            authNumber.setText(teacher.getEmail());

                            break;
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        Fragment fragment = null;
        Intent intent = null;
        String title = "";

        switch (id) {
            case R.id.nav_home:
                title = "Chronos";
                fragment = new HomeFragment();
                break;
            case R.id.nav_teachers:
                title = "Teachers";
                fragment = new TeacherListFragment();
                break;
            case R.id.nav_students:
                title = "Students";
                fragment = new StudentListFragment();
                break;
            case R.id.nav_classes:
                title = "Classes";
                fragment = new ClassListFragment();
                break;
            case R.id.nav_subjects:
                title = "Subjects";
                fragment = new SubjectListFragment();
                break;
            case R.id.nav_rooms:
                title = "Rooms";
                fragment = new RoomListFragment();
                break;
            default:
                title = "Chronos";
                fragment = new GeneralFragment();
                break;
        }

        if (fragment != null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.content_frame, fragment);
            transaction.commit();
        } else {
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        getSupportActionBar().setTitle(title);

        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawerLayout == null) {
            super.onBackPressed();
            return;
        }

        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }

    @Override
    public void onTagDiscovered(Tag tag) {
        IsoDep isoDep = IsoDep.get(tag);
        Tranceiver tranceiver = new Tranceiver(isoDep, this);
        Thread thread = new Thread(tranceiver);
        thread.start();
    }

    @Override
    public void onMessage(byte[] message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
            }
        });
    }

    @Override
    public void onError(Exception exception) {
        onMessage(exception.getMessage().getBytes());
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

                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);

                finish();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
