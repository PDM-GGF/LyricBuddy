package com.progettopdm.lyricbuddy.ui.home;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.bumptech.glide.Glide;
import com.progettopdm.lyricbuddy.model.Album;
import com.progettopdm.lyricbuddy.model.GenericImage;
import com.progettopdm.lyricbuddy.model.Playlist;
import com.progettopdm.lyricbuddy.model.Track;
import com.progettopdm.lyricbuddy.model.TrackContainer;
import com.progettopdm.lyricbuddy.model.response.FeaturedResponse;
import com.progettopdm.lyricbuddy.model.response.NewReleaseResponse;
import com.progettopdm.lyricbuddy.model.response.AlbumTrackListResponse;
import com.progettopdm.lyricbuddy.model.response.PlaylistTrackListResponse;
import com.progettopdm.lyricbuddy.model.response.SearchTracksResponse;
import com.progettopdm.lyricbuddy.model.response.wrappers.TrackWrapper;
import com.progettopdm.lyricbuddy.repository.ICCAuthRepository;
import com.progettopdm.lyricbuddy.repository.ISpotifyRepository;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends AndroidViewModel {

    private MutableLiveData<NewReleaseResponse> mNewReleasesLiveData;
    private MutableLiveData<FeaturedResponse> mFeaturedPlaylistsLiveData;
    private MutableLiveData<AlbumTrackListResponse> mAlbumTracklistLiveData;
    private MutableLiveData<PlaylistTrackListResponse> mPlaylistTracklistLiveData;

    TrackContainer mClickedTrackContainer;
    MutableLiveData<String> spotiToken;

    private ISpotifyRepository iSpotifyRepository;
    private ICCAuthRepository iccAuthRepository;

    public HomeViewModel(@NonNull Application application, ISpotifyRepository iSpotifyRepository, ICCAuthRepository iccAuthRepository ) {
        super(application);
        this.iSpotifyRepository = iSpotifyRepository;
        this.iccAuthRepository = iccAuthRepository;
        mAlbumTracklistLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<String> getSpotiToken() {
        if(spotiToken == null){
            loadSpotiToken();
            }
        return spotiToken;
    }

    public TrackContainer getmClickedTrackContainer() {
        return mClickedTrackContainer;
    }

    public void setTokenToNull() {
        this.spotiToken = null;
    }

    public void setTrackListToNull(int type) {

        //type = 0 -> album
        //type = 1 -> playlist

        switch (type){
            case 0:
                mAlbumTracklistLiveData.postValue(null);
                break;
            case 1:
                mPlaylistTracklistLiveData.postValue(null);
        }
    }

    public LiveData<NewReleaseResponse> getmNewReleases(String token) {
        if(mNewReleasesLiveData == null) {
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

    public LiveData<AlbumTrackListResponse> getmAlbumTracklistLiveData(Album album, String token) {
        loadAlbumTrackList(album, token);
        return mAlbumTracklistLiveData;
    }

    public LiveData<PlaylistTrackListResponse> getmPlaylistTracklistLiveData(Playlist playlist, String token) {
        loadPlaylistTracklist(playlist, token);
        return mPlaylistTracklistLiveData;
    }

    private void loadAlbumTrackList(Album album, String token) {

        String id = null;

        if (album != null) {
            id = album.getId();
        }

        mAlbumTracklistLiveData = iSpotifyRepository.fetchAlbumTrackList(id, token);
    }

    public void loadPlaylistTracklist(Playlist playlist, String token) {

        String id = null;

        if (playlist != null) {
            id = playlist.getId();
        }

        mPlaylistTracklistLiveData = iSpotifyRepository.fetchPlaylistTracklist(id, token);

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