package com.spartanmart.adapters;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.spartanmart.R;
import com.spartanmart.model.Product;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by David on 12/15/16.
 */

public class ProductAdapter extends ArrayAdapter<Product> {

    public static class ItemViewHolder {

        @Nullable
        @BindView(R.id.tvTitle)
        TextView tvTitle;

        @Nullable
        @BindView(R.id.tvPrice)
        TextView tvPrice;

        public ItemViewHolder(View v) {
            ButterKnife.bind(this, v);
        }
    }

    public ProductAdapter(Context c, List<Product> products) {
        super(c, R.layout.layout_grid_item, products);
    }

    @Override
    public View getView(int pos, View view, ViewGroup viewGroup) {
        ItemViewHolder viewHolder;

        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(R.layout.layout_grid_item, viewGroup, false);
            viewHolder = new ItemViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ItemViewHolder)view.getTag();
        }

        Product product = getItem(pos);
        viewHolder.tvTitle.setText(product.title);
        viewHolder.tvPrice.setText(String.format("$%.2f", product.price));

        return view;
    }
}
