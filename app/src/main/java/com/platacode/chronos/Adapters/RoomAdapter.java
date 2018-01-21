package com.platacode.chronos.Adapters;

import android.content.Context;
import android.widget.TextView;

import com.platacode.chronos.Interfaces.Collector;
import com.platacode.chronos.Models.College;
import com.platacode.chronos.Models.Room;

import java.util.List;
import java.util.Map;

public class RoomAdapter extends ListSwipableAdapter {

    private List<Room> rooms;
    private Map<Integer, College> colleges;

    public RoomAdapter(Context context, List<Room> rooms, Map<Integer, College> colleges) {
        super(context);
        this.rooms = rooms;
        this.colleges = colleges;

        new College().get(new Collector<College>() {
            @Override
            public void collect(List<College> items) {

            }
        });
    }

    @Override
    void setDisplayItem(int position, TextView heading, TextView subheading) {
        Room room = rooms.get(position);

        int id = Integer.valueOf(room.getCollege_id());
        College college = colleges.get(id);

        heading.setText(college.getInitials() + " " + room.getNumber());
        subheading.setText(college.getName());
    }

    @Override
    void onItemClicked(int position) {

    }

    @Override
    void onEditActionButtonClicked(int position) {

    }

    @Override
    void onDeleteActionButtonClicked(int position) {

    }

    @Override
    public int getCount() {
        return rooms.size();
    }

    @Override
    public Object getItem(int position) {
        return rooms.get(position);
    }
}
