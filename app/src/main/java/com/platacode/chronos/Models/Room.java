package com.platacode.chronos.Models;

public class Room {
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

    public String getRoomId() {
        return room_id;
    }

    public void setRoomId(String room_id) {
        this.room_id = room_id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCollegeId() {
        return college_id;
    }

    public void setCollegeId(String college_id) {
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
}
