package com.platacode.chronos;

import android.content.Intent;
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
import android.view.MenuItem;

import com.google.firebase.database.FirebaseDatabase;
import com.platacode.chronos.Adapters.SectionsPagerAdapter;
import com.platacode.chronos.Models.Role;
import com.platacode.chronos.Models.Student;
import com.platacode.chronos.Models.UserRole;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private UserRole role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Role.getRoleInstance().getAuthUserRole() == UserRole.teacher)
            setContentView(R.layout.activity_main);
        else if (Role.getRoleInstance().getAuthUserRole() == UserRole.student)
            setContentView(R.layout.student_activity_main);

        initializeComponents();
    }

    private void initializeComponents() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (Role.getRoleInstance().getAuthUserRole() == UserRole.teacher) {
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
        } else if (Role.getRoleInstance().getAuthUserRole() == UserRole.student) {
            SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager(), this);
            ViewPager pager = (ViewPager) findViewById(R.id.pager);
            pager.setAdapter(adapter);

            TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
            tabs.setupWithViewPager(pager);
        }
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
                fragment = new GeneralFragment();
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

//    @Override
//    public void onBackPressed() {
//        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawerLayout == null) {
//            super.onBackPressed();
//            return;
//        }
//
//        if (drawerLayout.isDrawerOpen(GravityCompat.START))
//            drawerLayout.closeDrawer(GravityCompat.START);
//        else {
//            if (getSupportFragmentManager().getBackStackEntryCount() > 0){
//                getSupportFragmentManager().popBackStack();
//            } else {
//                super.onBackPressed();
//            }
//        }
//    }
}
