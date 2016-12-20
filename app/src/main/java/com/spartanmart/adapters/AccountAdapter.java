package com.spartanmart.adapters;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.spartanmart.R;
import com.spartanmart.model.User;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by David on 12/19/16.
 */
public class AccountAdapter extends BaseAdapter {

    public static class ItemViewHolder {

        @Nullable
        @BindView(R.id.tvText)
        TextView tvText;

        public ItemViewHolder(View v) {
            ButterKnife.bind(this, v);
        }
    }

    public final static int OPTIONCHANGEIMAGE = 3;
    public final static int OPTIONCHANGEPASSWORD = 4;
    public final static int OPTIONCHANGECONTACT = 5;

    protected Context mContext;

    protected User mUser;

    public AccountAdapter(Context c, User user) {
        mContext = c;
        mUser = user;
    }

    @Override
    public int getCount() {
        return 6;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ItemViewHolder viewHolder;

        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            view = inflater.inflate(R.layout.layout_account_item, viewGroup, false);
            viewHolder = new ItemViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ItemViewHolder)view.getTag();
        }

        switch (i) {
            case 0: {
                viewHolder.tvText.setText(mUser.email);
            } break;
            case 1: {
                viewHolder.tvText.setText(mUser.fullName());
            } break;
            case 2: {
                viewHolder.tvText.setText(mUser.formattedContact());
            } break;
            case 3: {
                viewHolder.tvText.setText("Change profile image");
            } break;
            case 4: {
                viewHolder.tvText.setText("Change password");
            } break;
            case 5: {
                viewHolder.tvText.setText("Change contact");
            } break;
        }

        return view;
    }
}