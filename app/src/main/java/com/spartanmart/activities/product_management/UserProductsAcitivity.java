package com.spartanmart.activities.product_management;

import android.content.Intent;

import com.spartanmart.activities.base_activities.ProductListActivity;
import com.spartanmart.model.Product;
import com.spartanmart.model.Query;
import com.spartanmart.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserProductsAcitivity extends ProductListActivity {

    @Override
    protected void reload() {
        Query.Product.byUserId(User.currentUser().uid, new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful() && response.code() == 200) {
                    mAdapter.clear();
                    mAdapter.addAll(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });
    }

    @Override
    protected void onItemSelected(int pos) {
        EditProductActivity.mProduct = mAdapter.getItem(pos);
        startActivity(new Intent(this, EditProductActivity.class));
    }
}
