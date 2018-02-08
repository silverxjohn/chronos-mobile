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

import com.platacode.chronos.Adapters.ClassAdapter;
import com.platacode.chronos.Interfaces.Collector;
import com.platacode.chronos.Models.Class;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ClassListFragment extends Fragment {


    public ClassListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_class_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        displayClassList();

        FloatingActionButton fab = (FloatingActionButton) getView().findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CreateClassActivity.class);

                startActivity(intent);
            }
        });
    }

    private void displayClassList() {
        final View v = getView();

        new Class().get(new Collector<Class>() {
            @Override
            public void collect(List<Class> classes) {
                ClassAdapter adapter = new ClassAdapter(v.getContext(), classes);

                ListView listView = (ListView) v.findViewById(R.id.listview);
                listView.setAdapter(adapter);
            }
        });
    }
}
