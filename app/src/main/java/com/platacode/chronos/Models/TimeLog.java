package com.platacode.chronos.Models;

public class TimeLog {
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

    public String getTimeLogId() {
        return time_log_id;
    }

    public void setTimeLogId(String time_log_id) {
        this.time_log_id = time_log_id;
    }

    public String getClassId() {
        return class_id;
    }

    public void setClassId(String class_id) {
        this.class_id = class_id;
    }

    public String getStudentId() {
        return student_id;
    }

    public void setStudentId(String student_id) {
        this.student_id = student_id;
    }

    public String getCreatedAt() {
        return created_at;
    }

    public void setCreatedAt(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdatedAt() {
        return updated_at;
    }

    public void setUpdatedAt(String updated_at) {
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
}
