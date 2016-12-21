package com.spartanmart.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.spartanmart.R;
import com.spartanmart.activities.base_activities.BaseActivity;
import com.spartanmart.adapters.TransactionAdapter;
import com.spartanmart.model.Query;
import com.spartanmart.model.Transaction;
import com.spartanmart.model.User;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnItemClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransactionsActivity extends BaseActivity {

    @BindView(R.id.listView)
    public ListView mListView;

    protected TransactionAdapter mAdapter;
    protected static User mUser = User.currentUser();

    protected boolean showCompleted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);

        mAdapter = new TransactionAdapter(this, new ArrayList<Transaction>());
        mListView.setAdapter(mAdapter);
        reload();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.transactions, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh: break;
            case R.id.action_show_pending:      showCompleted = false;  break;
            case R.id.action_show_completed:    showCompleted = true;   break;
        }
        reload();
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        reload();
    }

    protected void reload() {
        final boolean showCompleted = this.showCompleted;

        Query.Transaction.byUserId(mUser.uid, new Callback<List<Transaction>>() {
            @Override
            public void onResponse(Call<List<Transaction>> call, Response<List<Transaction>> response) {
                if (!response.body().isEmpty()) {
                    mAdapter.clear();
                    for (Transaction transaction: response.body()) {
                        if (showCompleted && transaction.status.equals("succeeded")) {
                            mAdapter.add(transaction);
                        } else if (!showCompleted && transaction.status.equals("pending")) {
                            mAdapter.add(transaction);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Transaction>> call, Throwable t) {
                // TODO: Handle failure to retreive transcation history
            }
        });
    }

    @OnItemClick(R.id.listView)
    public void onUserSelectTransaction(int pos) {
        final Transaction transaction = mAdapter.getItem(pos);
        if (transaction.status.equals("pending")) {

        }
    }
}
