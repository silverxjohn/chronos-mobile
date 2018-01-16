package com.platacode.chronos.Models;

import com.google.firebase.database.FirebaseDatabase;

public abstract class Model {

    abstract String getDbNode();

    abstract String getIdentifier();

    public void create() {
        FirebaseDatabase.getInstance().getReference()
                .child(getDbNode())
                .child(getIdentifier())
                .setValue(this);
    }
}
