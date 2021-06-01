package com.progettopdm.lyricbuddy.ui.home;

import android.app.Application;
import android.util.JsonReader;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

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
    MutableLiveData<TrackContainer> mClickedTrackContainer;
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



    public LiveData<TrackContainer> getmClickedTrackContainer() {
        return mClickedTrackContainer;
    }

    public LiveData<NewReleaseResponse> getmNewReleases(String token) {
        if(mNewReleasesLiveData == null) {
            mNewReleasesLiveData = new MutableLiveData<>();
            loadNewReleases(token);

        }
        return mNewReleasesLiveData;
    }

    /*public LiveData<FeaturedResponse> getFeaturedPlaylists() throws IOException {
        if(mFeaturedPlaylistsLiveData == null) {
            mFeaturedPlaylistsLiveData = new MutableLiveData<>();
            loadFeaturedPlaylists();
        }
        loadImagesFromUrl(mFeaturedPlaylistsLiveData.getValue().getPlaylistWrapper().getPlaylistList());
        return mFeaturedPlaylistsLiveData;
    }*/

    public void loadTrackList(List<? extends TrackContainer> trackContainer) throws IOException {
        InputStream fileInputStream = null;
        JsonReader jsonReader = null;

        for (TrackContainer tc : trackContainer){
            try {
                fileInputStream = getApplication().getAssets().open("tracklist.json");
                jsonReader = new JsonReader(new InputStreamReader(fileInputStream, "UTF-8"));
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
                TrackListResponse response = new Gson().fromJson(bufferedReader, TrackListResponse.class);
                tc.setTrackList(response.getTrackList());

            } catch (IOException e) {
                //e.printStackTrace();
            }finally {
                jsonReader.close();
                fileInputStream.close();
            }
        }

    }

    private void loadSpotiToken() {
        spotiToken = iccAuthRepository.authorize();
    }

    private void loadNewReleases(String token) {
        mNewReleasesLiveData = iSpotifyRepository.fetchNewReleases(token);
    }

    /*private void loadFeaturedPlaylists() throws IOException {
        InputStream fileInputStream = null;
        JsonReader jsonReader = null;
        try {
            fileInputStream = getApplication().getAssets().open("featured.json");
            jsonReader = new JsonReader(new InputStreamReader(fileInputStream, "UTF-8"));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            FeaturedResponse response = new Gson().fromJson(bufferedReader, FeaturedResponse.class);
            Log.d("PLAYLIST RESPONSE: ", response.getPlaylistWrapper().getPlaylistList().get(0).getName());
            mFeaturedMessage = response.getMessage();
            mFeaturedPlaylists.setValue(response.getPlaylistWrapper().getPlaylistList());
            loadImagesFromUrl(mFeaturedPlaylists.getValue());
        } catch (IOException e) {
            //e.printStackTrace();
        }finally {
            jsonReader.close();
            fileInputStream.close();
        }
    }*/


    public void loadImagesFromUrl(List<? extends TrackContainer> tcList){

        for(TrackContainer tc : tcList){
            for(GenericImage i : tc.getImgList()){
                i.setImg(Glide.with(getApplication().getApplicationContext()).load(i.getImgUrl()));
            }
        }
    }
}