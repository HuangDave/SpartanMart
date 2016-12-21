package com.spartanmart.server;

import com.spartanmart.model.Card;
import com.spartanmart.model.Product;
import com.spartanmart.model.Transaction;
import com.spartanmart.model.User;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by David on 11/7/16.
 */
public interface SpartanMartAPI {

    /**
     *  Authentication Endpoints
     */
    @FormUrlEncoded
    @POST("auth/signup")
    Call<AuthToken> register(@Field("email") final String email,
                             @Field("password") final String password,
                             @Field("contact") final String contact,
                             @Field("first_name") final String first_name,
                             @Field("last_name") final String last_name);

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

    @FormUrlEncoded
    @PUT("users/password/{userId}")
    Call<Void> updatePassword(@Header("Authorization") final String token,
                              @Path("userId") final String userId,
                              @FieldMap final Map<String, String> updates);

    @FormUrlEncoded
    @PUT("users/contact/{userId}")
    Call<Void> updateContact(@Header("Authorization") final String token,
                              @Path("userId") final String userId,
                              @FieldMap final Map<String, String> updates);

    @FormUrlEncoded
    @PUT("users/{userId}")
    Call<Void> updateAccount(@Header("Authorization") final String token,
                             @Path("userId") final String userId,
                             @FieldMap final Map<String, Object> updates);

    /**
     * Payment Management Endpoints
     */
    @FormUrlEncoded
    @POST("users/{userId}/cards")
    Call<Map<String, Object>> addCard(@Header("Authorization") final String token,
                                      @Path("userId") final String userId,
                                      @FieldMap final Map<String, String> card);

    @FormUrlEncoded
    @PUT("users/{userId}/cards/{cardId}")
    Call<Map<String, Object>> updateCard(@Header("Authorization") final String token,
                                         @Path("userId") final String userId,
                                         @Path("cardId") final String cardId,
                                         @FieldMap final Map<String, String> updates);

    @GET("users/{userId}/cards/{cardId}")
    Call<Map<String, Object>> getCard(@Header("Authorization") final String token,
                                      @Path("userId") final String userId,
                                      @Path("cardId") final String cardId);

    @GET("users/{userId}/cards/")
    Call<List<Card>> listCards(@Header("Authorization") final String token,
                              @Path("userId") final String userId);

    @DELETE("users/{userId}/cards/{cardId}")
    Call<String> removeCard(@Header("Authorization") final String token,
                            @Path("userId") final String userId,
                            @Path("cardId") final String cardId);

    /**
     *  Product Management Endpoints
     */
    @FormUrlEncoded
    @POST("products/{userId}")
    Call<Product> addProduct(@Header("Authorization") final String token,
                             @Path("userId") final String userId,
                             @FieldMap final Map<String, Object> product);

    @FormUrlEncoded
    @PUT("products/{userId}/{productId}")
    Call<Map<String, Object>> updateProduct(@Header("Authorization") final String token,
                                            @Path("userId") final String userId,
                                            @Path("productId") final String productId,
                                            @FieldMap final Map<String, Object> updates);

    @GET("products/{productId}")
    Call<Product> getProduct(@Header("Authorization") final String token,
                             @Path("productId") final String productId);

    @DELETE("products/{userId}/{productId}")
    Call<Map<String, Object>> removeProduct(@Header("Authorization") final String token,
                                            @Path("userId") final String userId,
                                            @Path("productId") final String productId);

    /**
     * Product Query
     */

    @GET("products/{userId}/list")
    Call<List<Product>> listProducts(@Header("Authorization") final String token,
                                     @Path("userId") final String userId);

    @GET("products/recent?")
    Call<List<Product>> queryRecentProducts(@Header("Authorization") final String token,
                                            @Query("limit") final int limit);

    @GET("products/search?")
    Call<List<Product>> queryByKeyword(@Header("Authorization") final String token,
                                       @Query("keyword") final String keyword,
                                       @Query("limit") final int limit);

    /**
     * Transaction History Endpoints
     */

    @POST("transactions/create/{userId}/{productId}")
    Call<Void> createTransaction(@Header("Authorization") final String token,
                                 @Path("userId") final String userId,
                                 @Path("productId") final String productId);

    @GET("transactions/{userId}/list")
    Call<List<Transaction>> transactionHistory(@Header("Authorization") final String token,
                                               @Path("userId") final String userId);

    @GET("transactions/{transactionId}")
    Call<Transaction> getTransaction(@Header("Authorization") final String token,
                                     @Path("transactionId") final String transactionId);

    @GET("transactions/{userId}/list")
    Call<List<Transaction>> listTransactions(@Header("Authorization") final String token,
                                             @Path("userId") final String userId);
}
