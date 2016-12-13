package com.spartanmart.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.widget.ListView;

import com.spartanmart.R;
import com.spartanmart.adapters.ProductAdapter;
import com.spartanmart.model.Product;
import com.spartanmart.server.ServerManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MarketActivity extends AppCompatActivity {

    @BindView(R.id.searchView) SearchView mSearchView;
    @BindView(R.id.listView) ListView mListView;

    ServerManager manager = ServerManager.manager;
    ProductAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);
        ButterKnife.bind(this);

        //mAdapter = new ProductAdapter(this, null);
        //mListView.setAdapter(mAdapter);
        //queryProducts(null);
    }

    public void queryRecent() {
        HashMap<String, Object> queryMap = new HashMap<>();
        queryMap.put("by", "createdAt");
        queryMap.put("order", "descending");
        manager.service.listProducts(manager.getToken(), queryMap).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {

                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });
    }

    public String[] parseKeywords(String s) {
        ArrayList<String> keywords = new ArrayList<>();
        return (String[])keywords.toArray();
    }

}
