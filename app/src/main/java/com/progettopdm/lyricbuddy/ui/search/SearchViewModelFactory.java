package com.progettopdm.lyricbuddy.ui.search;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.progettopdm.lyricbuddy.repository.ICCAuthRepository;
import com.progettopdm.lyricbuddy.repository.ISpotifyRepository;
import com.progettopdm.lyricbuddy.ui.home.HomeViewModel;

import org.jetbrains.annotations.NotNull;

public class SearchViewModelFactory implements ViewModelProvider.Factory {

    private final Application application;
    private final ISpotifyRepository iSpotifyRepository;
    private final ICCAuthRepository iccAuthRepository;

    public SearchViewModelFactory(Application application, ISpotifyRepository iSpotifyRepository, ICCAuthRepository iccAuthRepository) {
        this.application = application;
        this.iSpotifyRepository = iSpotifyRepository;
        this.iccAuthRepository = iccAuthRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull @NotNull Class<T> modelClass) {
        return (T) new SearchViewModel(application, iSpotifyRepository, iccAuthRepository);
    }
}
