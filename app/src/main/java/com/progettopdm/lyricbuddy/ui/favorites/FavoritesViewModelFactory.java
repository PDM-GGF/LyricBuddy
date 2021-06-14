package com.progettopdm.lyricbuddy.ui.favorites;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.progettopdm.lyricbuddy.repository.ICCAuthRepository;
import com.progettopdm.lyricbuddy.repository.IDatabaseRepository;
import com.progettopdm.lyricbuddy.repository.ISpotifyRepository;
import com.progettopdm.lyricbuddy.ui.home.HomeViewModel;

import org.jetbrains.annotations.NotNull;

public class FavoritesViewModelFactory implements ViewModelProvider.Factory {

    private final Application application;
    private final IDatabaseRepository iDatabaseRepository;

    public FavoritesViewModelFactory(Application application, IDatabaseRepository iDatabaseRepository) {
        this.application = application;
        this.iDatabaseRepository = iDatabaseRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull @NotNull Class<T> modelClass) {
        return (T) new FavoritesViewModel(application, iDatabaseRepository);
    }
}
