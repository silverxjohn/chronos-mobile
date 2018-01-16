package com.platacode.chronos.Models;

public class College {
    private String college_id;
    private String name;

    public College(String college_id, String name) {
        this.college_id = college_id;
        this.name = name;
    }

    public College() {
    }

    public String getCollegeId() {
        return college_id;
    }

    public void setCollegeId(String college_id) {
        this.college_id = college_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "College{" +
                "college_id='" + college_id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
