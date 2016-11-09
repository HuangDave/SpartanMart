package com.spartanmart.server;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by David on 11/7/16.
 */
public class ServerManager {

    public static final ServerManager manager = new ServerManager();

    private final String baseURL = "https://spartanmarttest.herokuapp.com/";

    private Retrofit retrofit;

    public SpartanMartAPI service;

    public SpartanMartAuth.AuthToken mAuthToken;

    private ServerManager() {
        retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(SpartanMartAPI.class);
    }

    public static ServerManager sharedManager() {
        return manager;
    }
}
