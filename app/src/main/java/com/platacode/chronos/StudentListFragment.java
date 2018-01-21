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
import com.platacode.chronos.Adapters.StudentAdapter;
import com.platacode.chronos.Models.Student;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class StudentListFragment extends Fragment {


    public StudentListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        displayStudentList();

        FloatingActionButton fab = (FloatingActionButton) getView().findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CreateStudentActivity.class);

                startActivity(intent);
            }
        });
    }

    private void displayStudentList() {
        final View v = getView();

        new Student().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Student> students = new ArrayList<Student>();

                for (DataSnapshot studentsSnapshot : dataSnapshot.getChildren()) {
                    Student student = studentsSnapshot.getValue(Student.class);

                    students.add(student);
                }

                ListView listView = (ListView) v.findViewById(R.id.listview);
                StudentAdapter adapter = new StudentAdapter(getContext(), students);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
