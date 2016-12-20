package com.spartanmart.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by David on 12/13/16.
 */

public class Transaction extends DBObject {

    @Expose
    @SerializedName("chargeId")
    public String chargeId;

    @Expose
    @SerializedName("sellerId")
    public String sellerId;

    @Expose
    @SerializedName("buyerId")
    public String buyerId;

    @Expose
    @SerializedName("amount")
    public Double amount;

    @Expose
    @SerializedName("status")
    public String status;

}
