package com.progettopdm.lyricbuddy.repository;

import com.progettopdm.lyricbuddy.services.MxmLyricsService;
import com.progettopdm.lyricbuddy.utils.Constants;

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

    public MxmLyricsService getMxmLyricsServiceWithRetrofit() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.MXM_BASE_URL).
                addConverterFactory(GsonConverterFactory.create()).build();
        return retrofit.create(MxmLyricsService.class);
    }

}
