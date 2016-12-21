package com.spartanmart.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.spartanmart.R;
import com.spartanmart.activities.base_activities.BaseActivity;
import com.spartanmart.adapters.CardAdapter;
import com.spartanmart.model.Card;
import com.spartanmart.model.User;
import com.spartanmart.server.ServerManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnItemClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by David on 12/19/16.
 */

public class PaymentManagementActivity extends BaseActivity {

    @BindView(R.id.listView)
    public ListView mListView;

    protected CardAdapter mAdapter;
    protected static User mUser = User.currentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_management);

        mAdapter = new CardAdapter(this, new ArrayList<Card>());
        mListView.setAdapter(mAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        reload();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.payment_method, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add: onUserSelectAddPaymentMethod(); break;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void reload() {
        ServerManager manager = ServerManager.manager;
        manager.api.listCards(manager.getToken(), mUser.uid).enqueue(new Callback<List<Card>>() {
            @Override
            public void onResponse(Call<List<Card>> call, Response<List<Card>> response) {
                if (response.isSuccessful() && response.code() == 200) {
                    if (!response.body().isEmpty()) {
                        mAdapter.clear();
                        mAdapter.addAll(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Card>> call, Throwable t) {

            }
        });
    }

    @OnItemClick(R.id.listView)
    public void onUserSelectCard(int pos) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final LayoutInflater layoutInflater = getLayoutInflater();
        final View view = layoutInflater.inflate(R.layout.alert_updatecard, null);

        final EditText etMonth = (EditText)view.findViewById(R.id.etMonth);
        final EditText etYear = (EditText)view.findViewById(R.id.etYear);

        final Card card = mAdapter.getItem(pos);
        etMonth.setText(card.expMonth);
        etYear.setText(card.expYear);

        builder.setView(view)
                .setTitle("Edit Card")
                .setMessage("Update the expiration month or remove the card.")
                .setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Remove", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialog, int i) {
                        mUser.removeCard(card, new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                if (response.isSuccessful() && response.code() == 200) {
                                    Toast.makeText(getApplicationContext(), "Card Removed!", Toast.LENGTH_SHORT).show();
                                    reload();
                                    dialog.dismiss();
                                }
                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {
                                Log.d("Error removing card", t.getLocalizedMessage());
                                Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                })
                .setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialog, int i) {
                        card.expMonth = etMonth.getText().toString();
                        card.expYear = etYear.getText().toString();
                        mUser.updateCard(card, new Callback<Map<String, Object>>() {
                            @Override
                            public void onResponse(Call<Map<String, Object>> call, Response<Map<String, Object>> response) {
                                if (response.isSuccessful() && response.code() == 200) {
                                    Toast.makeText(getApplicationContext(), "Card Updated!", Toast.LENGTH_SHORT).show();
                                    reload();
                                    dialog.dismiss();
                                }
                            }

                            @Override
                            public void onFailure(Call<Map<String, Object>> call, Throwable t) {
                                Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    protected void onUserSelectAddPaymentMethod() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final LayoutInflater layoutInflater = getLayoutInflater();
        final View view = layoutInflater.inflate(R.layout.alert_addcard, null);

        builder.setView(view)
                .setTitle("Add Card")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialog, int i) {
                        EditText etCardNumber = (EditText)view.findViewById(R.id.etCardNumber);
                        EditText etMonth = (EditText)view.findViewById(R.id.etMonth);
                        EditText etYear = (EditText)view.findViewById(R.id.etYear);
                        EditText etCVC = (EditText)view.findViewById(R.id.etCVC);

                        // TODO: check input fields
                        Card card = new Card();
                        card.number = etCardNumber.getText().toString();
                        card.expMonth = etMonth.getText().toString();
                        card.expYear = etYear.getText().toString();
                        card.cvc = etCVC.getText().toString();

                        mUser.addCard(card, new Callback<Map<String, Object>>() {
                            @Override
                            public void onResponse(Call<Map<String, Object>> call, Response<Map<String, Object>> response) {
                                if (response.isSuccessful() && response.code() == 201) {
                                    Toast.makeText(getApplicationContext(), "Card Added!", Toast.LENGTH_SHORT).show();
                                    reload();
                                    dialog.dismiss();
                                }
                            }

                            @Override
                            public void onFailure(Call<Map<String, Object>> call, Throwable t) {
                                // TODO: handle failure is failed to add card
                                Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
