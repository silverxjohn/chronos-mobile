package com.platacode.chronos.Models;

import com.platacode.chronos.App;
import com.platacode.chronos.R;

import java.util.Map;

public class College extends Model {
    private String college_id;
    private String name;

    public College(String college_id, String name) {
        this.college_id = college_id;
        this.name = name;
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

    @Override
    public String toString() {
        return "College{" +
                "college_id='" + college_id + '\'' +
                ", name='" + name + '\'' +
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
    Map<String, Object> toMap() {
        return null;
    }
}
