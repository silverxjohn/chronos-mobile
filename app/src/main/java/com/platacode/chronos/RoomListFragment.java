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

import com.platacode.chronos.Adapters.RoomAdapter;
import com.platacode.chronos.Interfaces.Collector;
import com.platacode.chronos.Models.College;
import com.platacode.chronos.Models.Room;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class RoomListFragment extends Fragment {


    public RoomListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_room_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        displayRoomList();

        FloatingActionButton fab = (FloatingActionButton) getView().findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CreateTeacherActivity.class);

                startActivity(intent);
            }
        });
    }

    private void displayRoomList() {
        final View v = getView();

        new Room().get(new Collector<Room>() {

            @Override
            public void collect(final List<Room> rooms) {
                new College().get(new Collector<College>() {
                    @Override
                    public void collect(List<College> items) {
                        Map<Integer, College> colleges = new HashMap<>();

                        for (College college : items) {
                            colleges.put(Integer.valueOf(college.getCollege_id()), college);
                        }

                        RoomAdapter adapter = new RoomAdapter(getView().getContext(), rooms, colleges);

                        ListView listView = (ListView) v.findViewById(R.id.listview);
                        listView.setAdapter(adapter);
                    }
                });
            }
        });
    }
}
