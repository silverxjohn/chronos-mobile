package com.platacode.chronos.Models;

import com.google.firebase.database.DataSnapshot;
import com.platacode.chronos.App;
import com.platacode.chronos.R;

import java.util.HashMap;
import java.util.Map;

public class Teacher extends Model<Teacher> {
    private String teacher_id;
    private String first_name;
    private String last_name;
    private String phone;
    private String email;

    public Teacher(String teacher_id, String first_name, String last_name, String phone, String email) {
        this.teacher_id = teacher_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone = phone;
        this.email = email;
    }

    public Teacher() {
    }

    public String getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(String teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return getFirst_name() + " " + getLast_name();
    }

    @Override
    String getDbNode() {
        return App.getContext().getString(R.string.node_teachers);
    }

    @Override
    String getIdentifier() {
        return getTeacher_id();
    }

    @Override
    Teacher parseSnapshot(DataSnapshot snapshot) {
        return snapshot.getValue(Teacher.class);
    }

    @Override
    Map<String, Object> toMap() {
        Map<String, Object> teacher = new HashMap<>();

        teacher.put(App.getContext().getString(R.string.teacher_field_teacher_id), getTeacher_id());
        teacher.put(App.getContext().getString(R.string.teacher_field_first_name), getFirst_name());
        teacher.put(App.getContext().getString(R.string.teacher_field_last_name), getLast_name());
        teacher.put(App.getContext().getString(R.string.teacher_field_phone), getPhone());
        teacher.put(App.getContext().getString(R.string.teacher_field_email), getEmail());

        return teacher;
    }
}
