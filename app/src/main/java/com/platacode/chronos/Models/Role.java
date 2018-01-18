package com.platacode.chronos.Models;

import com.google.firebase.database.FirebaseDatabase;
import com.platacode.chronos.App;
import com.platacode.chronos.R;

import java.util.Map;

public class Role extends Model {
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

    public void createRole(Student student, UserRole role) {
        int roleId = 0;
        switch (role) {
            case student:
                roleId = 1;
                break;
            case teacher:
                roleId = 2;
                break;
            case admin:
                roleId = 3;
                break;
        }
        FirebaseDatabase.getInstance().getReference()
                .child(getDbNode())
                .child(student.getStudent_id())
                .setValue(roleId);
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
    Map<String, Object> toMap() {
        return null;
    }
}
