package com.spartanmart.server;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by David on 11/7/16.
 */
public class ServerManager {

    public static final ServerManager manager = new ServerManager();

    private final String baseURL = "https://spartanmarttest.herokuapp.com/";
    private Retrofit retrofit;
    private AuthToken mAuthToken;

    public Context mContext;
    public SpartanMartAPI service;


    private ServerManager() {
        retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(SpartanMartAPI.class);
        getSessionToken();
    }

    public static ServerManager sharedManager() {
        return manager;
    }

    public void updateSessionToken(AuthToken token) {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(mContext);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("com.spartanmart.session_token", token.token);
        editor.commit();
    }

    private void getSessionToken() {
        SharedPreferences settings = PreferenceManager
                .getDefaultSharedPreferences(mContext);
        String tokenString = settings.getString("com.spartanmart.session_token", ""/*default value*/);
        mAuthToken = new AuthToken(tokenString);
    }

    public void logoutFromCurrentSession() {

    }

}
