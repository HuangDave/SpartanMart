package com.spartanmart.server;

import android.support.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.spartanmart.model.User;

/**
 * Created by David on 11/8/16.
 */

public class AuthToken {

    @Expose
    @Nullable
    @SerializedName("user")
    public User user;

    @Expose
    @SerializedName("token")
    public String token;

    public AuthToken(String tokenString) {
        this.token = tokenString;
    }
}
