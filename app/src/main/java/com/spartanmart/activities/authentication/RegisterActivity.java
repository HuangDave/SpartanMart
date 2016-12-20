package com.spartanmart.activities.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.spartanmart.R;
import com.spartanmart.activities.BrowseActivity;
import com.spartanmart.model.User;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.etEmail)
    public EditText etEmail;

    @BindView(R.id.etPassword)
    public EditText etPassword;

    @BindView(R.id.etFirstName)
    public EditText etFirstName;

    @BindView(R.id.etLastName)
    public EditText etLastName;

    @BindView(R.id.etContact)
    public EditText etContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.register, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_register: onUserSelectRegister(); break;
            default: finish(); break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onUserSelectRegister() {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        final String first = etFirstName.getText().toString();
        final String last = etLastName.getText().toString();
        String contact = etContact.getText().toString();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
            return;
        } else if (!email.contains("@sjsu.edu")) {
            Toast.makeText(getApplicationContext(), "Please provide an SJSU email address!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(first)) {
            Toast.makeText(getApplicationContext(), "Enter your first name!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(last)) {
            Toast.makeText(getApplicationContext(), "Enter your last name!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(contact)) {
            Toast.makeText(getApplicationContext(), "Enter your contact!", Toast.LENGTH_SHORT).show();
            return;
        }

        User user = new User();
        user.email = email;
        user.password = password;
        user.name = new HashMap<String, String>(){{
            put("first", first);
            put("last", last);
        }};
        user.contact = contact;
        user.register(new User.RegistrationCallback() {
            @Override
            public void onRegisterSuccessful() {
                Toast.makeText(getApplicationContext(), "Registration Successful!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), BrowseActivity.class));
                finish();
            }

            @Override
            public void onRegisterFailed(String localizedMessage) {
                Toast.makeText(getApplicationContext(), localizedMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
