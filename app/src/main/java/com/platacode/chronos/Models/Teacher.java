package com.platacode.chronos.Models;

import com.platacode.chronos.App;
import com.platacode.chronos.R;

public class Teacher extends Model {
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
        return "Teacher{" +
                "teacher_id='" + teacher_id + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    String getDbNode() {
        return App.getContext().getString(R.string.node_teachers);
    }

    @Override
    String getIdentifier() {
        return getTeacher_id();
    }
}
