package com.spartanmart.model;

import android.media.Image;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;
import java.util.concurrent.Semaphore;

/**
 * Created by David on 10/21/16.
 */

public class Product extends Object {

    protected static DatabaseReference productRef = baseRef.child("products");
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
        final Semaphore sema = new Semaphore(0);
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
                sema.release();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("", databaseError.toString());
            }
        });

        try {
            sema.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save() {
        data.put("description", description);
        data.put("price", price);
        data.put("udpatedAt", new Date());
        super.save();
    }
}
