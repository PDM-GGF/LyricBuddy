package com.progettopdm.lyricbuddy.repository;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.progettopdm.lyricbuddy.model.Album;
import com.progettopdm.lyricbuddy.model.Track;
import com.progettopdm.lyricbuddy.model.response.FeaturedResponse;
import com.progettopdm.lyricbuddy.model.response.NewReleaseResponse;
import com.progettopdm.lyricbuddy.model.response.TrackListResponse;
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
    private final MutableLiveData<TrackListResponse> mTrackListLiveData;


    public SpotifyRepository(Application application) {
        this.application = application;
        this.spotifyService = ServiceLocator.getInstance().getSpotifyServiceWithRetrofit();
        this.mNewReleaseLiveData = new MutableLiveData<>();
        this.mFeaturedPlaylistsLiveData = new MutableLiveData<>();
        this.mTrackListLiveData = new MutableLiveData<>();
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
                    mNewReleaseLiveData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<NewReleaseResponse> call, Throwable t) {
                Log.d("FAILED: ", "NEW RELEASE FETCH" + t.getMessage());
            }

        });

        return mNewReleaseLiveData;
    }

    @Override
    public MutableLiveData<TrackListResponse> fetchAlbumTrackList(String albumId, String token) {

        Call<TrackListResponse> call = spotifyService.getAlbumTrackList(albumId,
                "IT",
                50,
                "Bearer " + token);

        call.enqueue(new Callback<TrackListResponse>() {
            @Override
            public void onResponse(@NonNull Call<TrackListResponse> call, @NonNull retrofit2.Response<TrackListResponse> response) {
                if (response.body() != null && response.isSuccessful()) {
                    mTrackListLiveData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<TrackListResponse> call, Throwable t) {
                Log.d("FAILED: ", "NEW RELEASE FETCH " + t.getMessage());
            }

        });

        return mTrackListLiveData;
    }

    @Override
    public MutableLiveData<FeaturedResponse> fetchFeaturedPlaylists(String token) {
        Call<FeaturedResponse> call = spotifyService.getFeaturedPlaylists("IT",
                5,
                "Bearer " + token);

        call.enqueue(new Callback<FeaturedResponse>() {
            @Override
            public void onResponse(@NonNull Call<FeaturedResponse> call, @NonNull retrofit2.Response<FeaturedResponse> response) {
                if (response.body() != null && response.isSuccessful()) {
                    mFeaturedPlaylistsLiveData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<FeaturedResponse> call, Throwable t) {
                Log.d("FAILED: ", "FEATURED PLAYLISTS FETCH" + t.getMessage());
            }

        });

        return mFeaturedPlaylistsLiveData;
    }
}
