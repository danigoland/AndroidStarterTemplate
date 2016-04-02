package com.undot.androidtemplate.network.responses;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 0503337710 on 02/04/2016.
 */
public class LoginResponse {
    private String token;

    @SerializedName("response")
    private boolean success;
    //private User user;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }
}
