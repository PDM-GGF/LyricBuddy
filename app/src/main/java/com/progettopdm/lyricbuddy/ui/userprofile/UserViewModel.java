package com.progettopdm.lyricbuddy.ui.userprofile;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.progettopdm.lyricbuddy.login.LoginResponse;
import com.progettopdm.lyricbuddy.login.User;
import com.progettopdm.lyricbuddy.repository.user.IUserRepository;
import com.progettopdm.lyricbuddy.utils.Constants;

public class UserViewModel extends AndroidViewModel {
    private MutableLiveData<LoginResponse> loginResultMutableLiveData;
    private MutableLiveData<Boolean> firebaseRealtimeDbResponseMutableLiveData;
    private final IUserRepository userRepository;
    private boolean isLogged;

    public UserViewModel(Application application, IUserRepository userRepository) {
        super(application);
        this.userRepository = userRepository;
        this.isLogged = false;
    }

    public MutableLiveData<LoginResponse> getUser(String username, String password) {
        if (!isLogged) {
            login(username, password);
        }
        return loginResultMutableLiveData;
    }

    private void login(String username, String password) {
        loginResultMutableLiveData = userRepository.login(username, password);
    }

    public MutableLiveData<LoginResponse> createUser(String fullName, String email, String phoneNumber, String password, String confPassword) {
        if (!isLogged) {
            register(fullName, email, phoneNumber, password, confPassword);
        }
        return loginResultMutableLiveData;
    }

    private void register(String fullName, String email, String phoneNumber, String password, String confPassword) {
        loginResultMutableLiveData = userRepository.register(fullName, email, phoneNumber, password, confPassword);
    }

    public void setLogged(boolean isLogged) {
        this.isLogged = isLogged;
    }

    public String getAuthenticationToken() {
        SharedPreferences sharedPref = getApplication().getSharedPreferences("shared_preferences", Context.MODE_PRIVATE);
        return sharedPref.getString("authenticationToken", null);
    }
}
