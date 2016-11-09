package com.spartanmart.model;

import android.media.Image;
import android.util.Log;

import java.util.Date;
import java.util.concurrent.Semaphore;

/**
 * Created by David on 10/21/16.
 */

public class Product extends DBObject {

    protected String sellerId;
    public Image image;
    public String name;
    public Double price;
    public String description;

    public Product() {
        super();
    }

    public Product(String id) {
        super(id);
    }

    public Product(User seller, Double price, String description) {
        super();
        this.sellerId = seller.id;
        this.price = price;
        this.description = description;
    }

    @Override
    public void fetchData() {

    }

    @Override
    public void save() {
        data.put("description", description);
        data.put("price", price);
        data.put("udpatedAt", new Date());
        super.save();
    }
}
