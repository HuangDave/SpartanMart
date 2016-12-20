package com.spartanmart.activities.base_activities;

import android.os.Bundle;
import android.widget.GridView;

import com.spartanmart.R;
import com.spartanmart.adapters.ProductAdapter;
import com.spartanmart.model.Product;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnItemClick;

/**
 * Created by David on 12/18/16.
 */

public class ProductListActivity extends BaseActivity {

    @BindView(R.id.gridView)
    public GridView mGridView;

    protected ProductAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        mAdapter = new ProductAdapter(this, new ArrayList<Product>());
        mGridView.setAdapter(mAdapter);
        reload();
    }

    @Override
    protected void onResume() {
        super.onResume();
        reload();
    }

    protected void reload() {
        // override...
    }

    @OnItemClick(R.id.gridView)
    protected void onItemSelected(int pos) {
        // override
    }

}
