package com.spartanmart.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public static void query(final String productId, Callback<Product> callback) {
        manager.api.getProduct(manager.getToken(), productId).enqueue(callback);
    }

    public static Product query(final String productId) {
        Product product = null;
        try {
            product = manager.api.getProduct(manager.getToken(), productId).execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return product;
    }

    public static void query(final Map<String, Object> options, Callback<List<Product>> callback) {

    }

    protected Map<String, Object> serializedData() {
        HashMap<String, Object> data = new HashMap<>();
        //updates.put("image", image);
        data.put("title", title);
        data.put("description", description);
        data.put("price", price);
        return data;
    }

    public void update(Callback<Map<String, Object>> callback) {
        manager.api.updateProduct(manager.getToken(), User.currentUser().uid, uid, serializedData()).enqueue(callback);
    }

    public void delete(Callback<Map<String, Object>> callback) {
        manager.api.removeProduct(manager.getToken(), User.currentUser().uid, uid).enqueue(callback);
    }
}
