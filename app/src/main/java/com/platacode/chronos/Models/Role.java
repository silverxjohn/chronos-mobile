package com.platacode.chronos.Models;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;
import com.platacode.chronos.App;
import com.platacode.chronos.R;

import java.util.Map;

public class Role extends Model<Role> {
    private UserRole role;
    private static Role roleInstance;

    private boolean hasModified;

    private Role() {
        hasModified = false;
    }

    public static Role getRoleInstance() {
        if (roleInstance == null)
            roleInstance = new Role();

        return roleInstance;
    }

    public void setRole(UserRole role) {
        if (!hasModified) {
            this.role = role;
            hasModified = true;
        }
    }

    public void resetRole() {
        this.hasModified = false;
        this.role = null;
    }

    public void createRole(Student student) {
        FirebaseDatabase.getInstance().getReference()
                .child(getDbNode())
                .child(student.getStudent_id())
                .setValue(1);
    }

    public void createRole(Teacher teacher) {
        FirebaseDatabase.getInstance().getReference()
                .child(getDbNode())
                .child(teacher.getTeacher_id())
                .setValue(2);
    }

    public void createRole(Parent parent) {
        FirebaseDatabase.getInstance().getReference()
                .child(getDbNode())
                .child(parent.getParent_id())
                .setValue(3);
    }

    public void deleteRole(String id) {
        FirebaseDatabase.getInstance().getReference()
                .child(getDbNode())
                .child(id)
                .removeValue();
    }

    public UserRole getAuthUserRole() {
        return role;
    }

    @Override
    String getDbNode() {
        return App.getContext().getString(R.string.node_roles);
    }

    @Override
    String getIdentifier() {
        return null;
    }

    @Override
    Role parseSnapshot(DataSnapshot snapshot) {
        return snapshot.getValue(Role.class);
    }

    @Override
    Map<String, Object> toMap() {
        return null;
    }
}
