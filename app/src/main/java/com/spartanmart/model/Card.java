package com.spartanmart.model;

import android.support.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by David on 12/19/16.
 */

public class Card {

    @Expose
    @SerializedName("id")
    public String cardId;

    @Expose
    @SerializedName("brand")
    public String brand;

    @Nullable
    @SerializedName("number")
    public String number;

    @Expose
    @SerializedName("last4")
    public String last4;

    @Expose
    @SerializedName("exp_month")
    public String expMonth;

    @Expose
    @SerializedName("exp_year")
    public String expYear;

    @Nullable
    @SerializedName("cvc")
    public String cvc;

    public Map<String, String> serializedData() {
        HashMap<String, String> data = new HashMap<>();
        if (number != null) {
            data.put("number", number);
        }
        data.put("exp_month", expMonth);
        data.put("exp_year", expYear);
        if (cvc != null) {
            data.put("cvc", cvc);
        }
        return data;
    }
}
