package com.progettopdm.lyricbuddy.repository;

import androidx.lifecycle.LiveData;

import com.progettopdm.lyricbuddy.model.Track;

import java.util.List;

public interface IDatabaseRepository {

    LiveData<List<Track>> fetchFavorites();
}
