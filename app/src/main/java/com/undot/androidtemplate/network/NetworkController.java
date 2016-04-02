package com.undot.androidtemplate.network;

import com.undot.androidtemplate.network.requests.LoginRequest;
import com.undot.androidtemplate.network.responses.LoginResponse;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

/**
 * Created by 0503337710 on 03/04/2016.
 */
public class NetworkController {
    private static NetworkController instance = null;
    NetworkManager networkManager;
    protected NetworkController() {
        // Exists only to defeat instantiation.
    }
    public static NetworkController getInstance() {
        if(instance == null) {
            instance = new NetworkController();



            instance.networkManager = NetworkManager.getInstance();
        }
        return instance;
    }

    public void login(final String token)
    {
        instance.networkManager.service.loginUser(token).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                Timber.i("Json Response %s",response.isSuccessful());
                LoginResponse loginResponse =response.body();
                loginResponse.setSuccess(true);
                EventBus.getDefault().post(loginResponse);
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                LoginResponse loginResponse = new LoginResponse();
                loginResponse.setSuccess(false);
                EventBus.getDefault().post(loginResponse);

            }
        });
    }
}

