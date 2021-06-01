package com.progettopdm.lyricbuddy.services;

import com.progettopdm.lyricbuddy.model.response.NewReleaseResponse;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface SpotifyService {

    @GET("browse/new-releases")
    Call<NewReleaseResponse> getNewReleases(@Query("country") String country,
                                @Query("limit") int limit,
                                @Header("Authorization") String authToken);
}
