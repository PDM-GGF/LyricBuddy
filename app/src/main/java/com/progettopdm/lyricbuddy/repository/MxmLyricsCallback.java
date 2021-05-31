package com.progettopdm.lyricbuddy.repository;

import com.progettopdm.lyricbuddy.model.mxmResponse.MxmTrack;

public interface MxmLyricsCallback {
    void onResponse(String track);
    void onFailure(String msg);
}
