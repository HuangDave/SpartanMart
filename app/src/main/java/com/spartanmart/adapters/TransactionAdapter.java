package com.spartanmart.adapters;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.spartanmart.R;
import com.spartanmart.model.Transaction;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by David on 12/20/16.
 */
public class TransactionAdapter extends ArrayAdapter<Transaction> {

    public static class ItemViewHolder {

        @Nullable
        @BindView(R.id.tvTitle)
        public TextView tvTitle;

        @Nullable
        @BindView(R.id.tvAmount)
        public TextView tvAmount;

        @Nullable
        @BindView(R.id.tvStatus)
        public TextView tvStatus;

        public ItemViewHolder(View v) {
            ButterKnife.bind(this, v);
        }
    }

    public TransactionAdapter(Context c, List<Transaction> transactions) {
        super(c, R.layout.item_transaction, transactions);
    }

    @Override
    public View getView(int pos, View view, ViewGroup viewGroup) {
        ItemViewHolder viewHolder;

        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(R.layout.item_transaction, viewGroup, false);
            viewHolder = new ItemViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ItemViewHolder)view.getTag();
        }
        Transaction transaction = getItem(pos);
        viewHolder.tvTitle.setText(transaction.product.get("title").toString());
        viewHolder.tvAmount.setText(String.format("$%.2f", Double.valueOf(transaction.product.get("amount").toString())));
        viewHolder.tvStatus.setText(transaction.status);

        return view;
    }
}
