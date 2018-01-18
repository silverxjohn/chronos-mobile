package com.platacode.chronos.Models;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public abstract class Model {

    abstract String getDbNode();

    abstract String getIdentifier();

    public void create() {
        FirebaseDatabase.getInstance().getReference()
                .child(getDbNode())
                .child(getIdentifier())
                .setValue(this);
    }

    public void saveChanges() {
        FirebaseDatabase.getInstance().getReference()
                .child(getDbNode())
                .child(getIdentifier())
                .updateChildren(toMap());
    }

    public void delete() {
        delete(getIdentifier());
    }

    public void delete(String id) {
        FirebaseDatabase.getInstance().getReference()
                .child(getDbNode())
                .child(id)
                .removeValue();
    }

    public void addValueEventListener(ValueEventListener listener) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        reference.child(getDbNode()).addValueEventListener(listener);
    }

    abstract Map<String, Object> toMap();
}
