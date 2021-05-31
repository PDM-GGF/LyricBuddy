package com.progettopdm.lyricbuddy.repository;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;

import com.progettopdm.lyricbuddy.model.Album;
import com.progettopdm.lyricbuddy.model.response.AuthResponse;
import com.progettopdm.lyricbuddy.model.response.NewReleaseResponse;
import com.progettopdm.lyricbuddy.repository.callback.SpotifyCallback;
import com.progettopdm.lyricbuddy.services.SpotifyService;
import com.progettopdm.lyricbuddy.utils.ServiceLocator;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class SpotifyRepository {

    SpotifyService spotifyService;
    SpotifyCallback spotifyCallback;
    CCAuthRepository ccAuthRepository;

    public SpotifyRepository(SpotifyCallback spotifyCallback, Application application) {
        this.spotifyService = ServiceLocator.getInstance().getSpotifyServiceWithRetrofit();
        this.spotifyCallback = spotifyCallback;
    }

    public void getNewReleases(String authToken) {


        Call<NewReleaseResponse> call = spotifyService.getNewReleases("IT",
                5,
                "Bearer " + authToken);

        call.enqueue(new Callback<NewReleaseResponse>() {
            @Override
            public void onResponse(@NonNull Call<NewReleaseResponse> call, @NonNull retrofit2.Response<NewReleaseResponse> response) {
                if (response.body() != null && response.isSuccessful()) {
                    List<Album> albumList = response.body().getAlbumList();
                    spotifyCallback.onNewReleaseResponse(albumList);
                }
            }

            @Override
            public void onFailure(Call<NewReleaseResponse> call, Throwable t) {
                spotifyCallback.onNewReleaseFailure("nah");
            }

        });
    }
}
