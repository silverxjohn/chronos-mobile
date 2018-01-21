package com.platacode.chronos.Models;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;
import com.platacode.chronos.App;
import com.platacode.chronos.Interfaces.Collector;
import com.platacode.chronos.R;

import java.util.HashMap;
import java.util.Map;

public class Parent extends Model<Parent> {
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

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
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

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
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

    @Override
    String getDbNode() {
        return App.getContext().getString(R.string.node_parents);
    }

    @Override
    String getIdentifier() {
        return getParent_id();
    }

    @Override
    Map<String, Object> toMap() {
        Map<String, Object> parent = new HashMap<>();

        parent.put(App.getContext().getString(R.string.parent_field_parent_id), getParent_id());
        parent.put(App.getContext().getString(R.string.parent_field_first_name), getFirst_name());
        parent.put(App.getContext().getString(R.string.parent_field_last_name), getLast_name());
        parent.put(App.getContext().getString(R.string.parent_field_phone), getPhone());
        parent.put(App.getContext().getString(R.string.parent_field_email), getEmail());
        parent.put(App.getContext().getString(R.string.parent_field_student_id), getStudent_id());

        return parent;
    }

    @Override
    public void create() {
        FirebaseDatabase.getInstance().getReference()
                .child(getDbNode())
                .child(getStudent_id())
                .child(getIdentifier())
                .setValue(this);
    }

    @Override
    public void saveChanges() {
        FirebaseDatabase.getInstance().getReference()
                .child(getDbNode())
                .child(getStudent_id())
                .child(getIdentifier())
                .updateChildren(toMap());
    }

    @Override
    public void get(Collector collector) {
//        super.get(collector);
    }

    @Override
    Parent parseSnapshot(DataSnapshot snapshot) {
        return snapshot.getValue(Parent.class);
    }
}
