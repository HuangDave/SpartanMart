
package com.spartanmart.activities.product_management;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.spartanmart.R;
import com.spartanmart.activities.base_activities.ProductActivity;
import com.spartanmart.model.Product;
import com.spartanmart.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddProductActivity extends ProductActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_product, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Product product = new Product();
        product.sellerId = User.currentUser().uid;
        product.title = etTitle.getText().toString();
        product.description = etDescription.getText().toString();
        product.price = Double.parseDouble(etPrice.getText().toString());

        User.currentUser().addProduct(product, new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                if (response.code() == 201) {
                    Toast.makeText(getApplicationContext(), "Posted!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return super.onOptionsItemSelected(item);
    }

}
