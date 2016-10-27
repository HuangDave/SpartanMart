package com.spartanmart.model;

import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by David on 10/21/16.
 */
@IgnoreExtraProperties
public class User extends Object {

    protected static DatabaseReference userRef = baseRef.child("users");
    public String email;
    public String password;
    public String firstName;
    public String lastName;
    public String contact;
    public Product[] products;

    public User(String id) {
        super(id);
        ref = userRef.child(id);
    }

    public static void register(final String email, final String password, final OnCompleteListener<AuthResult> listener) {
        FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(listener);
    }

    public static void login(final String email, final String password, final FirebaseAuth.AuthStateListener listener) {
        final FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.addAuthStateListener(listener);
        auth.signInWithEmailAndPassword(email, password);
    }

    @Override
    public void save() {
        if (!exists) {
            id = ref.push().getKey();
            ref.child(id);
            exists = true;
        }

        Log.d("Updating user", id);
        data.put("email", email);
        userRef.child(id).setValue(data);
    }

    public void logout() {
        FirebaseAuth.getInstance().signOut();
    }
}
