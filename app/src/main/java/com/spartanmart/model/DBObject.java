package com.spartanmart.model;

import android.util.Log;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by David on 10/21/16.
 */

public class DBObject {

    public String id;
    protected HashMap<String, java.lang.Object> data = new HashMap<>();
    protected boolean exists = false;
    protected Date createdAt;
    protected Date updatedAt;

    public DBObject(String id) {
        this.id = id;
    }

    public DBObject() {
        id = null;
    }

    public void fetchData() { }

    public String getObjectId() {
        return data.get("id").toString();
    }

    public Date getCreatedAt() { return new Date(data.get("createdAy").toString()); }

    public Date getUpdatedAt() { return new Date(data.get("updatedAt").toString()); }

    public void save() {

    }
}

