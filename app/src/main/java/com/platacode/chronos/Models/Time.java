package com.platacode.chronos.Models;

import com.google.firebase.database.DataSnapshot;
import com.platacode.chronos.App;
import com.platacode.chronos.R;

import java.util.HashMap;
import java.util.Map;

public class Time extends Model<Time> {
    public static final String AM_6_7 = "1";
    public static final String AM_7_8 = "2";
    public static final String AM_8_9 = "3";
    public static final String AM_9_10 = "4";
    public static final String AM_10_11 = "5";
    public static final String AM_11_12 = "6";
    public static final String PM_12_1 = "7";
    public static final String PM_1_2 = "8";
    public static final String PM_2_3 = "9";
    public static final String PM_3_4 = "10";
    public static final String PM_4_5 = "11";
    public static final String PM_5_6 = "12";
    public static final String PM_6_7 = "13";
    public static final String PM_7_8 = "14";

    private String time_id;
    private String name;

    public Time(String time_id, String name) {
        this.time_id = time_id;
        this.name = name;
    }

    public Time() {
    }

    public String getTime_id() {
        return time_id;
    }

    public void setTime_id(String time_id) {
        this.time_id = time_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Time{" +
                "time_id='" + time_id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    String getDbNode() {
        return App.getContext().getString(R.string.node_times);
    }

    @Override
    String getIdentifier() {
        return getTime_id();
    }

    @Override
    Time parseSnapshot(DataSnapshot snapshot) {
        return snapshot.getValue(Time.class);
    }

    @Override
    Map<String, Object> toMap() {
        Map<String, Object> time = new HashMap<>();

        time.put(App.getContext().getString(R.string.time_field_time_id), getTime_id());
        time.put(App.getContext().getString(R.string.time_field_name), getName());

        return time;
    }
}
