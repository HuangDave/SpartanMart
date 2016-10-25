package com.spartanmart.model;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.spartanmart.LoginActivity;

/**
 * Created by David on 10/21/16.
 */

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
    protected String email;
    protected String password;
    protected String firstName;
    protected String lastName;
    protected String contact;
    protected Product[] products;

    public User(String id) {
        super(id);
        ref = userRef.child(id);
    }

    public static void register(Activity activity, final String email, final String password, final RegisterHandler handler) {
        FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            handler.onRegisterSuccessful();
                        } else {
                            handler.onRegisterFailed();
                        }
                    }
                });
    }

    public static void login(final String email, final String password, final LoginHandler handler) {
        final FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user = auth.getCurrentUser();
                if (user != null) {
                    handler.onLoginSuccessful();
                } else {
                    handler.onLoginFailed();
                }
            }
        });
        auth.signInWithEmailAndPassword(email, password);
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getContact() {
        return contact;
    }

    public void addProduct(Product p) {

    }

    public Product[] getProducts() {
        return products;
    }
}
