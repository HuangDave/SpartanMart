package com.spartanmart.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.spartanmart.server.AuthToken;
import com.spartanmart.server.ServerManager;

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

    public interface RegistrationCallback {
        void onRegisterSuccessful();
        void onRegisterFailed(String localizedMessage);
    }

    public interface AuthCallback {
        void onLoginSuccessful();
        void onLoginFailed(String localizedMessage);
    }

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

    public static User currentUser() {
        return ServerManager.currentUser;
    }

    public static void login(final String email, final String password, final AuthCallback callback) {
        manager.api.authenticate(email, password).enqueue(new Callback<AuthToken>() {
            @Override
            public void onResponse(Call<AuthToken> call, Response<AuthToken> response) {
                if (response.isSuccessful()) {
                    AuthToken token = response.body();
                    manager.updateSessionToken(token);

                    manager.api.getUser("bearer " + token.token, token.user.uid).enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            if (response.code() == 200) {
                                manager.currentUser = response.body();
                                callback.onLoginSuccessful();
                            }
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            callback.onLoginFailed(t.getLocalizedMessage());
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<AuthToken> call, Throwable t) {
                callback.onLoginFailed(t.getLocalizedMessage());
            }
        });
    }

    public void register(final RegistrationCallback callback) {
        if (uid != null) {
            return;
        }

        manager.api.register(email, password, contact, name.get("first"), name.get("last")).enqueue(new Callback<AuthToken>() {
            @Override
            public void onResponse(Call<AuthToken> call, Response<AuthToken> response) {
                if (response.code() == 201) {
                    User.login(email, password, new AuthCallback() {
                        @Override
                        public void onLoginSuccessful() {
                            callback.onRegisterSuccessful();
                        }

                        @Override
                        public void onLoginFailed(String localizedMessage) {
                            // TODO: handle login failure after successful registration
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<AuthToken> call, Throwable t) {
                callback.onRegisterFailed(t.getLocalizedMessage());
            }
        });
    }

    public String fullName() {
        return name.get("first") + " " + name.get("last");
    }

    public String formattedContact() {
        String areaCode = contact.substring(0,3);
        String firstThree = contact.substring(3,6);
        String lastFour = contact.substring(6, contact.length());
        return "("+areaCode+")"+firstThree+"-"+lastFour;
    }

    public void addProduct(final Product product, Callback<Product> callback) {
        manager.api.addProduct(manager.getToken(), uid, product.serializedData()).enqueue(callback);
    }

    public void removeProduct(final String productId, Callback<Map<String, Object>> callback) {
        manager.api.removeProduct(manager.getToken(), uid, productId).enqueue(callback);
    }

    public void getAllProducts(Callback<List<Product>> callback) {
        manager.api.listProducts(manager.getToken(), uid).enqueue(callback);
    }

    public void update(Callback<Void> callback) {
        HashMap<String, Object> updates = new HashMap<>();
        updates.put("password", password);
        updates.put("contact", contact);
        manager.api.updateAccount(manager.getToken(), uid, updates).enqueue(callback);
    }
}
