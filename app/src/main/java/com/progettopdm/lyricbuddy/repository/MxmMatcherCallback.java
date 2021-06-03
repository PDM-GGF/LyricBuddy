package com.progettopdm.lyricbuddy.repository;

public interface MxmMatcherCallback {
    void onResponse(String id);
    void onFailure(String msg);
}
