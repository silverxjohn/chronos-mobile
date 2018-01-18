package com.platacode.chronos.Models;

import com.platacode.chronos.App;
import com.platacode.chronos.R;

import java.util.Map;

public class TimeLog extends Model {
    private String time_log_id;
    private String class_id;
    private String student_id;
    private String created_at;
    private String updated_at;

    public TimeLog(String time_log_id, String class_id, String student_id, String created_at, String updated_at) {
        this.time_log_id = time_log_id;
        this.class_id = class_id;
        this.student_id = student_id;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public TimeLog() {
    }

    public String getTimeLog_id() {
        return time_log_id;
    }

    public void setTimeLog_id(String time_log_id) {
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

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    @Override
    public String toString() {
        return "TimeLog{" +
                "time_log_id='" + time_log_id + '\'' +
                ", class_id='" + class_id + '\'' +
                ", student_id='" + student_id + '\'' +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                '}';
    }

    @Override
    String getDbNode() {
        return App.getContext().getString(R.string.node_timelogs);
    }

    @Override
    String getIdentifier() {
        return getTimeLog_id();
    }

    @Override
    Map<String, Object> toMap() {
        return null;
    }
}
