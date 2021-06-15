package com.progettopdm.lyricbuddy.ui.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.progettopdm.lyricbuddy.repository.ICCAuthRepository;
import com.progettopdm.lyricbuddy.repository.ISpotifyRepository;

import org.jetbrains.annotations.NotNull;

public class HomeViewModelFactory implements ViewModelProvider.Factory {

    private final Application application;
    private final ISpotifyRepository iSpotifyRepository;
    private final ICCAuthRepository iccAuthRepository;

    public HomeViewModelFactory(Application application, ISpotifyRepository iSpotifyRepository, ICCAuthRepository iccAuthRepository) {
        this.application = application;
        this.iSpotifyRepository = iSpotifyRepository;
        this.iccAuthRepository = iccAuthRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull @NotNull Class<T> modelClass) {
        return (T) new HomeViewModel(application, iSpotifyRepository, iccAuthRepository);
    }
}
