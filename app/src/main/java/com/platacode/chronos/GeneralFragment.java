package com.platacode.chronos;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.vipulasri.timelineview.TimelineView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.platacode.chronos.Adapters.TimeLineAdapter;
import com.platacode.chronos.Models.OrderStatus;
import com.platacode.chronos.Models.Orientation;
import com.platacode.chronos.Models.TimeLineModel;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class GeneralFragment extends Fragment {

    private Orientation mOrientation;
    private Context mContext;


    public GeneralFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_general, container, false);


//        List<TimeLineModel> timelines = new ArrayList<TimeLineModel>();
//        timelines.add(new TimeLineModel("English", "9:00", OrderStatus.ACTIVE));
//        timelines.add(new TimeLineModel("Mathematics", "10:00", OrderStatus.ACTIVE));
//        timelines.add(new TimeLineModel("History", "11:00", OrderStatus.ACTIVE));
//
//        mOrientation = Orientation.VERTICAL;
//
//        RecyclerView recyclerView = (RecyclerView) getView().findViewById(R.id.time_marker);
//        recyclerView.setLayoutManager(getLinearLayoutManager());
//        recyclerView.setHasFixedSize(true);
//
//        TimeLineAdapter adapter = new TimeLineAdapter(timelines, Orientation.VERTICAL, true);
//        recyclerView.setAdapter(adapter);

        return view;
    }

    private LinearLayoutManager getLinearLayoutManager() {
        if (mOrientation == Orientation.HORIZONTAL) {
            return new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        } else {
            return new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        }
    }

}
