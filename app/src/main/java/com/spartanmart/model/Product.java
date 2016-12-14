package com.spartanmart.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.spartanmart.server.ServerManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by David on 10/21/16.
 */
public class Product extends DBObject {

    @Expose
    @SerializedName("sellerId")
    public String sellerId;

    @Expose
    @SerializedName("image")
    public String image;

    @Expose
    @SerializedName("title")
    public String title;

    @Expose
    @SerializedName("description")
    public String description;

    @Expose
    @SerializedName("price")
    public Double price;

    @Expose
    @SerializedName("tags")
    public List<String> tags;

    public static Call<Product> add(Map<String, Object> info) {
        User currentUser = ServerManager.currentUser;
        return manager.service.addProduct(manager.getToken(), currentUser.uid, info);
    }

    public void update() {
        HashMap<String, Object> updates = new HashMap<>();
        updates.put("description", description);
        manager.service.updateProduct(manager.getToken(), uid, updates);
    }
}
