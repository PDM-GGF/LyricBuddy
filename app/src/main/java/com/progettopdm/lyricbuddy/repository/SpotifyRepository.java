package com.progettopdm.lyricbuddy.repository;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.progettopdm.lyricbuddy.model.Album;
import com.progettopdm.lyricbuddy.model.response.FeaturedResponse;
import com.progettopdm.lyricbuddy.model.response.NewReleaseResponse;
import com.progettopdm.lyricbuddy.repository.callback.SpotifyCallback;
import com.progettopdm.lyricbuddy.services.SpotifyService;
import com.progettopdm.lyricbuddy.utils.ServiceLocator;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class SpotifyRepository implements ISpotifyRepository{

    private final SpotifyService spotifyService;

    private final Application application;
    CCAuthRepository ccAuthRepository;

    private final MutableLiveData<NewReleaseResponse> mNewReleaseLiveData;
    private final MutableLiveData<FeaturedResponse> mFeaturedPlaylistsLiveData;
    String authToken;



    public SpotifyRepository(Application application) {
        this.application = application;
        this.spotifyService = ServiceLocator.getInstance().getSpotifyServiceWithRetrofit();
        this.mNewReleaseLiveData = new MutableLiveData<>();
        this.mFeaturedPlaylistsLiveData = new MutableLiveData<>();
        this.ccAuthRepository = new CCAuthRepository(application);
    }

    public MutableLiveData<NewReleaseResponse> fetchNewReleases(String token) {

        Call<NewReleaseResponse> call = spotifyService.getNewReleases("IT",
                5,
                "Bearer " + token);

        call.enqueue(new Callback<NewReleaseResponse>() {
            @Override
            public void onResponse(@NonNull Call<NewReleaseResponse> call, @NonNull retrofit2.Response<NewReleaseResponse> response) {
                if (response.body() != null && response.isSuccessful()) {
                    List<Album> albumList = response.body().getAlbumWrapper().getAlbumList();
                    mNewReleaseLiveData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<NewReleaseResponse> call, Throwable t) {
                Log.d("FAILED: ", "NEW RELEASE FETCH");
            }

        });

        return mNewReleaseLiveData;
    }
}
