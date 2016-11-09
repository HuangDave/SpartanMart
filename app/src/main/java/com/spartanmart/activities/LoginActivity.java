package com.spartanmart.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.spartanmart.R;
import com.spartanmart.model.User;
import com.spartanmart.server.ServerManager;
import com.spartanmart.server.SpartanMartAuth;

import butterknife.ButterKnife;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    // UI references.
    @BindView(R.id.etEmail) EditText etEmail;
    @BindView(R.id.etPassword) EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        etEmail.setText("testemail@sjsu.edu");
        etPassword.setText("1234567");

        ServerManager manager = ServerManager.manager;
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    @OnClick(R.id.bLogin)
    public void onUserSelectLogin(final Button button) {

        // Disable button.
        button.setEnabled(false);

        // Reset errors.
        etEmail.setError(null);
        etPassword.setError(null);

        // Store values at the time of the login attempt.
        final String email = etEmail.getText().toString();
        final String password = etPassword.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !(password.length() > 4)) {
            etPassword.setError(getString(R.string.error_invalid_password));
            focusView = etPassword;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            etEmail.setError(getString(R.string.error_field_required));
            focusView = etEmail;
            cancel = true;
        } else if (!email.contains("@sjsu.edu")) {
            etEmail.setError(getString(R.string.error_invalid_email));
            focusView = etEmail;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
            button.setEnabled(true);

        } else {
            User.login(email, password, new SpartanMartAuth.AuthCallback() {
                @Override
                public void onLoginSuccessful() {
                    Log.d("LOGIN SUCCESS", "");
                }

                @Override
                public void onLoginFailed(String localizedMessage) {
                    Log.d("LOGIN FAILURE", "");
                }
            });
        }
    }

    @OnClick(R.id.bRecovery)
    public void onUserSelectRecover() {
        Intent recoveryIntent = new Intent(getApplicationContext(), RecoverActivity.class);
        startActivity(recoveryIntent);
    }

    @OnClick(R.id.bRegister)
    public void onUserSelectRegister() {
        Intent registerIntent = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(registerIntent);
    }

}

