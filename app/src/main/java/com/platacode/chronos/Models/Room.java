package com.platacode.chronos.Models;

import com.platacode.chronos.App;
import com.platacode.chronos.R;

public class Room extends Model {
    private String room_id;
    private String number;
    private String college_id;

    public Room(String room_id, String number, String college_id) {
        this.room_id = room_id;
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
        return "Room{" +
                "room_id='" + room_id + '\'' +
                ", number='" + number + '\'' +
                ", college_id='" + college_id + '\'' +
                '}';
    }

    @Override
    String getDbNode() {
        return App.getContext().getString(R.string.node_rooms);
    }

    @Override
    String getIdentifier() {
        return getRoom_id();
    }
}
