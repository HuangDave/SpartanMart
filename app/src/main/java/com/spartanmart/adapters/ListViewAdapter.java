package com.spartanmart.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.spartanmart.R;
import com.spartanmart.model.Product;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by David on 10/25/16.
 */

public class ListViewAdapter extends BaseAdapter {


    public static class ItemViewHolder {
        @BindView(R.id.imageView) ImageView mImageView;
        @BindView(R.id.tvName) TextView tvTitle;
        @BindView(R.id.tvPrice) TextView tvPrice;

        public ItemViewHolder(View v) {
            ButterKnife.bind(this, v);
        }
    }

    protected Context mContext;
    protected Product[] mProducts;

    public ListViewAdapter(Context c) {
        mContext = c;
    }

    @Override
    public int getCount() {
        return mProducts.length;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public Product getItem(int i) {
        return mProducts[i];
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        /*
        ItemViewHolder viewHolder;

        if (view == null) {
            LayoutInflater inflater = new LayoutInflater();
            view = inflator.inflate(R.layout.marketview_item, viewGroup, false);
            viewHolder = new ItemViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ItemViewHolder) view.getTag();
        }

        Product p = mProducts[i];
        viewHolder.tvTitle.setText(p.name);
        viewHolder.tvPrice.setText(String.format("$%.2f", p.price));

        return view;
        */
        return null;
    }

}
