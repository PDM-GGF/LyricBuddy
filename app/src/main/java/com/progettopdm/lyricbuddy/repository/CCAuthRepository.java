package com.progettopdm.lyricbuddy.repository;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;

import com.progettopdm.lyricbuddy.model.response.AuthResponse;
import com.progettopdm.lyricbuddy.repository.callback.CCAuthCallback;
import com.progettopdm.lyricbuddy.services.CCAuthService;
import com.progettopdm.lyricbuddy.utils.ServiceLocator;

import retrofit2.Call;
import retrofit2.Callback;
import com.progettopdm.lyricbuddy.utils.Constants;

public class CCAuthRepository {

    CCAuthService ccAuthService;
    private final CCAuthCallback ccAuthCallback;

    public CCAuthRepository(CCAuthCallback ccAuthCallback, Application application) {
        this.ccAuthService = ServiceLocator.getInstance().getCCAuthServiceWithRetrofit();
        this.ccAuthCallback = ccAuthCallback;
    }

    public void authorize(){

        Call<AuthResponse> call = ccAuthService.getToken("client_credentials",
                "Basic " +  Constants.SPOTIFY_CLIENT_ID);

        call.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(@NonNull Call<AuthResponse> call, @NonNull retrofit2.Response<AuthResponse> response) {
                if (response.body() != null && response.isSuccessful()) {
                    String token = response.body().getAccessToken();
                    ccAuthCallback.onAuthResponse(token);
                }
            }

            @Override
            public void onFailure(@NonNull Call<AuthResponse> call, @NonNull Throwable t) {
                ccAuthCallback.onAuthFailure(t.getMessage());
            }
        });

    }
}
