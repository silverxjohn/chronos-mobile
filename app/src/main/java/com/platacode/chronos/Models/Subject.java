package com.platacode.chronos.Models;

import com.google.firebase.database.DataSnapshot;
import com.platacode.chronos.App;
import com.platacode.chronos.R;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Subject extends Model<Subject> {
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

    public Subject(String name, String description, String units) {
        this.subject_id = UUID.randomUUID().toString().replace("-", "").substring(0, 27);
        this.name = name;
        this.description = description;
        this.units = units;
    }

    public Subject() {
    }

    public String getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(String subject_id) {
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
        return getName();
    }

    @Override
    String getDbNode() {
        return App.getContext().getString(R.string.node_subjects);
    }

    @Override
    String getIdentifier() {
        return getSubject_id();
    }

    @Override
    Subject parseSnapshot(DataSnapshot snapshot) {
        return snapshot.getValue(Subject.class);
    }

    @Override
    Map<String, Object> toMap() {
        Map<String, Object> subject = new HashMap<>();

        subject.put(App.getContext().getString(R.string.subject_field_subject_id), getSubject_id());
        subject.put(App.getContext().getString(R.string.subject_field_name), getName());
        subject.put(App.getContext().getString(R.string.subject_field_description), getDescription());
        subject.put(App.getContext().getString(R.string.subject_field_units), getUnits());

        return subject;
    }
}
