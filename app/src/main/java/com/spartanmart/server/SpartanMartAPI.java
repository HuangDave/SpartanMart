package com.spartanmart.server;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;

/**
 * Created by David on 11/7/16.
 */
public interface SpartanMartAPI {

    @FormUrlEncoded
    @POST("users/auth")
    //@Header({ "Content-Type: application/x-www-form-urlencoded"})
    Call<SpartanMartAuth.AuthToken> authenticate(@Field("username") final String email, @Field("password") final String password);
}
