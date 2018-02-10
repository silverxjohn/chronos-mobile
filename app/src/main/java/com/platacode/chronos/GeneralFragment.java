package com.platacode.chronos;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.platacode.chronos.Adapters.ClassStepperAdapter;
import com.platacode.chronos.Interfaces.Collector;
import com.platacode.chronos.Interfaces.SingleCollector;
import com.platacode.chronos.Models.Class;
import com.platacode.chronos.Models.Student;

import java.util.List;

import moe.feng.common.stepperview.VerticalStepperView;


/**
 * A simple {@link Fragment} subclass.
 */
public class GeneralFragment extends Fragment {

    private Context mContext;


    public GeneralFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_general, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final VerticalStepperView stepperView = (VerticalStepperView) getView().findViewById(R.id.classStepperView);

        new Student().find(FirebaseAuth.getInstance().getCurrentUser().getUid(), new SingleCollector<Student>() {
            @Override
            public void collect(Student student) {
                student.getClasses(new Collector<Class.ClassCache>() {
                    @Override
                    public void collect(List<Class.ClassCache> classes) {
                        stepperView.setStepperAdapter(new ClassStepperAdapter(classes));
                    }
                });
            }
        });
    }
}
