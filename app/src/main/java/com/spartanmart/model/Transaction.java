package com.spartanmart.model;

import android.support.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Map;

/**
 * Created by David on 12/13/16.
 */

public class Transaction extends DBObject {

    @Expose
    @Nullable
    @SerializedName("chargeId")
    // NULL if status is not 'succeeded'.
    public String chargeId;

    @Expose
    @SerializedName("sellerId")
    public String sellerId;

    @Expose
    @SerializedName("buyerId")
    public String buyerId;

    @Expose
    @SerializedName("product")
    public Map<String, Object> product;

    @Expose
    @SerializedName("status")
    public String status;

}
