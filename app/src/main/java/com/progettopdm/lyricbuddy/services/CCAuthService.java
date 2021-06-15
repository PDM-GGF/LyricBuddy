package com.progettopdm.lyricbuddy.services;

import retrofit2.Call;
import com.progettopdm.lyricbuddy.model.response.AuthResponse;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface CCAuthService {
    static final String CLIENT_ID = "Y2M0MWIzYzM3MTNhNGYyZmFiMjky" +
            "Y2QxNjFhMmJlOGM6ODE2ZDM1NjM4OW" +
            "MyNGMxY2I2MDExMTYxMjBkOTc4Yjc";

    static final String BASE_URL = "https://accounts.spotify.com";

    @FormUrlEncoded
    @POST("/api/token")
    Call<AuthResponse> getToken(@Field("grant_type") String grantType,
                                @Header("Authorization") String clientId);
}
