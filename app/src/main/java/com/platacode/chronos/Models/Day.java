package com.platacode.chronos.Models;

public class Day {
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

    public String getDayId() {
        return day_id;
    }

    public void setDayId(String day_id) {
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
}
