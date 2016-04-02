package com.undot.androidtemplate.network;

import com.undot.androidtemplate.network.requests.LoginRequest;
import com.undot.androidtemplate.network.responses.LoginResponse;
import com.undot.androidtemplate.utils.Constants;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Dani Goland on 02/04/2016.
 */
public interface RestApi {

    @POST(Constants.LOGIN)
    Call<LoginResponse> loginUser(@Body LoginRequest loginRequest);
}
