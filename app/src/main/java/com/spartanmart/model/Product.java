package com.spartanmart.model;

import android.media.Image;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;

/**
 * Created by David on 10/21/16.
 */

public class Product extends Object {

    protected DatabaseReference productRef = baseRef.child("products");
    protected DatabaseReference ref;
    protected String sellerId;
    protected Image image;
    protected Double price;
    protected String description;

    public Product(String id) {
        super(id);
        ref = productRef.child(id);
    }

    public Product(User seller, Double price, String description) {
        super();
        this.sellerId = seller.id;
        this.price = price;
        this.description = description;
    }

    @Override
    public void fetchData() {
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Product p = dataSnapshot.getValue(Product.class);
                    sellerId = p.sellerId;
                    image = p.image;
                    price = p.price;
                    description = p.description;
                    exists = true;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("", databaseError.toString());
            }
        });
    }

    @Override
    public void save() {
        data.put("description", description);
        data.put("price", price);
        data.put("udpatedAt", new Date());
        super.save();
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Image getImage() {
        return image;
    }

    public Double getPrice() {
        return price;
    }
}
