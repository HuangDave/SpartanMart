package com.spartanmart.server;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.spartanmart.model.User;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by David on 11/7/16.
 *
 * ServerManager is a singleton class.
 */
public class ServerManager {

    // ServerManager Singleton
    public static final ServerManager manager = new ServerManager();

    // Base server URL.
    private final String baseURL = "https://spartanmartserver.herokuapp.com/";

    // Used to generate an api for https requests to server.
    private Retrofit retrofit;

    // Instance for making api calls. Created from Retrofit.
    public SpartanMartAPI api;

    // JWT for server authorization.
    private AuthToken mAuthToken;

    // Application context.
    private Context mContext;

    // Currently logged in user.
    public static User currentUser;

    /**
     * Initializer
     * Initializes Retrofit and generates a set of API endpoints for accessing server.
     */
    private ServerManager() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        api = retrofit.create(SpartanMartAPI.class);
    }

    /**
     * Context is set on app launch.
     *
     * @param mContext - Application context.
     */
    public void setContext(Context mContext) {
        this.mContext = mContext;
        getSessionToken();
    }

    /**
     * Called when a user is successfully authenticated and receives a JWT.
     * Stores the JWT in SharedPreferences for future authentication.
     *
     * @param token - JWT received on successful authentication.
     */
    public void updateSessionToken(AuthToken token) {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(mContext);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("com.spartanmart.session_token", token.token);
        editor.commit();
        mAuthToken = token;
    }

    /**
     * Retrieve the stored JWT from Shared Preferences/
     */
    private void getSessionToken() {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(mContext);
        String tokenString = settings.getString("com.spartanmart.session_token", "");
        if (tokenString != "") {
            mAuthToken = new AuthToken(tokenString);
        }
    }

    /**
     * Getter for mAuthToken
     *
     * @return Returns the current JWT.
     */
    public String getToken() {
        return "bearer " + mAuthToken.token;
    }
}
