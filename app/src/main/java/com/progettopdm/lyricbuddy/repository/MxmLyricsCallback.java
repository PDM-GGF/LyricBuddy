package com.progettopdm.lyricbuddy.repository;

import com.progettopdm.lyricbuddy.model.mxmResponse.MxmTrack;

public interface MxmLyricsCallback {
    void onLyricsGet(String lyrics);
    void onFailure(String msg);
}
