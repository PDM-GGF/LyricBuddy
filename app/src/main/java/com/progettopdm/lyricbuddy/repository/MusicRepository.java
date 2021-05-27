package com.progettopdm.lyricbuddy.repository;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.room.Dao;

import java.util.List;

import com.progettopdm.lyricbuddy.database.MusicDatabase;
import com.progettopdm.lyricbuddy.database.TrackDao;
import com.progettopdm.lyricbuddy.model.Track;
import com.progettopdm.lyricbuddy.utils.Constants;
import com.progettopdm.lyricbuddy.utils.ServiceLocator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MusicRepository implements IMusicRepository {
/*
    private final TrackDao tTrackDao;
    //private final ResponseCallback responseCallback;
    private long lastUpdate = 0;


    public MusicRepository(ResponseCallback responseCallback, Application application, long lastUpdate) {
        //this.newsService = ServiceLocator.getInstance().getNewsServiceWithRetrofit();
        MusicDatabase db = ServiceLocator.getInstance().getMusicDao(application);
        this.tTrackDao = db.TrackDao();
        //this.responseCallback = responseCallback;
        this.lastUpdate = lastUpdate;
    }

    @Override
    public void fetchMusic(String , int page) {

        long currentTime = System.currentTimeMillis();

        // It gets news from the Web Service if the last download of news has been performed more than one hour ago
        if (currentTime - lastUpdate > Constants.FRESH_TIMEOUT) {
            Call<Response> call = MusicService.METODO IN SERVICE (STRING, Constants.LUNGHEZZA, page, Constants.KEY);

            call.enqueue(new Callback<Response>() {
                @Override
                public void onResponse(@NonNull Call<Response> call, @NonNull retrofit2.Response<Response> response) {
                    if (response.body() != null && response.isSuccessful()) {
                        lastUpdate = System.currentTimeMillis();
                        List<Track> trackList = response.body().LISTA DELLE TRACK();
                        saveDataInDatabase(trackList);
                        responseCallback.onResponse(trackList, lastUpdate);
                    }
                }

                @Override
                public void onFailure(@NonNull Call<Response> call, @NonNull Throwable t) {
                    responseCallback.onFailure(t.getMessage());
                }
            });
            // It gets news from the local database
        } else {
            Log.d(TAG, "Data read from the local database");
            readDataFromDatabase();
        }
    }










    private void readDataFromDatabase() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                responseCallback.onResponse(TrackDao.getAllTracks(), lastUpdate);
            }
        };
        new Thread(runnable).start();
    }

    private void saveDataInDatabase(List<Track> TrackList) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                tTrackDao.deleteAll();
                tTrackDao.insertTracks(TrackList);
            }
        };
        new Thread(runnable).start();
    }*/
}
