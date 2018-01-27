package com.platacode.chronos;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.platacode.chronos.Adapters.CardMapAdapter;
import com.platacode.chronos.Interfaces.Collector;
import com.platacode.chronos.Models.TimeLog;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends Fragment {


    public HistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_history, container, false);

        new TimeLog(FirebaseAuth.getInstance().getCurrentUser().getUid()).get(new Collector<TimeLog>() {
            @Override
            public void collect(List<TimeLog> items) {
                CardMapAdapter adapter = new CardMapAdapter(items);
                recyclerView.setAdapter(adapter);
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        return recyclerView;
    }

}
