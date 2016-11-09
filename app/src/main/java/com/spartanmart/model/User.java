package com.spartanmart.model;

import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.spartanmart.server.ServerManager;
import com.spartanmart.server.SpartanMartAuth;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by David on 10/21/16.
 */
public class User extends DBObject {

    public String uid;

    @Expose
    @SerializedName("email")
    public String email;

    @Expose
    @SerializedName("password")
    public String password;

    @Expose
    @SerializedName("first_name")
    public String firstName;

    @Expose
    @SerializedName("last_name")
    public String lastName;

    @Expose
    @SerializedName("contact")
    public String contact;

    @Expose
    @SerializedName("products")
    public Product[] products;

    public static void create(final String email, final String password, final SpartanMartAuth.AuthCallback callback) {

    }

    public static void login(final String email, final String password, final SpartanMartAuth.AuthCallback callback) {
        final ServerManager manager = ServerManager.manager;
        Call<SpartanMartAuth.AuthToken> auth = ServerManager.sharedManager().service.authenticate(email, password);
        auth.enqueue(new Callback<SpartanMartAuth.AuthToken>() {
            @Override
            public void onResponse(Call<SpartanMartAuth.AuthToken> call, Response<SpartanMartAuth.AuthToken> response) {
                if (response.isSuccessful()) {
                    manager.mAuthToken = response.body();
                    Log.d("TOKEN", manager.mAuthToken.token);
                    callback.onLoginSuccessful();
                }
            }

            @Override
            public void onFailure(Call<SpartanMartAuth.AuthToken> call, Throwable t) {
                t.printStackTrace();
                callback.onLoginFailed(t.getLocalizedMessage());
            }
        });
    }

    @Override
    public void save() {

    }

    public void logout() {

    }
}
