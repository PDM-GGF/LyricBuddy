package com.progettopdm.lyricbuddy.ui.favorites;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FavoritesViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public FavoritesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Salva i brani preferiti.. nel prossimo aggiornamento");
    }

    public LiveData<String> getText() {
        return mText;
    }
}