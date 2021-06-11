package com.progettopdm.lyricbuddy.repository.user;

import androidx.lifecycle.MutableLiveData;

import com.progettopdm.lyricbuddy.login.LoginResponse;

public interface IUserRepository {
    MutableLiveData<LoginResponse> login(String username, String password);
    MutableLiveData<LoginResponse> register(String fullName, String email, String phoneNumber, String password, String confPassword);
}
