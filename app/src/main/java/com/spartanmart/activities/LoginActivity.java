package com.spartanmart.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.spartanmart.R;
import com.spartanmart.model.User;
import com.spartanmart.server.ServerManager;
import com.spartanmart.server.SpartanMartAPI;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.email)
    public EditText inputEmail;

    @BindView(R.id.password)
    public EditText inputPassword;

    @BindView(R.id.btn_signIn)
    public Button btnSignIn;

    @BindView(R.id.btn_register)
    public Button btnSignUp;

    @BindView(R.id.btn_reset_password)
    public Button btnResetPassword;

    @BindView(R.id.progressBar)
    public ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        ServerManager.manager.setContext(getApplicationContext());
    }

    @OnClick(R.id.btn_register)
    public void onUserSelectSignUp() {
        String email = inputEmail.getText().toString().trim();
        String password = inputPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.length() < 6) {
            Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    @OnClick(R.id.btn_signIn)
    public void onUserSelectSignIn(Button b) {
        String email = inputEmail.getText().toString();
        final String password = inputPassword.getText().toString();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
            return;
        }

        User.login(email, password, new SpartanMartAPI.AuthCallback() {
            @Override
            public void onLoginSuccessful() {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }

            @Override
            public void onLoginFailed(String localizedMessage) {
                Log.d("LOGIN FAILURE", "");
            }
        });
    }

    @OnClick(R.id.btn_reset_password)
    public void onUserSelectResetPassword() {
        startActivity(new Intent(LoginActivity.this, ResetPasswordActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }
}