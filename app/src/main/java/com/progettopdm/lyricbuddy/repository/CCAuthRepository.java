package com.progettopdm.lyricbuddy.repository;

import android.util.Log;

import androidx.annotation.NonNull;

import com.progettopdm.lyricbuddy.model.AuthResponse;
import com.progettopdm.lyricbuddy.services.CCAuthService;
import com.progettopdm.lyricbuddy.utils.ServiceLocator;

import retrofit2.Call;
import retrofit2.Callback;
import com.progettopdm.lyricbuddy.utils.Constants;

public class CCAuthRepository {

    CCAuthService ccAuthService;
    CCAuthCallback ccAuthCallback;

    public CCAuthRepository() {
        this.ccAuthService = ServiceLocator.getInstance().getCCAuthServiceWithRetrofit();
    }

    public void authorize(){

        Call<AuthResponse> call = ccAuthService.getToken("client_credentials",
                "Basic " +  Constants.SPOTIFY_CLIENT_ID);

        call.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(@NonNull Call<AuthResponse> call, @NonNull retrofit2.Response<AuthResponse> response) {
                if (response.body() != null && response.isSuccessful())
                Log.d("TOKEN: ", response.body().getAccessToken());
            }

            @Override
            public void onFailure(@NonNull Call<AuthResponse> call, @NonNull Throwable t) {
                Log.d("FAIL: ", t.getMessage());
            }
        });

    }
}
