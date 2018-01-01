package com.platacode.chronos.Models;


import java.util.Date;

public class History {
    private String name;

    public History(String name, String createdAt) {
        this.name = name;
        this.createdAt = createdAt;
    }

    private String createdAt;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
