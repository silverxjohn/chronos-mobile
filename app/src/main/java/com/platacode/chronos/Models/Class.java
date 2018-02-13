package com.platacode.chronos.Models;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.platacode.chronos.App;
import com.platacode.chronos.Interfaces.Collector;
import com.platacode.chronos.Interfaces.SingleCollector;
import com.platacode.chronos.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Class extends Model<Class> {
    private String class_id;
    private String teacher_id;
    private String room_id;
    private String day_id;
    private String time_id;
    private String subject_id;
    private String subject_name;

    public Class(String class_id, String teacher_id, String room_id, String day_id, String time_id, String subject_id, String subject_name) {
        this.class_id = class_id;
        this.teacher_id = teacher_id;
        this.room_id = room_id;
        this.day_id = day_id;
        this.time_id = time_id;
        this.subject_id = subject_id;
        this.subject_name = subject_name;
    }

    public Class(String teacher_id, String room_id, String day_id, String time_id, String subject_id, String subject_name) {
        this.class_id = UUID.randomUUID().toString().replace("-", "").substring(0, 27);
        this.teacher_id = teacher_id;
        this.room_id = room_id;
        this.day_id = day_id;
        this.time_id = time_id;
        this.subject_id = subject_id;
        this.subject_name = subject_name;
    }

    public Class() {
    }

    public String getClass_id() {
        return class_id;
    }

    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }

    public String getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(String teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }

    public String getDay_id() {
        return day_id;
    }

    public void setDay_id(String day_id) {
        this.day_id = day_id;
    }

    public String getTime_id() {
        return time_id;
    }

    public void setTime_id(String time_id) {
        this.time_id = time_id;
    }

    public String getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(String subject_id) {
        this.subject_id = subject_id;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    @Override
    public String toString() {
        return "Class{" +
                "class_id='" + class_id + '\'' +
                ", teacher_id='" + teacher_id + '\'' +
                ", room_id='" + room_id + '\'' +
                ", day_id='" + day_id + '\'' +
                ", time_id='" + time_id + '\'' +
                ", subject_id='" + subject_id + '\'' +
                '}';
    }

    @Override
    String getDbNode() {
        return App.getContext().getString(R.string.node_classes);
    }

    @Override
    String getIdentifier() {
        return getClass_id();
    }

    @Override
    Class parseSnapshot(DataSnapshot snapshot) {
        return snapshot.getValue(Class.class);
    }

    @Override
    Map<String, Object> toMap() {
        Map<String, Object> classMap = new HashMap<>();

        classMap.put(App.getContext().getString(R.string.class_field_class_id), getClass_id());
        classMap.put(App.getContext().getString(R.string.class_field_teacher_id), getTeacher_id());
        classMap.put(App.getContext().getString(R.string.class_field_room_id), getRoom_id());
        classMap.put(App.getContext().getString(R.string.class_field_day_id), getDay_id());
        classMap.put(App.getContext().getString(R.string.class_field_time_id), getTime_id());
        classMap.put(App.getContext().getString(R.string.class_field_subject_id), getSubject_id());

        return classMap;
    }

    public void getTeacher(SingleCollector collector) {
        new Teacher().find(getTeacher_id(), collector);
    }

    public void getSubject(SingleCollector collector) {
        new Subject().find(getSubject_id(), collector);
    }

    public void getRoom(SingleCollector collector) {
        new Room().find(getRoom_id(), collector);
    }

    public void getStudents(final Collector collector) {
        final List<Student> students = new ArrayList<>();

        FirebaseDatabase.getInstance()
                .getReference()
                .child(App.getContext().getString(R.string.node_classes))
                .child(getClass_id())
                .child(App.getContext().getString(R.string.node_students))
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        students.clear();

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Student student = snapshot.getValue(Student.class);

                            students.add(student);
                        }

                        collector.collect(students);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }

    public void addStudent(Student student) {
        FirebaseDatabase.getInstance().getReference()
                .child(App.getContext().getString(R.string.node_classes))
                .child(getClass_id())
                .child(App.getContext().getString(R.string.node_students))
                .child(student.getStudent_id())
                .setValue(student);

        student.addClass(this);
    }

    public void removeStudent(Student student) {
        FirebaseDatabase.getInstance().getReference()
                .child(App.getContext().getString(R.string.node_classes))
                .child(getClass_id())
                .child(App.getContext().getString(R.string.node_students))
                .child(student.getIdentifier())
                .removeValue();

        student.removeClass(this);
    }

    public static class ClassCache {
        private Class mClass;
        private String time;

        public ClassCache(Class aClass, String time) {
            mClass = aClass;
            this.time = time;
        }

        public Class getClasss() {
            return mClass;
        }

        public void setClass(Class aClass) {
            mClass = aClass;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
