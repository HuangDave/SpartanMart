package com.spartanmart.model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by David on 10/21/16.
 */

public class Object {

    protected static DatabaseReference baseRef =FirebaseDatabase.getInstance().getReference();
    protected DatabaseReference ref;
    protected String id;
    protected HashMap<String, java.lang.Object> data;
    protected boolean exists = false;

    public Object(String id) {
        this.id = id;
    }

    public Object() {
        id = null;
    }

    public void fetchData() {

    }

    public String getObjectId() {
        return data.get("id").toString();
    }

    public Date getCreatedAt() { return new Date(data.get("createdAy").toString()); }

    public Date getUpdatedAt() { return new Date(data.get("updatedAt").toString()); }

    public void save() {
        if (!exists) {
            id = ref.push().getKey();
        }
        ref.updateChildren(data);
    }
}

