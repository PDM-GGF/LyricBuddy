package com.progettopdm.lyricbuddy.ui.track;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.progettopdm.lyricbuddy.model.Track;

public class TrackViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public TrackViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is TRACK user fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}