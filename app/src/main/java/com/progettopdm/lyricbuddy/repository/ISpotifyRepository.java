package com.progettopdm.lyricbuddy.repository;

import androidx.lifecycle.MutableLiveData;

import com.progettopdm.lyricbuddy.model.response.NewReleaseResponse;

public interface ISpotifyRepository {
    MutableLiveData<NewReleaseResponse> fetchNewReleases(String token);
}
