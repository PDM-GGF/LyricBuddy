package com.progettopdm.lyricbuddy.services;

import com.progettopdm.lyricbuddy.model.MxmTrack;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MxmMatcherService {
    @GET("matcher.track.get")
    Call<MxmTrack> getTrackId(@Query("apikey") String apikey,
                            @Query("q_track") String q_track,
                            @Query("q_artist") String q_artist
                            );

}