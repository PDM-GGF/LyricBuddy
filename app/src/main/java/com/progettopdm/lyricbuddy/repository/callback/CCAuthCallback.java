package com.progettopdm.lyricbuddy.repository.callback;

public interface CCAuthCallback {
    void onAuthResponse(String token);
    void onAuthFailure(String msg);
}
