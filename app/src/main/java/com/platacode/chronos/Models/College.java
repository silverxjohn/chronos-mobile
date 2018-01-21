package com.platacode.chronos.Models;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.platacode.chronos.App;
import com.platacode.chronos.Interfaces.Collector;
import com.platacode.chronos.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class College extends Model<College> {
    private String college_id;
    private String name;
    private String initials;

    public College(String college_id, String name, String initials) {
        this.college_id = college_id;
        this.name = name;
        this.initials = initials;
    }

    public College() {
    }

    public String getCollege_id() {
        return college_id;
    }

    public void setCollege_id(String college_id) {
        this.college_id = college_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    @Override
    public String toString() {
        return "College{" +
                "college_id='" + college_id + '\'' +
                ", name='" + name + '\'' +
                ", initials='" + initials + '\'' +
                '}';
    }

    @Override
    String getDbNode() {
        return App.getContext().getString(R.string.node_colleges);
    }

    @Override
    String getIdentifier() {
        return getCollege_id();
    }

    @Override
    College parseSnapshot(DataSnapshot snapshot) {
        return snapshot.getValue(College.class);
    }

    @Override
    Map<String, Object> toMap() {
        Map<String, Object> college = new HashMap<>();

        college.put(App.getContext().getString(R.string.college_field_college_id), getCollege_id());
        college.put(App.getContext().getString(R.string.college_field_name), getName());
        college.put(App.getContext().getString(R.string.college_field_initials), getInitials());

        return college;
    }

    @Override
    public void get(final Collector collector) {
        FirebaseDatabase.getInstance().getReference()
                .child(getDbNode())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        List<College> colleges = new ArrayList<>();

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            for (DataSnapshot collegeSnapshot : snapshot.getChildren()) {
                                colleges.add(parseSnapshot(collegeSnapshot));
                            }
                        }

                        collector.collect(colleges);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }
}
