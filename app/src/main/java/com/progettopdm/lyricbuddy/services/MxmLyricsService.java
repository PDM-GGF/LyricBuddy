package com.progettopdm.lyricbuddy.services;

import com.progettopdm.lyricbuddy.model.MxmTrack;

import retrofit2.Call;
import retrofit2.http.Query;
import retrofit2.http.GET;

public interface MxmLyricsService {
    @GET("track.lyrics.get")
    //Call<MxmTrack>
    Call<MxmTrack> getTrack(@Query("apikey") String apikey,
                            @Query("track_id") int track_id
                            );
}