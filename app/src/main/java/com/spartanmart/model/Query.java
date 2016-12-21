package com.spartanmart.model;

import com.spartanmart.server.ServerManager;
import com.spartanmart.server.SpartanMartAPI;

import java.util.List;

import retrofit2.Callback;

/**
 * Created by David on 12/18/16.
 */

public class Query {

    private static SpartanMartAPI mAPI = ServerManager.manager.api;
    private static String mAuthToken = ServerManager.manager.getToken();

    public static class User {

        public static void queryById(final String userId, Callback<com.spartanmart.model.User> callback) {
            mAPI.getUser(mAuthToken, userId).enqueue(callback);
        }
    }

    public static class Product {

        public static void byUserId(final String userId, Callback<List<com.spartanmart.model.Product>> callback) {
            mAPI.listProducts(mAuthToken, userId).enqueue(callback);
        }

        public static void recent(final int limit, Callback<List<com.spartanmart.model.Product>> callback) {
            mAPI.queryRecentProducts(mAuthToken, limit).enqueue(callback);
        }

        public static void queryByKeyword(final String keyword, final int limit, Callback<List<com.spartanmart.model.Product>> callback) {
            mAPI.queryByKeyword(mAuthToken, keyword, limit).enqueue(callback);
        }
    }

    public static class Transaction {

        public static void queryById(final String transactionId, Callback<com.spartanmart.model.Transaction> callback) {
            mAPI.getTransaction(mAuthToken, transactionId).enqueue(callback);
        }

        public static void byUserId(final String userId, Callback<List<com.spartanmart.model.Transaction>> callback) {
            mAPI.listTransactions(mAuthToken, userId).enqueue(callback);
        }
    }
}
