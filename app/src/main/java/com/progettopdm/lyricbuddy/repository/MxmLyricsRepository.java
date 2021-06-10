package com.progettopdm.lyricbuddy.repository;

import android.util.Log;

import com.progettopdm.lyricbuddy.model.MxmTrack;
import com.progettopdm.lyricbuddy.services.MxmLyricsService;
import com.progettopdm.lyricbuddy.utils.Constants;
import com.progettopdm.lyricbuddy.utils.ServiceLocator;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MxmLyricsRepository {

    private MxmLyricsService mxmLyricsService;
    private MxmLyricsCallback mxmLyricsCallback;

    public MxmLyricsRepository(MxmLyricsCallback mxmLyricsCallback) {
        this.mxmLyricsService = ServiceLocator.getInstance().getMxmLyricsServiceWithRetrofit();
        this.mxmLyricsCallback = mxmLyricsCallback;
    }


    public void fetchLyrics(int id) {
        Call<MxmTrack> call = mxmLyricsService.getTrack(Constants.MXM_API_KEY, id);

        call.enqueue(new Callback<MxmTrack>() {
            @Override
            public void onResponse(@NotNull Call<MxmTrack> call, @NotNull Response<MxmTrack> response) {
                if(response.body() != null && response.isSuccessful()){
                    mxmLyricsCallback.onLyricsGet((response.body().getLyrics()));
                }
            }

            @Override
            public void onFailure(@NotNull Call<MxmTrack> call, @NotNull Throwable t) {
                Log.d("MXM ERROR: ", t.getMessage());
                mxmLyricsCallback.onLyricsFailure(t.getMessage());
            }
        });
    }
}
