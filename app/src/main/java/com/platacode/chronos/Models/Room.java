package com.platacode.chronos.Models;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;
import com.platacode.chronos.App;
import com.platacode.chronos.Interfaces.SingleCollector;
import com.platacode.chronos.R;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Room extends Model<Room> {
    private String room_id;
    private String number;
    private String college_id;

    public Room(String room_id, String number, String college_id) {
        this.room_id = room_id;
        this.number = number;
        this.college_id = college_id;
    }

    public Room(String number, String college_id) {
        this.room_id = UUID.randomUUID().toString().replace("-", "").substring(0, 27);
        this.number = number;
        this.college_id = college_id;
    }

    public Room() {
    }

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCollege_id() {
        return college_id;
    }

    public void setCollege_id(String college_id) {
        this.college_id = college_id;
    }

    @Override
    public String toString() {
        return getNumber();
    }

    @Override
    String getDbNode() {
        return App.getContext().getString(R.string.node_rooms);
    }

    @Override
    String getIdentifier() {
        return getRoom_id();
    }

    @Override
    Room parseSnapshot(DataSnapshot snapshot) {
        return snapshot.getValue(Room.class);
    }

    @Override
    Map<String, Object> toMap() {
        Map<String, Object> room = new HashMap<>();

        room.put(App.getContext().getString(R.string.room_field_room_id), getRoom_id());
        room.put(App.getContext().getString(R.string.room_field_number), getNumber());
        room.put(App.getContext().getString(R.string.room_field_college_id), getCollege_id());

        return room;
    }

    public void getCollege(SingleCollector collector) {
        new College().find(getCollege_id(), collector);
    }
}
