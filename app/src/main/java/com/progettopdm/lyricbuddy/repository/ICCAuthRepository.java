package com.progettopdm.lyricbuddy.repository;

import androidx.lifecycle.MutableLiveData;

public interface ICCAuthRepository {
    MutableLiveData<String> authorize();
}
