package com.progettopdm.lyricbuddy.utils;

import android.app.Application;

import com.progettopdm.lyricbuddy.database.TrackRoomDatabase;
import com.progettopdm.lyricbuddy.model.Track;
import com.progettopdm.lyricbuddy.services.MxmMatcherService;
import com.progettopdm.lyricbuddy.services.MxmLyricsService;
import com.progettopdm.lyricbuddy.services.CCAuthService;
import com.progettopdm.lyricbuddy.services.SpotifyService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceLocator {

    private static ServiceLocator instance = null;

    private ServiceLocator() {
    }

    public static ServiceLocator getInstance() {
        if (instance == null) {
            synchronized (ServiceLocator.class) {
                instance = new ServiceLocator();
            }
        }
        return instance;
    }

    public CCAuthService getCCAuthServiceWithRetrofit() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://accounts.spotify.com").
                addConverterFactory(GsonConverterFactory.create()).build();
        return retrofit.create(CCAuthService.class);
    }

    public SpotifyService getSpotifyServiceWithRetrofit() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.spotify.com/v1/").
                addConverterFactory(GsonConverterFactory.create()).build();
        return retrofit.create((SpotifyService.class));
    }

    public MxmLyricsService getMxmLyricsServiceWithRetrofit() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.MXM_BASE_URL).
                addConverterFactory(GsonConverterFactory.create()).build();
        return retrofit.create(MxmLyricsService.class);
    }
    public MxmMatcherService getMxmMatcherServiceWithRetrofit() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.MXM_BASE_URL).
                addConverterFactory(GsonConverterFactory.create()).build();
        return retrofit.create(MxmMatcherService.class);
    }

    public TrackRoomDatabase getTrackDao (Application application){
        return TrackRoomDatabase.getDatabase(application);
    }
}
