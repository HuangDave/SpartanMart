package com.spartanmart.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.view.menu.MenuView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.spartanmart.R;
import com.spartanmart.activities.product_management.AddProductActivity;
import com.spartanmart.activities.base_activities.ProductListActivity;
import com.spartanmart.adapters.ProductAdapter;
import com.spartanmart.model.Product;
import com.spartanmart.model.Query;
import com.spartanmart.model.Transaction;
import com.spartanmart.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BrowseActivity extends ProductListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((SearchView)findViewById(R.id.searchView)).setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return onUserDidSearch(query);
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return onUserDidSearch(newText);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.browse, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Class c = null;
        switch (item.getItemId()) {
            case R.id.action_refresh:   reload();                       break;
            case R.id.action_add:       c = AddProductActivity.class;   break;
        }
        if (c != null) {
            startActivity(new Intent(getApplicationContext(), c));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void reload() {
        Query.Product.recent(20, new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful() && response.code() == 200) {

                    if (response.body().isEmpty()) {
                        return;
                    }

                    mAdapter.clear();
                    for (Product product: response.body()) {
                        if (!product.sellerId.equals(User.currentUser().uid)) {
                            mAdapter.add(product);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });
    }

    public boolean onUserDidSearch(final String keyword) {
        if (keyword.isEmpty()) {
            reload();
            return false;
        }

        Query.Product.queryByKeyword(keyword, 20, new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful() && response.code() == 200) {

                    if (response.body().isEmpty()) {
                        return;
                    }

                    mAdapter.clear();
                    for (Product product: response.body()) {
                        if (!product.sellerId.equals(User.currentUser().uid)) {
                            mAdapter.add(product);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });
        return false;
    }

    @Override
    protected void onItemSelected(final int pos) {

        final Context context = this;
        final Product product = mAdapter.getItem(pos);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final LayoutInflater layoutInflater = getLayoutInflater();
        final View view = layoutInflater.inflate(R.layout.alert_productdetail, null);

        TextView tvTitle = (TextView)view.findViewById(R.id.tvTitle);
        TextView tvDescription = (TextView)view.findViewById(R.id.tvDescription);
        TextView tvPrice = (TextView)view.findViewById(R.id.tvPrice);
        tvTitle.setText(product.title);
        tvDescription.setText(product.description);
        //tvPrice.setText(product.);

        builder.setView(view)
                .setTitle("Add Transaction")
                .setMessage("Would you like to add this product to your transactions?")
                .setPositiveButton("Yes", null)
                .setNeutralButton("Cancel", null);

        final AlertDialog dialog = builder.create();
        dialog.show();

        dialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = User.currentUser();
                user.addTransaction(product.uid, new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful() && response.code() == 201) {
                            dialog.dismiss();

                            AlertDialog.Builder builder = new AlertDialog.Builder(context);
                            builder.setTitle("Transaction Added")
                                    .setMessage("To complete the transaction, contact the seller. \n" +
                                                "You can view this transaction in Transactions.")
                                    .setPositiveButton("OK", null)
                                    .setNeutralButton("Cancel", null);
                            builder.create().show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });
            }
        });
    }
}
