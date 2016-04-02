package com.undot.androidtemplate.network;

import com.undot.androidtemplate.network.requests.LoginRequest;
import com.undot.androidtemplate.network.responses.LoginResponse;
import com.undot.androidtemplate.utils.Constants;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Dani Goland on 02/04/2016.
 */
public interface RestApi {

    @GET(Constants.LOGIN)
    Call<LoginResponse> loginUser(@Query("token") String token);
}
