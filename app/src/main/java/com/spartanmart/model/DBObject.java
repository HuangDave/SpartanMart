package com.spartanmart.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.spartanmart.server.ServerManager;

import java.util.Date;

/**
 * Created by David on 10/21/16.
 */

public class DBObject {

    protected static ServerManager manager = ServerManager.manager;

    @Expose
    @SerializedName("uid")
    public String uid;

    @Expose
    @SerializedName("createdAt")
    public Date createdAt;

    @Expose
    @SerializedName("updatedAt")
    public Date updatedAt;
}

