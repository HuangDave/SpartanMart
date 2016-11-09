package com.spartanmart.server;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.spartanmart.model.User;

/**
 * Created by David on 11/7/16.
 */

public class SpartanMartAuth {

    public interface AuthCallback {
        void onLoginSuccessful();
        void onLoginFailed(String localizedMessage);
    }

    public class AuthToken {

        //@Expose
        //@SerializedName("user")
        public User user;

        //@Expose
        //@SerializedName("token")
        public String token;
    }
}
