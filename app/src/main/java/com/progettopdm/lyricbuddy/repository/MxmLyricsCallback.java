package com.progettopdm.lyricbuddy.repository;

public interface MxmLyricsCallback {
    void onResponse(String track);
    void onFailure(String msg);
}
