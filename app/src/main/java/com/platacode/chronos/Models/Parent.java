package com.platacode.chronos.Models;

public class Parent {
    private String parent_id;
    private String first_name;
    private String last_name;
    private String phone;
    private String email;
    private String student_id;

    public Parent(String parent_id, String first_name, String last_name, String phone, String email, String student_id) {
        this.parent_id = parent_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone = phone;
        this.email = email;
        this.student_id = student_id;
    }

    public Parent() {
    }

    public String getParentId() {
        return parent_id;
    }

    public void setParentId(String parent_id) {
        this.parent_id = parent_id;
    }

    public String getFirstName() {
        return first_name;
    }

    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }

    public String getLastName() {
        return last_name;
    }

    public void setLastName(String last_name) {
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

    public String getStudentId() {
        return student_id;
    }

    public void setStudentId(String student_id) {
        this.student_id = student_id;
    }

    @Override
    public String toString() {
        return "Parent{" +
                "parent_id='" + parent_id + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", student_id='" + student_id + '\'' +
                '}';
    }
}
