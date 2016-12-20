package com.spartanmart.activities;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

import com.spartanmart.R;
import com.spartanmart.activities.product_management.AddProductActivity;
import com.spartanmart.activities.base_activities.ProductListActivity;
import com.spartanmart.model.Product;
import com.spartanmart.model.Query;
import com.spartanmart.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BrowseActivity extends ProductListActivity {

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
        startActivity(new Intent(getApplicationContext(), c));
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

}
