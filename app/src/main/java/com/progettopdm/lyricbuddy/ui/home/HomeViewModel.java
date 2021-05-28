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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class HomeViewModel extends AndroidViewModel {

    private MutableLiveData<List<Album>> mNewReleases;
    private MutableLiveData<List<Playlist>> mFeaturedPlaylists;
    String featuredMessage;

    public HomeViewModel(@NonNull Application application) {
        super(application);
    }


    public LiveData<List<Album>> getNewReleases() throws IOException {
        if(mNewReleases == null) {
            mNewReleases = new MutableLiveData<List<Album>>();
            loadNewReleases();
        }
        loadImagesFromUrl(mNewReleases.getValue());
        //servirà per implementazione api (per ora prendiamo la tracklist del primo album di new releases da tracklist.json)
        loadTrackList(mNewReleases.getValue());
        return mNewReleases;
    }

    public LiveData<List<Playlist>> getFeaturedPlaylists() throws IOException {
        if(mFeaturedPlaylists == null) {
            mFeaturedPlaylists = new MutableLiveData<List<Playlist>>();
            loadFeaturedPlaylists();
        }
        loadImagesFromUrl(mFeaturedPlaylists.getValue());
        return mFeaturedPlaylists;
    }

    private void loadTrackList(List<? extends TrackContainer> trackContainer) throws IOException {
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

    private void loadNewReleases() throws IOException {
        InputStream fileInputStream = null;
        JsonReader jsonReader = null;
        try {
            fileInputStream = getApplication().getAssets().open("newreleases.json");
            jsonReader = new JsonReader(new InputStreamReader(fileInputStream, "UTF-8"));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            NewReleaseResponse response = new Gson().fromJson(bufferedReader, NewReleaseResponse.class);
            mNewReleases.setValue(response.getAlbumList());
        } catch (IOException e) {
            //e.printStackTrace();
        }finally {
            jsonReader.close();
            fileInputStream.close();
        }

    }

    private void loadFeaturedPlaylists() throws IOException {
        InputStream fileInputStream = null;
        JsonReader jsonReader = null;
        try {
            fileInputStream = getApplication().getAssets().open("featured.json");
            jsonReader = new JsonReader(new InputStreamReader(fileInputStream, "UTF-8"));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            FeaturedResponse response = new Gson().fromJson(bufferedReader, FeaturedResponse.class);
            Log.d("PLAYLIST RESPONSE: ", response.getPlaylistWrapper().getPlaylistList().get(0).getName());
            featuredMessage = response.getMessage();
            mFeaturedPlaylists.setValue(response.getPlaylistWrapper().getPlaylistList());
            loadImagesFromUrl(mFeaturedPlaylists.getValue());
        } catch (IOException e) {
            //e.printStackTrace();
        }finally {
            jsonReader.close();
            fileInputStream.close();
        }
    }

    private void loadImagesFromUrl(List<? extends TrackContainer> tcList){

        for(TrackContainer tc : tcList){
            for(GenericImage i : tc.getImgList()){
                i.setImg(Glide.with(getApplication().getApplicationContext()).load(i.getImgUrl()));
            }
        }
    }
}