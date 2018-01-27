package com.platacode.chronos.Models;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.platacode.chronos.Interfaces.Collector;
import com.platacode.chronos.Interfaces.SingleCollector;

import java.lang.*;
import java.lang.Class;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class Model<T> {

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

    public void find(String id, final SingleCollector collector) {
        FirebaseDatabase.getInstance().getReference()
                .child(getDbNode())
                .child(id)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        T item = parseSnapshot(dataSnapshot);

                        collector.collect(item);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }

    public void get(final Collector collector) {
        final List<T> items = new ArrayList<>();

        addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                items.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    items.add(parseSnapshot(snapshot));
                }

                collector.collect(items);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    abstract T parseSnapshot(DataSnapshot snapshot);

    abstract Map<String, Object> toMap();
}
