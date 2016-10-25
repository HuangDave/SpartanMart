package com.spartanmart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;

import com.spartanmart.model.User;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.evEmail) EditText etEmail;
    @BindView(R.id.evPassword) EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
    }

    protected boolean isValidCard(String number, String code, String exp) {
        return true;
    }

    @OnClick(R.id.bRegister)
    public void onUserSelectRegister() {

        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        if (TextUtils.isEmpty(email)) {
            etEmail.setError(getString(R.string.error_field_required));
            etEmail.requestFocus();
            return;

        } else if (!email.contains("@sjsu.edu")) {
            etEmail.setError(getString(R.string.error_invalid_email));
            etEmail.requestFocus();
            return;
        }

        User.register(this, email, password, new User.RegisterHandler() {
            @Override
            public void onRegisterSuccessful() {
                Log.d("REGISTER", "Successful");
                finishActivity(1);
            }

            @Override
            public void onRegisterFailed() {
                Log.d("REGISTER", "Failed");

            }
        });
    }

}
