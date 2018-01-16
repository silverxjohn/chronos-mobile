package com.platacode.chronos.Models;

public class Class {
    private String class_id;
    private String teacher_id;
    private String room_id;
    private String day_id;
    private String time_id;
    private String subject_id;

    public Class(String class_id, String teacher_id, String room_id, String day_id, String time_id, String subject_id) {
        this.class_id = class_id;
        this.teacher_id = teacher_id;
        this.room_id = room_id;
        this.day_id = day_id;
        this.time_id = time_id;
        this.subject_id = subject_id;
    }

    public Class() {
    }

    public String getClassId() {
        return class_id;
    }

    public void setClassId(String class_id) {
        this.class_id = class_id;
    }

    public String getTeacherId() {
        return teacher_id;
    }

    public void setTeacherId(String teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getRoomId() {
        return room_id;
    }

    public void setRoomId(String room_id) {
        this.room_id = room_id;
    }

    public String getDayId() {
        return day_id;
    }

    public void setDayId(String day_id) {
        this.day_id = day_id;
    }

    public String getTimeId() {
        return time_id;
    }

    public void setTimeId(String time_id) {
        this.time_id = time_id;
    }

    public String getSubjectId() {
        return subject_id;
    }

    public void setSubjectId(String subject_id) {
        this.subject_id = subject_id;
    }

    @Override
    public String toString() {
        return "Class{" +
                "class_id='" + class_id + '\'' +
                ", teacher_id='" + teacher_id + '\'' +
                ", room_id='" + room_id + '\'' +
                ", day_id='" + day_id + '\'' +
                ", time_id='" + time_id + '\'' +
                ", subject_id='" + subject_id + '\'' +
                '}';
    }
}
