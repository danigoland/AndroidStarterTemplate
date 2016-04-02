package com.undot.androidtemplate.network;

import com.undot.androidtemplate.utils.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 0503337710 on 02/04/2016.
 */
public class NetworkManager {
    private static NetworkManager instance = null;
    RestApi service;
    Retrofit retrofit;
    protected NetworkManager() {
        // Exists only to defeat instantiation.
    }
    public static NetworkManager getInstance() {
        if(instance == null) {
            instance = new NetworkManager();

            instance.retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.SERVER_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            instance.service = instance.retrofit.create(RestApi.class);
        }
        return instance;
    }
}