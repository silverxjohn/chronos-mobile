package com.platacode.chronos;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.platacode.chronos.Adapters.ClassStepperAdapter;

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

        stepperView.setStepperAdapter(new ClassStepperAdapter());
        stepperView.setActivatedColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
        stepperView.setNormalColor(ContextCompat.getColor(getContext(), R.color.colorGray));

        FloatingActionButton fab = (FloatingActionButton) getView().findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stepperView.nextStep();
            }
        });
    }
}
