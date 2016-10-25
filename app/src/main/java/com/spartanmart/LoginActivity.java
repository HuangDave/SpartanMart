package com.spartanmart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.spartanmart.model.User;

import butterknife.ButterKnife;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    // UI references.
    @BindView(R.id.etEmail) EditText etEMail;
    @BindView(R.id.etPassword) EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    @OnClick(R.id.bLogin)
    public void onUserSelectLogin() {

        // Reset errors.
        etEMail.setError(null);
        etPassword.setError(null);

        // Store values at the time of the login attempt.
        String email = etEMail.getText().toString();
        String password = etPassword.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            etPassword.setError(getString(R.string.error_invalid_password));
            focusView = etPassword;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            etEMail.setError(getString(R.string.error_field_required));
            focusView = etEMail;
            cancel = true;
        } else if (!email.contains("@sjsu.edu")) {
            etEMail.setError(getString(R.string.error_invalid_email));
            focusView = etEMail;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();

        } else {

            User.login(email, password, new User.LoginHandler() {
                @Override
                public void onLoginSuccessful() {
                    Log.d("LOGIN", "Successful");
                }

                @Override
                public void onLoginFailed() {
                    Log.d("LOGIN", "Failed");
                }
            });
        }
    }

    /**
        Go to RegisterActivity
     */
    @OnClick(R.id.bRegister)
    public void onUserSelectRegister(Button button) {
        Intent registerIntent = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivityForResult(registerIntent, 1);
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }

}

