package com.progettopdm.lyricbuddy.repository;

import android.util.Log;

import com.progettopdm.lyricbuddy.model.MxmTrack;
import com.progettopdm.lyricbuddy.services.MxmLyricsService;
import com.progettopdm.lyricbuddy.utils.Constants;
import com.progettopdm.lyricbuddy.utils.ServiceLocator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MxmLyricsRepository {

    private MxmLyricsService mxmLyricsService;
    private MxmLyricsCallback mxmLyricsCallback;

    public MxmLyricsRepository(MxmLyricsCallback mxmLyricsCallback) {
        this.mxmLyricsService = ServiceLocator.getInstance().getMxmServiceWithRetrofit();
        this.mxmLyricsCallback = mxmLyricsCallback;
    }


    public void fetchLyrics() {
        int id = 215778095;
        Call<MxmTrack> call = mxmLyricsService.getTrack(Constants.MXM_API_KEY, id);

        call.enqueue(new Callback<MxmTrack>() {
            @Override
            public void onResponse(Call<MxmTrack> call, Response<MxmTrack> response) {
                if(response.body() != null && response.isSuccessful()){
                    response.body().setId(id);
                    Log.d("TRACK: ", response.body().getLyrics());
                    mxmLyricsCallback.onResponse(response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<MxmTrack> call, Throwable t) {
                Log.d("MXM ERROR: ", t.getMessage());
                mxmLyricsCallback.onFailure(t.getMessage());
            }
        });
    }
}