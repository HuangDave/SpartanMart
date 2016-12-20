package com.spartanmart.activities;

import android.os.Bundle;
import android.widget.ListView;

import com.spartanmart.R;
import com.spartanmart.activities.base_activities.BaseActivity;
import com.spartanmart.adapters.AccountAdapter;
import com.spartanmart.model.User;

import butterknife.BindView;
import butterknife.OnItemClick;

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
            case AccountAdapter.OPTIONCHANGEPASSWORD: break;
            case AccountAdapter.OPTIONCHANGECONTACT: break;
            default: break;
        }
    }
}
