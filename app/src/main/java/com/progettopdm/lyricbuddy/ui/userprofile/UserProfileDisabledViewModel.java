package com.progettopdm.lyricbuddy.ui.userprofile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UserProfileDisabledViewModel extends ViewModel {
    private MutableLiveData<String> mess_log;

    public UserProfileDisabledViewModel() {
        mess_log = new MutableLiveData<>();
        mess_log.setValue("you are not logged in");
    }

    public LiveData<String> getText() {
        return mess_log;
    }
}
