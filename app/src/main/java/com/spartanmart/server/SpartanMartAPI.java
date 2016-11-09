package com.spartanmart.server;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by David on 11/7/16.
 */
public interface SpartanMartAPI {

    interface AuthCallback {
        void onLoginSuccessful();
        void onLoginFailed(String localizedMessage);
    }

    @FormUrlEncoded
    @POST("users/auth")
    Call<AuthToken> authenticate(@Field("username") final String email, @Field("password") final String password);
}
