package com.platacode.chronos;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.platacode.chronos.Adapters.TeacherAdapter;
import com.platacode.chronos.Models.Teacher;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TeacherListFragment extends Fragment {


    public TeacherListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_teacher_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        displayTeacherList();

        FloatingActionButton fab = (FloatingActionButton) getView().findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CreateTeacherActivity.class);

                startActivity(intent);
            }
        });
    }

    private void displayTeacherList() {
        final View v = getView();

        new Teacher().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Teacher> teachers = new ArrayList<>();

                for (DataSnapshot teacherSnapshop : dataSnapshot.getChildren()) {
                    Teacher teacher = teacherSnapshop.getValue(Teacher.class);

                    teachers.add(teacher);
                }

                ListView listView = (ListView) v.findViewById(R.id.listview);
                TeacherAdapter adapter = new TeacherAdapter(getContext(), teachers);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
