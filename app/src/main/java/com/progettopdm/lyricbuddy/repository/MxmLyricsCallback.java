package com.progettopdm.lyricbuddy.repository;

public interface MxmLyricsCallback {
    void onLyricsGet(String lyrics);
    void onLyricsFailure(String msg);

}
