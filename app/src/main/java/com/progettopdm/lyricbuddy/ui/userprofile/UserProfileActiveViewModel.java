package com.progettopdm.lyricbuddy.ui.userprofile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UserProfileActiveViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public UserProfileActiveViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is userprofile active fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}











/*
public class UserProfileActiveViewModel extends ViewModel {
        private MutableLiveData<String> text;

        public UserProfileActiveViewModel() {
            text = new MutableLiveData<>();
            text.setValue("user profile");
        }

        public LiveData<String> getText() {
            return text;
        }
}
*/
