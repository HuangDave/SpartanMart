package com.spartanmart.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.spartanmart.server.AuthToken;
import com.spartanmart.server.ServerManager;
import com.spartanmart.server.SpartanMartAPI;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by David on 10/21/16.
 */
public class User extends DBObject {

    @Expose
    @SerializedName("email")
    public String email;

    @Expose
    @SerializedName("password")
    public String password;

    @Expose
    @SerializedName("name")
    public HashMap<String, String> name;

    @Expose
    @SerializedName("contact")
    public String contact;

    @Expose
    @SerializedName("products")
    public List<String> products;

    public static void register(final String email, final String password, final SpartanMartAPI.AuthCallback callback) {

    }

    public static void login(final String email, final String password, final SpartanMartAPI.AuthCallback callback) {

        final ServerManager manager = ServerManager.manager;
        Call<AuthToken> auth = manager.service.authenticate(email, password);

        auth.enqueue(new Callback<AuthToken>() {

            @Override
            public void onResponse(Call<AuthToken> call, Response<AuthToken> response) {
                if (response.isSuccessful()) {
                    manager.updateSessionToken(response.body());
                    callback.onLoginSuccessful();
                }
            }

            @Override
            public void onFailure(Call<AuthToken> call, Throwable t) {
                t.printStackTrace();
                callback.onLoginFailed(t.getLocalizedMessage());
            }
        });
    }
}
