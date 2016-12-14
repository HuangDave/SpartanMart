package com.spartanmart.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.spartanmart.server.AuthToken;
import com.spartanmart.server.ServerManager;
import com.spartanmart.server.SpartanMartAPI;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public void addProduct(final Map<String, Object> info, Callback<Product> callback) {
        manager.service.addProduct(manager.getToken(), uid, info).enqueue(callback);
    }

    public void removeProduct(final String productId, Callback<Map<String, Object>> callback) {
        manager.service.removeProduct(manager.getToken(), uid, productId).enqueue(callback);
    }

    public void getAllProducts(Callback<List<Product>> callback) {
        manager.service.listProducts(manager.getToken(), uid).enqueue(callback);
    }

    public void update(Callback<User> callback) {
        HashMap<String, Object> updates = new HashMap<>();
        updates.put("password", password);
        updates.put("contact", contact);
        manager.service.updateAccount(manager.getToken(), uid, updates).enqueue(callback);
    }
}
