package com.spartanmart.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

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
}
