package com.spartanmart.server;

import com.google.gson.internal.bind.ObjectTypeAdapter;
import com.spartanmart.model.Product;
import com.spartanmart.model.User;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * Created by David on 11/7/16.
 */
public interface SpartanMartAPI {

    interface AuthCallback {
        void onLoginSuccessful();
        void onLoginFailed(String localizedMessage);
    }

    /**
     *  Authentication Endpoints
     */
    @FormUrlEncoded
    @POST("auth/signup")
    Call<AuthToken> register(@Field("email") final String email,
                             @Field("password") final String password,
                             @FieldMap final Map<String, Object> params);

    @FormUrlEncoded
    @POST("auth/login")
    Call<AuthToken> authenticate(@Field("email") final String email,
                                 @Field("password") final String password);

    /**
     *
     * User Account Management Endpoints
     */
    @GET("users/{userId}")
    Call<User> getUser(@Header("Authorization") final String token,
                       @Path("userId") final String userId);

    @PUT("users/{userId}")
    Call<User> updateAccount(@Header("Authorization") final String token,
                             @Path("userId") final String userId,
                             @FieldMap final Map<String, Object> updates);

    /**
     * Payment Management Endpoints
     */
    @POST("users/{userId}/cards")
    Call<Map<String, Object>> addCard(@Header("Authorization") final String token,
                                      @Path("userId") final String userId,
                                      @FieldMap final Map<String, Object> card);

    @PUT("users/{userId}/cards/{cardId}")
    Call<Map<String, Object>> updateCard(@Header("Authorization") final String token,
                                         @Path("userId") final String userId,
                                         @Path("cardId") final String cardId,
                                         @FieldMap final Map<String, Object> updates);

    @GET("users/{userId}/cards/{cardId}")
    Call<Map<String, Object>> getCard(@Header("Authorization") final String token,
                                      @Path("userId") final String userId,
                                      @Path("cardId") final String cardId);

    @GET("users/{userId}/cards/")
    Call<List<Map<String, Object>>> listCards(@Header("Authorization") final String token,
                                              @Path("userId") final String userId);

    @DELETE("users/{userId}/cards/{cardId}")
    Call<String> removeCard(@Header("Authorization") final String token,
                            @Path("userId") final String userId,
                            @Path("cardId") final String cardId);

    /**
     *  Product Management Endpoints
     */
    @POST("products/{userId}")
    Call<Product> addProduct(@Header("Authorization") final String token,
                             @Path("userId") final String userId,
                             @FieldMap final Map<String, Object> product);

    @PUT("products/{userId}/{productId}")
    Call<Map<String, Object>> updateProduct(@Header("Authorization") final String token,
                                @Path("userId") final String userId,
                                @Path("productId") final String productId,
                                @FieldMap final Map<String, Object> updates);

    @GET("products/{userId}/list")
    Call<List<Product>> listProducts(@Header("Authorization") final String token,
                                     @Path("userId") final String userId);

    @GET("products/{productId}")
    Call<Product> getProduct(@Header("Authorization") final String token,
                             @Path("productId") final String productId);

    @DELETE("products/{userId}/{productId}")
    Call<Map<String, Object>> removeProduct(@Header("Authorization") final String token,
                                            @Path("userId") final String userId,
                                            @Path("productId") final String productId);

    /**
     * Transaction History Endpoints
     */
    @GET("transactions/{userId}/list")
    Call<List<Transaction>> transactionHistory(@Header("Authorization") final String token,
                                               @Path("userId") final String userId);

    @GET("transactions/{transactionId}")
    Call<Transaction> getTransaction(@Header("Authorization") final String token,
                                     @Path("transactionId") final String transactionId);
}
