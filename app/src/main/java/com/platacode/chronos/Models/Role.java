package com.platacode.chronos.Models;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.platacode.chronos.App;
import com.platacode.chronos.R;

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
}
