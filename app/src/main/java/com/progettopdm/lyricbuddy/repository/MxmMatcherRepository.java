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

    private MxmMatcherService mxmMatcherService;
    private MxmMatcherCallback mxmMatcherCallback;

    public MxmMatcherRepository(MxmMatcherCallback mxmMatcherCallback) {
        this.mxmMatcherService = ServiceLocator.getInstance().getMxmMatcherServiceWithRetrofit();
        this.mxmMatcherCallback = mxmMatcherCallback;
    }


    public void fetchTrackId(String q_track, String q_artist) {

        Call<MxmTrack> call = mxmMatcherService.getTrackId(Constants.MXM_API_KEY, q_track, q_artist);

        call.enqueue(new Callback<MxmTrack>() {
            @Override
            public void onResponse(Call<MxmTrack> call, Response<MxmTrack> response) {
                if(response.body() != null && response.isSuccessful()){
                    mxmMatcherCallback.onIdGet(response.body().getMessage().getBody().getTrack().getTrack_id());

                }else{
                    Log.d("MATCHER ERROR", String.valueOf(response.body().getMessage()));
                }
            }

            @Override
            public void onFailure(Call<MxmTrack> call, Throwable t) {
                Log.d("MXM ERROR: ", t.getMessage());
                mxmMatcherCallback.onMatcherFailure(t.getMessage());
            }
        });
    }
}
