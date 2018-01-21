package com.platacode.chronos.Models;

import com.google.firebase.database.DataSnapshot;
import com.platacode.chronos.App;
import com.platacode.chronos.R;

import java.util.HashMap;
import java.util.Map;

public class TimeLog extends Model<TimeLog> {
    private String time_log_id;
    private String class_id;
    private String student_id;
    private String latitude;
    private String longitude;
    private String created_at;

    public TimeLog(String time_log_id, String class_id, String student_id, String latitude, String longitude, String created_at) {
        this.time_log_id = time_log_id;
        this.class_id = class_id;
        this.student_id = student_id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.created_at = created_at;
    }

    public TimeLog() {
    }

    public String getTime_log_id() {
        return time_log_id;
    }

    public void setTime_log_id(String time_log_id) {
        this.time_log_id = time_log_id;
    }

    public String getClass_id() {
        return class_id;
    }

    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        return "TimeLog{" +
                "time_log_id='" + time_log_id + '\'' +
                ", class_id='" + class_id + '\'' +
                ", student_id='" + student_id + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", created_at='" + created_at + '\'' +
                '}';
    }

    @Override
    String getDbNode() {
        return App.getContext().getString(R.string.node_timelogs);
    }

    @Override
    String getIdentifier() {
        return getTime_log_id();
    }

    @Override
    TimeLog parseSnapshot(DataSnapshot snapshot) {
        return snapshot.getValue(TimeLog.class);
    }

    @Override
    Map<String, Object> toMap() {
        Map<String, Object> timelog = new HashMap<>();

        timelog.put(App.getContext().getString(R.string.timelog_field_time_log_id), getTime_log_id());
        timelog.put(App.getContext().getString(R.string.timelog_field_class_id), getClass_id());
        timelog.put(App.getContext().getString(R.string.timelog_field_student_id), getStudent_id());
        timelog.put(App.getContext().getString(R.string.timelog_field_latitude), getLatitude());
        timelog.put(App.getContext().getString(R.string.timelog_field_longitude), getLongitude());
        timelog.put(App.getContext().getString(R.string.timelog_field_created_at), getCreated_at());

        return timelog;
    }
}
