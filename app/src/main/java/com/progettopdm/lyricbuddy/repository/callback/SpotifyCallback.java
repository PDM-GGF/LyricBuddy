package com.progettopdm.lyricbuddy.repository.callback;

import com.progettopdm.lyricbuddy.model.Album;

import java.util.List;

public interface SpotifyCallback {
    void onNewReleaseResponse(List<Album> newReleasesList);
    void onNewReleaseFailure(String msg);
}
