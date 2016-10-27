package com.spartanmart.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.spartanmart.R;
import com.spartanmart.model.User;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfileActivity extends AppCompatActivity {

    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);

        HashMap<String, Object> data = new HashMap<>();
        data.put("email", "testemail@sjsu.edu");
        data.put("password", "1234567");
    }

    @OnClick(R.id.bSave)
    public void onSave(Button b) {
        mUser.save();
    }
}
