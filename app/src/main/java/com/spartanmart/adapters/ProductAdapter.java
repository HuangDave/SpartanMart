package com.spartanmart.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.spartanmart.R;
import com.spartanmart.model.Product;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by David on 11/6/16.
 */

public class ProductAdapter extends ArrayAdapter<Product> {

    public static class ItemViewHolder {
        @BindView(R.id.imageView) ImageView mImageView;
        @BindView(R.id.tvName) TextView tvTitle;
        @BindView(R.id.tvPrice) TextView tvPrice;

        public ItemViewHolder(View v) {
            ButterKnife.bind(this, v);
        }
    }

    public ProductAdapter(Context c, ArrayList<Product> products) {
        super(c, R.layout.marketview_item, products);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ItemViewHolder viewHolder;

        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(R.layout.marketview_item, viewGroup, false);
            viewHolder = new ItemViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ItemViewHolder)view.getTag();
        }

        Product p = getItem(i);
        viewHolder.tvTitle.setText(p.name);
        viewHolder.tvPrice.setText(String.format("$%.2f", p.price));

        return view;
    }
}
