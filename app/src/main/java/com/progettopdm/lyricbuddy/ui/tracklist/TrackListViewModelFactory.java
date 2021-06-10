package com.progettopdm.lyricbuddy.ui.tracklist;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.progettopdm.lyricbuddy.repository.ICCAuthRepository;
import com.progettopdm.lyricbuddy.repository.ISpotifyRepository;
import com.progettopdm.lyricbuddy.ui.home.HomeViewModel;

import org.jetbrains.annotations.NotNull;

public class TrackListViewModelFactory implements ViewModelProvider.Factory {


    public TrackListViewModelFactory(){
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull @NotNull Class<T> modelClass) {
        return (T) new TrackListViewModel();
    }
}
