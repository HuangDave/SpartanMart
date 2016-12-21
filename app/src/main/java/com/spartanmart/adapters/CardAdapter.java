package com.spartanmart.adapters;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.spartanmart.R;
import com.spartanmart.model.Card;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by David on 12/19/16.
 */

public class CardAdapter extends ArrayAdapter<Card> {

    public static class ItemViewHolder {

        @Nullable
        @BindView(R.id.tvCardNumber)
        TextView tvCardNumber;

        @Nullable
        @BindView(R.id.tvType)
        TextView tvType;

        public ItemViewHolder(View v) {
            ButterKnife.bind(this, v);
        }
    }

    public CardAdapter(Context c, List<Card> cards) {
        super(c, R.layout.item_card, cards);
    }

    @Override
    public View getView(int pos, View view, ViewGroup viewGroup) {
        ItemViewHolder viewHolder;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(R.layout.item_card, viewGroup, false);
            viewHolder = new ItemViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ItemViewHolder)view.getTag();
        }

        Card card = getItem(pos);
        String last4 = "Ending in ****" + card.last4;
        viewHolder.tvCardNumber.setText(last4);
        viewHolder.tvType.setText(card.brand);

        return view;
    }
}
