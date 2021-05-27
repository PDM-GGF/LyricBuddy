package com.progettopdm.lyricbuddy.utils;

import android.app.Application;

import com.progettopdm.lyricbuddy.database.MusicDatabase;
import com.progettopdm.lyricbuddy.services.CCAuthService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceLocator {

    private static ServiceLocator instance = null;

    private ServiceLocator() {}

    public static ServiceLocator getInstance() {
        if (instance == null) {
            synchronized(ServiceLocator.class) {
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


    /*metodo per tutto il database o solo per le track?*/
    public MusicDatabase getMusicDao(Application application) {
        return MusicDatabase.getDatabase(application);
    }
}
