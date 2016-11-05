package com.spartanmart.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.widget.ListView;

import com.spartanmart.R;
import com.spartanmart.adapters.ListViewAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MarketActivity extends AppCompatActivity {

    @BindView(R.id.searchView) SearchView mSearchView;
    @BindView(R.id.listView) ListView mListView;

    ListViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);
        ButterKnife.bind(this);

        mAdapter = new ListViewAdapter(this, null);
        mListView.setAdapter(mAdapter);
        queryProducts(null);
    }

    public void queryProducts(String[] keywords) {

    }

    public void userDidEnterKeywords(String keywords) {
        queryProducts(parseKeywords(keywords));
    }

    public String[] parseKeywords(String s) {
        ArrayList<String> keywords = new ArrayList<>();
        return (String[])keywords.toArray();
    }
}