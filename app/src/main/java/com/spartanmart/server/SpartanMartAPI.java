package com.spartanmart.server;

import com.spartanmart.model.Product;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by David on 11/7/16.
 */
public interface SpartanMartAPI {

    interface AuthCallback {
        void onLoginSuccessful();
        void onLoginFailed(String localizedMessage);
    }

    /*
        Authentication
     */
    @FormUrlEncoded
    @POST("auth/signup")
    Call<AuthToken> register(@Field("username") final String email, @Field("password") final String password, @FieldMap final Map<String, Object> params);

    @FormUrlEncoded
    @POST("auth/login")
    Call<AuthToken> authenticate(@Field("email") final String email, @Field("password") final String password);

    /*
        Products
     */
    @GET("products")
    Call<List<Product>> listProducts(@Header("Authorization") final String token, @QueryMap final Map<String, Object> params);

    @POST("products/{id}")
    Call<Product> add(@Header("Authorization") final String token, @FieldMap final Map<String, Object> params);
}
