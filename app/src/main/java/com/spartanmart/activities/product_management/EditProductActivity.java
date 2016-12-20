package com.spartanmart.activities.product_management;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.spartanmart.R;
import com.spartanmart.activities.base_activities.ProductActivity;
import com.spartanmart.model.Product;
import com.spartanmart.model.User;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by David on 12/18/16.
 */
public class EditProductActivity extends ProductActivity {

    public static Product mProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        etTitle.setText(mProduct.title);
        etDescription.setText(mProduct.description);
        etPrice.setText(mProduct.price.toString());
    }

    @Override
    protected void onStop() {
        super.onStop();
        mProduct = null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_update: onUserSelectUpdate(); break;
            case R.id.action_remove: onUserSelectRemove(); break;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void onUserSelectUpdate() {
        mProduct.sellerId = User.currentUser().uid;
        mProduct.title = etTitle.getText().toString();
        mProduct.description = etDescription.getText().toString();
        mProduct.price = Double.parseDouble(etPrice.getText().toString());
        mProduct.update(new Callback<Map<String, Object>>() {
            @Override
            public void onResponse(Call<Map<String, Object>> call, Response<Map<String, Object>> response) {
                if (response.code() == 200) {
                    Toast.makeText(getApplicationContext(), "Updated!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<Map<String, Object>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void onUserSelectRemove() {
        mProduct.delete(new Callback<Map<String, Object>>() {
            @Override
            public void onResponse(Call<Map<String, Object>> call, Response<Map<String, Object>> response) {
                if (response.code() == 200) {
                    Toast.makeText(getApplicationContext(), "Deleted!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<Map<String, Object>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}