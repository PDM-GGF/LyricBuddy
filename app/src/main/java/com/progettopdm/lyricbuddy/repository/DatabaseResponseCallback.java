package com.progettopdm.lyricbuddy.repository;

import com.progettopdm.lyricbuddy.model.Track;

import java.util.List;

public interface DatabaseResponseCallback {
    void onResponse(List<Track> TrackList);
    void onFailure(String msg);
}
