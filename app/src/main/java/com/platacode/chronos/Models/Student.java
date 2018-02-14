package com.platacode.chronos.Models;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.platacode.chronos.App;
import com.platacode.chronos.Interfaces.Collector;
import com.platacode.chronos.Interfaces.SingleCollector;
import com.platacode.chronos.R;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Student extends Model<Student> {
    private String student_id;
    private String id_number;
    private String first_name;
    private String middle_name;
    private String last_name;
    private String email;
    private String phone;

    public Student(String student_id, String id_number, String first_name, String middle_name, String last_name, String email, String phone) {
        this.student_id = student_id;
        this.id_number = id_number;
        this.first_name = first_name;
        this.middle_name = middle_name;
        this.last_name = last_name;
        this.email = email;
        this.phone = phone;
    }

    public Student() {
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getId_number() {
        return id_number;
    }

    public void setId_number(String id_number) {
        this.id_number = id_number;
    }

    @Override
    public String toString() {
        return "Student{" +
                "student_id='" + student_id + '\'' +
                ", id_number='" + id_number + '\'' +
                ", first_name='" + first_name + '\'' +
                ", middle_name='" + middle_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    @Override
    protected String getDbNode() {
        return App.getContext().getString(R.string.node_students);
    }

    @Override
    protected String getIdentifier() {
        return getStudent_id();
    }

    @Override
    Student parseSnapshot(DataSnapshot snapshot) {
        return snapshot.getValue(Student.class);
    }

    @Override
    Map<String, Object> toMap() {
        Map<String, Object> student = new HashMap<>();

        student.put(App.getContext().getString(R.string.student_field_email), getEmail());
        student.put(App.getContext().getString(R.string.student_field_first_name), getFirst_name());
        student.put(App.getContext().getString(R.string.student_field_middle_name), getMiddle_name());
        student.put(App.getContext().getString(R.string.student_field_last_name), getLast_name());
        student.put(App.getContext().getString(R.string.student_field_id_number), getId_number());
        student.put(App.getContext().getString(R.string.student_field_phone), getPhone());

        return student;
    }

    public void getClasses(final Collector collector) {

        FirebaseDatabase.getInstance().getReference()
                .child(getDbNode())
                .child(getIdentifier())
                .child(App.getContext().getString(R.string.node_classes))
                .child(String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 1))
                .orderByKey()
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        final List<Class.ClassCache> classes = new ArrayList<>();

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            for (DataSnapshot classSnapshot : snapshot.getChildren()) {
                                Class cls = classSnapshot.getValue(Class.class);
                                classes.add(new Class.ClassCache(cls, Time.getTimeString(Integer.parseInt(cls.getTime_id()))));

                                break;
                            }
                        }

                        collector.collect(classes);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }

    public void addClass(Class mClass) {
        FirebaseDatabase.getInstance().getReference()
                .child(getDbNode())
                .child(getIdentifier())
                .child(App.getContext().getString(R.string.node_classes))
                .child(mClass.getDay_id())
                .child(mClass.getTime_id())
                .child(mClass.getClass_id())
                .setValue(mClass);
    }

    public void removeClass(Class mClass) {
        FirebaseDatabase.getInstance().getReference()
                .child(getDbNode())
                .child(getIdentifier())
                .child(App.getContext().getString(R.string.node_classes))
                .child(mClass.getDay_id())
                .child(mClass.getTime_id())
                .child(mClass.getClass_id())
                .removeValue();
    }
}
