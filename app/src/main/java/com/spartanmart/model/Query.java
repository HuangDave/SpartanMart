package com.spartanmart.model;

import com.spartanmart.server.ServerManager;

import retrofit2.Call;

/**
 * Created by David on 12/13/16.
 */

public class Query {

    protected static ServerManager manager = ServerManager.manager;

    public static Call<Product> get(String productId) {
        return manager.service.getProduct(manager.getToken(), productId);
    }
}
