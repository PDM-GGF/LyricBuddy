package com.progettopdm.lyricbuddy.ui.home;

import android.app.Application;
import android.util.JsonReader;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.progettopdm.lyricbuddy.model.Album;
import com.progettopdm.lyricbuddy.model.GenericImage;
import com.progettopdm.lyricbuddy.model.Playlist;
import com.progettopdm.lyricbuddy.model.Track;
import com.progettopdm.lyricbuddy.model.TrackContainer;
import com.progettopdm.lyricbuddy.model.response.FeaturedResponse;
import com.progettopdm.lyricbuddy.model.response.NewReleaseResponse;
import com.progettopdm.lyricbuddy.model.response.TrackListResponse;
import com.progettopdm.lyricbuddy.repository.CCAuthRepository;
import com.progettopdm.lyricbuddy.repository.ICCAuthRepository;
import com.progettopdm.lyricbuddy.repository.ISpotifyRepository;
import com.progettopdm.lyricbuddy.repository.SpotifyRepository;
import com.progettopdm.lyricbuddy.repository.callback.SpotifyCallback;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class HomeViewModel extends AndroidViewModel {

    private MutableLiveData<NewReleaseResponse> mNewReleasesLiveData;
    private MutableLiveData<FeaturedResponse> mFeaturedPlaylistsLiveData;
    String mFeaturedMessage;
    TrackContainer mClickedTrackContainer;
    MutableLiveData<String> spotiToken;

    private ISpotifyRepository iSpotifyRepository;
    private ICCAuthRepository iccAuthRepository;

    public HomeViewModel(@NonNull Application application, ISpotifyRepository iSpotifyRepository, ICCAuthRepository iccAuthRepository ) {
        super(application);
        this.iSpotifyRepository = iSpotifyRepository;
        this.iccAuthRepository = iccAuthRepository;
    }

    public MutableLiveData<String> getSpotiToken() {
        loadSpotiToken();
        return spotiToken;
    }



    public TrackContainer getmClickedTrackContainer() {
        return mClickedTrackContainer;
    }

    public LiveData<NewReleaseResponse> getmNewReleases(String token) {
        if(mNewReleasesLiveData == null) {
            mNewReleasesLiveData = new MutableLiveData<>();
            loadNewReleases(token);

        }
        return mNewReleasesLiveData;
    }

    public LiveData<FeaturedResponse> getmFeaturedPlaylists(String token) {
        if(mFeaturedPlaylistsLiveData == null) {
            mFeaturedPlaylistsLiveData = new MutableLiveData<>();
            loadFeaturedPlaylists(token);
        }
        return mFeaturedPlaylistsLiveData;
    }

    public void loadAlbumTrackLists(List<Album> albumList, String token) {
        for(Album a : albumList) {
            iSpotifyRepository.fetchAlbumTrackList(a.getId(), token).observeForever(new Observer<TrackListResponse>() {
                @Override
                public void onChanged(TrackListResponse trackListResponse) {
                    for(Track t : trackListResponse.getTrackList()) {
                        t.setAlbum(a.getId());
                    }
                    a.setTrackList(trackListResponse.getTrackList());
                }
            });
        }
    }

    private void loadSpotiToken() {
        spotiToken = iccAuthRepository.authorize();
    }

    private void loadNewReleases(String token) {
        mNewReleasesLiveData = iSpotifyRepository.fetchNewReleases(token);
    }

    private void loadFeaturedPlaylists(String token){
        mFeaturedPlaylistsLiveData = iSpotifyRepository.fetchFeaturedPlaylists(token);
    }


    public void loadImagesFromUrl(List<? extends TrackContainer> tcList){

        for(TrackContainer tc : tcList){
            for(GenericImage i : tc.getImgList()){
                i.setImg(Glide.with(getApplication().getApplicationContext()).load(i.getImgUrl()));
            }
        }
    }
}