package com.platacode.chronos.Models;

public class Subject {
    private String subject_id;
    private String name;
    private String description;
    private String units;

    public Subject(String subject_id, String name, String description, String units) {
        this.subject_id = subject_id;
        this.name = name;
        this.description = description;
        this.units = units;
    }

    public Subject() {
    }

    public String getSubjectId() {
        return subject_id;
    }

    public void setSubjectId(String subject_id) {
        this.subject_id = subject_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "subject_id='" + subject_id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", units='" + units + '\'' +
                '}';
    }
}
