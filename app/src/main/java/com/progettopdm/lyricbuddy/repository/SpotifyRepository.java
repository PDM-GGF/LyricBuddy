package com.progettopdm.lyricbuddy.repository;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.progettopdm.lyricbuddy.model.response.FeaturedResponse;
import com.progettopdm.lyricbuddy.model.response.NewReleaseResponse;
import com.progettopdm.lyricbuddy.model.response.AlbumTrackListResponse;
import com.progettopdm.lyricbuddy.model.response.PlaylistTrackListResponse;
import com.progettopdm.lyricbuddy.services.SpotifyService;
import com.progettopdm.lyricbuddy.utils.ServiceLocator;

import retrofit2.Call;
import retrofit2.Callback;

public class SpotifyRepository implements ISpotifyRepository{

    private final SpotifyService spotifyService;

    private final Application application;
    CCAuthRepository ccAuthRepository;

    private final MutableLiveData<NewReleaseResponse> mNewReleaseLiveData;
    private final MutableLiveData<FeaturedResponse> mFeaturedPlaylistsLiveData;
    private final MutableLiveData<AlbumTrackListResponse> mAlbumTrackListLiveData;
    private final MutableLiveData<PlaylistTrackListResponse> mPlaylistTrackListLiveData;


    public SpotifyRepository(Application application) {
        this.application = application;
        this.spotifyService = ServiceLocator.getInstance().getSpotifyServiceWithRetrofit();
        this.mNewReleaseLiveData = new MutableLiveData<>();
        this.mFeaturedPlaylistsLiveData = new MutableLiveData<>();
        this.mAlbumTrackListLiveData = new MutableLiveData<>();
        this.mPlaylistTrackListLiveData = new MutableLiveData<PlaylistTrackListResponse>();
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
    public MutableLiveData<AlbumTrackListResponse> fetchAlbumTrackList(String albumId, String token) {

        Call<AlbumTrackListResponse> call = spotifyService.getAlbumTrackList(albumId,
                "IT",
                50,
                "Bearer " + token);

        call.enqueue(new Callback<AlbumTrackListResponse>() {
            @Override
            public void onResponse(@NonNull Call<AlbumTrackListResponse> call, @NonNull retrofit2.Response<AlbumTrackListResponse> response) {
                if (response.body() != null && response.isSuccessful()) {
                    mAlbumTrackListLiveData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<AlbumTrackListResponse> call, Throwable t) {
                Log.d("FAILED: ", "NEW RELEASE FETCH " + t.getMessage());
            }

        });

        return mAlbumTrackListLiveData;
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

    @Override
    public MutableLiveData<PlaylistTrackListResponse> fetchPlaylistTracklist(String playlistId, String token) {

        Call<PlaylistTrackListResponse> call = spotifyService.getPlaylistTrackList(playlistId,
                "IT",
                "Bearer " + token);

        call.enqueue(new Callback<PlaylistTrackListResponse>() {
            @Override
            public void onResponse(@NonNull Call<PlaylistTrackListResponse> call, @NonNull retrofit2.Response<PlaylistTrackListResponse> response) {
                if (response.body() != null && response.isSuccessful()) {
                    mPlaylistTrackListLiveData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<PlaylistTrackListResponse> call, Throwable t) {
                Log.d("FAILED: ", "PLAYLIST TRACKLIST FETCH " + t.getMessage());
            }

        });

        return mPlaylistTrackListLiveData;

    }
}