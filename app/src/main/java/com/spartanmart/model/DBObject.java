package com.spartanmart.model;

import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.spartanmart.server.ServerManager;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by David on 10/21/16.
 */

public class DBObject {

    protected static ServerManager manager = ServerManager.manager;

    @Expose
    @SerializedName("uid")
    public String uid;

    @Expose
    @SerializedName("exists")
    public boolean exists;

    @Expose
    @SerializedName("createdAt")
    public Date createdAt;

    @Expose
    @SerializedName("updatedAt")
    public Date updatedAt;
}

