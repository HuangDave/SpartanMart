package com.spartanmart.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.spartanmart.R;
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

    @OnClick(R.id.bRegister)
    public void onUserSelectRegister(final Button button) {

        button.setEnabled(false);

        boolean cancelled = false;
        final String email = etEmail.getText().toString();
        final String password = etPassword.getText().toString();

        if (TextUtils.isEmpty(email)) {
            etEmail.setError(getString(R.string.error_field_required));

        } else if (!email.contains("@sjsu.edu")) {
            etEmail.setError(getString(R.string.error_invalid_email));
        }

        if (cancelled) {
            etEmail.requestFocus();
            button.setEnabled(true);
            return;
        }

        User.register(email, password, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Log.d("REGISTER", "Successful");
                    finishActivity(1);
                    Intent marketIntent = new Intent(getApplicationContext(), MarketActivity.class);
                    startActivity(marketIntent);
                } else {
                    Log.d("REGISTER", "Failed");
                    button.setEnabled(true);
                }
            }
        });
    }

}
