package com.spartanmart.activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.spartanmart.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecoverActivity extends AppCompatActivity {

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @BindView(R.id.etEmail) EditText etEmail;
    @BindView(R.id.bRecover) Button bRecover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recover);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.bRecover)
    public void onUserSelectSubmit() {
        final String email = etEmail.getText().toString();
        boolean cancel = false;
        bRecover.setEnabled(false);

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            etEmail.setError(getString(R.string.error_field_required));
            cancel = true;
        } else if (!email.contains("@sjsu.edu")) {
            etEmail.setError(getString(R.string.error_invalid_email));
            cancel = true;
        }

        if (cancel) {
            etEmail.requestFocus();
            bRecover.setEnabled(true);
            Log.d("Recover", "Unsuccessful");
            return;
        }

        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful() && task.getResult() != null) {
                    Log.d("Recover", "Successful");
                } else {
                    Log.d("Recover", "Unsuccessful");
                    etEmail.setError("This email is not registered!");
                    etEmail.requestFocus();
                    bRecover.setEnabled(true);
                }
            }
        });
    }
}
