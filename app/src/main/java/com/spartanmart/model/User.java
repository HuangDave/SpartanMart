package com.spartanmart.model;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.IgnoreExtraProperties;
import com.spartanmart.LoginActivity;

import java.util.Map;

/**
 * Created by David on 10/21/16.
 */
@IgnoreExtraProperties
public class User extends Object {

    public interface RegisterHandler {
        void onRegisterSuccessful();
        void onRegisterFailed();
    }

    public interface LoginHandler {
        void onLoginSuccessful();
        void onLoginFailed();
    }

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

    public User(Map<String, java.lang.Object> data) {
        super();
        ref = userRef;
        email = data.get("email").toString();
        password = data.get("password").toString();
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
}
