package com.progettopdm.lyricbuddy.ui.search;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.bumptech.glide.Glide;
import com.progettopdm.lyricbuddy.model.Album;
import com.progettopdm.lyricbuddy.model.GenericImage;
import com.progettopdm.lyricbuddy.model.Playlist;
import com.progettopdm.lyricbuddy.model.TrackContainer;
import com.progettopdm.lyricbuddy.model.response.AlbumTrackListResponse;
import com.progettopdm.lyricbuddy.model.response.FeaturedResponse;
import com.progettopdm.lyricbuddy.model.response.NewReleaseResponse;
import com.progettopdm.lyricbuddy.model.response.PlaylistTrackListResponse;
import com.progettopdm.lyricbuddy.model.response.SearchTracksResponse;
import com.progettopdm.lyricbuddy.repository.ICCAuthRepository;
import com.progettopdm.lyricbuddy.repository.ISpotifyRepository;

import java.util.List;

public class SearchViewModel extends AndroidViewModel {


    private MutableLiveData<SearchTracksResponse> mSearchedPlaylistLiveData;

    MutableLiveData<String> spotiToken;

    private ISpotifyRepository iSpotifyRepository;
    private ICCAuthRepository iccAuthRepository;

    public SearchViewModel(@NonNull Application application, ISpotifyRepository iSpotifyRepository, ICCAuthRepository iccAuthRepository ) {
        super(application);
        this.iSpotifyRepository = iSpotifyRepository;
        this.iccAuthRepository = iccAuthRepository;
    }

    public MutableLiveData<String> getSpotiToken() {
        if(spotiToken == null){
            loadSpotiToken();
            }
        return spotiToken;
    }

    public LiveData<SearchTracksResponse> getmSearchedTracksLiveData(String query, String token) {
        loadSearchTracks(query, token);
        return mSearchedPlaylistLiveData;
    }

    private void loadSpotiToken() {
        spotiToken = iccAuthRepository.authorize();
    }

    private void loadSearchTracks(String query, String token){
        mSearchedPlaylistLiveData = iSpotifyRepository.fetchSearchedTracks(query, token);
    }

    public void loadImagesFromUrl(List<? extends TrackContainer> tcList){
        for(TrackContainer tc : tcList){
            for(GenericImage i : tc.getImgList()){
                i.setImg(Glide.with(getApplication().getApplicationContext()).load(i.getImgUrl()));
            }
        }
    }
}