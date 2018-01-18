package com.platacode.chronos.Models;

import com.platacode.chronos.App;
import com.platacode.chronos.R;

import java.util.HashMap;
import java.util.Map;

public class Day extends Model {
    public final static String SUNDAY = "0";
    public final static String MONDAY = "1";
    public final static String TUESDAY = "2";
    public final static String WEDNESDAY = "3";
    public final static String THURSDAY = "4";
    public final static String FRIDAY = "5";
    public final static String SATURDAY = "6";

    private String day_id;
    private String name;

    public Day(String day_id, String name) {
        this.day_id = day_id;
        this.name = name;
    }

    public Day() {
    }

    public String getDay_id() {
        return day_id;
    }

    public void setDay_id(String day_id) {
        this.day_id = day_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Day{" +
                "day_id='" + day_id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    String getDbNode() {
        return App.getContext().getString(R.string.node_days);
    }

    @Override
    String getIdentifier() {
        return getDay_id();
    }

    @Override
    Map<String, Object> toMap() {
        Map<String, Object> day = new HashMap<>();

        day.put(App.getContext().getString(R.string.day_field_day_id), getDay_id());
        day.put(App.getContext().getString(R.string.day_field_name), getName());

        return day;
    }
}
