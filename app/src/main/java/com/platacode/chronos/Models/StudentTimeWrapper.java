package com.platacode.chronos.Models;

public class StudentTimeWrapper {
    private Student student;
    private String time;
    private boolean isPresent;

    public StudentTimeWrapper(Student student, String time, boolean isPresent) {
        this.student = student;
        this.time = time;
        this.isPresent = isPresent;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isPresent() {
        return isPresent;
    }

    public void setPresent(boolean present) {
        isPresent = present;
    }
}
