package com.progettopdm.lyricbuddy.repository;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;

import com.progettopdm.lyricbuddy.model.Response;
import com.progettopdm.lyricbuddy.services.CCAuthService;
import com.progettopdm.lyricbuddy.utils.ServiceLocator;

import retrofit2.Call;
import retrofit2.Callback;

public class CCAuthRepository {

    CCAuthService ccAuthService;
    CCAuthCallback ccAuthCallback;

    public CCAuthRepository() {
        this.ccAuthService = ServiceLocator.getInstance().getCCAuthServiceWithRetrofit();
    }

    public void authorize(){

        Call<Response> call = ccAuthService.getToken("client_credentials",
                "Basic Y2M0MWIzYzM3MTNhNGYyZmFiMjkyY2QxNjFhMmJlOGM6ODE2ZDM1NjM4OWMyNGMxY2I2MDExMTYxMjBkOTc4Yjc");

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(@NonNull Call<Response> call, @NonNull retrofit2.Response<Response> response) {
                if (response.body() != null && response.isSuccessful())
                Log.d("TOKEN: ", response.body().getAccessToken());
            }

            @Override
            public void onFailure(@NonNull Call<Response> call, @NonNull Throwable t) {
                Log.d("FAIL: ", t.getMessage());
            }
        });
    }
}
