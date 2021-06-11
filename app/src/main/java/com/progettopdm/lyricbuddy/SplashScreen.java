package com.progettopdm.lyricbuddy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.progettopdm.lyricbuddy.authentication.WelcomeActivity;
import com.progettopdm.lyricbuddy.repository.user.IUserRepository;
import com.progettopdm.lyricbuddy.repository.user.UserRepository;
import com.progettopdm.lyricbuddy.ui.userprofile.UserViewModel;
import com.progettopdm.lyricbuddy.ui.userprofile.UserViewModelFactory;

//splash screen activity
public class SplashScreen extends AppCompatActivity {

    private UserViewModel userViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        IUserRepository userRepository = new UserRepository(getApplication());
        userViewModel = new ViewModelProvider(this,
                new UserViewModelFactory(getApplication(), userRepository)).get(UserViewModel.class);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (userViewModel.getAuthenticationToken() != null) {
                    //from splash screen to login activity
                    Intent i = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(i);
                    finish();
                } else {
                    Intent intent = new Intent(SplashScreen.this, WelcomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        },4000);
        /*if (userViewModel.getAuthenticationToken() != null) {
            Intent intent = new Intent(SplashScreen.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            Intent intent = new Intent(SplashScreen.this, WelcomeActivity.class);
            startActivity(intent);
            finish();
        }*/
    }

}




/*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //from splash screen to login activity
                Intent i = new Intent(SplashScreen.this, Login.class);
                startActivity(i);
                finish();
            }
        },4000);
    }
*/





   /* @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //from splash screen to login activity
                Intent i = new Intent(SplashScreen.this, Login.class);
                startActivity(i);
                finish();
            }
        },4000);
    }*/
