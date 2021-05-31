package com.progettopdm.lyricbuddy.repository;

import android.util.Log;

import com.progettopdm.lyricbuddy.model.MxmTrack;
import com.progettopdm.lyricbuddy.services.MxmMatcherService;
import com.progettopdm.lyricbuddy.utils.Constants;
import com.progettopdm.lyricbuddy.utils.ServiceLocator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MxmMatcherRepository {

    private MxmMatcherService MxmMatcherService;
    private MxmLyricsCallback MxmMatcherCallback;

    public MxmMatcherRepository(MxmLyricsCallback mxmMatcherCallback) {
        this.MxmMatcherService = ServiceLocator.getInstance().getMxmMatcherServiceWithRetrofit();
        this.MxmMatcherCallback = MxmMatcherCallback;
    }


    public void fetchTrackId() {
        String q_track = "paraocchi";
        String q_artist = "blanco";

        Call<MxmTrack> call = MxmMatcherService.getTrackId(Constants.MXM_API_KEY, q_track, q_artist);

        call.enqueue(new Callback<MxmTrack>() {
            @Override
            public void onResponse(Call<MxmTrack> call, Response<MxmTrack> response) {
                if(response.body() != null && response.isSuccessful()){
                    Log.d("ID: ", String.valueOf(response.body().getMessage().getBody().getTrack().getTrack_id()));
                    //MxmMatcherCallback.onResponse(String.valueOf(response.body().getMessage().getBody().getTrack().getTrack_id()));
                }
            }

            @Override
            public void onFailure(Call<MxmTrack> call, Throwable t) {
                Log.d("MXM ERROR: ", t.getMessage());
                MxmMatcherCallback.onFailure(t.getMessage());
            }
        });
    }
}
