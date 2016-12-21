package com.spartanmart.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.spartanmart.R;
import com.spartanmart.activities.base_activities.BaseActivity;
import com.spartanmart.adapters.AccountAdapter;
import com.spartanmart.model.User;

import butterknife.BindView;
import butterknife.OnItemClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountActivity extends BaseActivity {

    @BindView(R.id.listView)
    public ListView mListView;

    protected AccountAdapter mAdapter;

    protected User mUser = User.currentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        mAdapter = new AccountAdapter(this, mUser);
        mListView.setAdapter(mAdapter);
    }

    @OnItemClick(R.id.listView)
    public void onOptionSelected(int pos) {
        switch (pos) {
            case AccountAdapter.OPTIONCHANGEIMAGE: break;
            case AccountAdapter.OPTIONCHANGEPASSWORD: onUserSelectUpdatePassword(); break;
            case AccountAdapter.OPTIONCHANGECONTACT:  onUserSelectUpdateContact();  break;
            default: break;
        }
    }

    protected void onUserSelectUpdatePassword() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final LayoutInflater layoutInflater = getLayoutInflater();
        final View view = layoutInflater.inflate(R.layout.alert_updatepassword, null);

        final EditText etOldPassword = (EditText)view.findViewById(R.id.etOldPassword);
        final EditText etNewPassword = (EditText)view.findViewById(R.id.etNewPassword);

        builder.setView(view)
                .setTitle("Update Password")
                .setPositiveButton("Update", null)
                .setNeutralButton("Cancel", null);

        final AlertDialog dialog = builder.create();
        dialog.show();
        dialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String oldPassword = etOldPassword.getText().toString();
                final String newPassword = etNewPassword.getText().toString();

                if (etOldPassword.getText().toString().isEmpty() || etNewPassword.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Fields can't be empty!", Toast.LENGTH_SHORT).show();
                    return;
                } else if (oldPassword.equals(newPassword)) {
                    Toast.makeText(getApplicationContext(), "New password can't be the same!", Toast.LENGTH_SHORT).show();
                    return;
                }

                mUser.updatePassword(oldPassword, newPassword, new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful() && response.code() == 200) {
                            Toast.makeText(getApplicationContext(), "Password Updated!", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        } else if (response.code() == 304) {
                            Toast.makeText(getApplicationContext(), "Old password doesn't match", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    protected void onUserSelectUpdateContact() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final LayoutInflater layoutInflater = getLayoutInflater();
        final View view = layoutInflater.inflate(R.layout.alert_updatecontact, null);

        final EditText etOldPassword = (EditText)view.findViewById(R.id.etContact);

        builder.setView(view)
                .setTitle("Update Contact")
                .setPositiveButton("Update", null)
                .setNeutralButton("Cancel", null);

        final AlertDialog dialog = builder.create();
        dialog.show();
        dialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String newContact = etOldPassword.getText().toString();

                mUser.updateContact(newContact, new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful() && response.code() == 200) {
                            Toast.makeText(getApplicationContext(), "Contact Updated!", Toast.LENGTH_SHORT).show();
                            mUser.contact = newContact;
                            mAdapter.notifyDataSetChanged();
                            dialog.dismiss();
                        } else if (response.code() == 304) {
                            //Toast.makeText(getApplicationContext(), "Old password doesn't match", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
