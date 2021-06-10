package com.progettopdm.lyricbuddy.ui.userprofile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.progettopdm.lyricbuddy.R;
import com.progettopdm.lyricbuddy.login.Login;

public class UserProfileDisabledFragment extends Fragment {

    private UserProfileDisabledViewModel userProfileDisabledViewModel;
    Button ToLogin;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        userProfileDisabledViewModel = new ViewModelProvider(this).get(UserProfileDisabledViewModel.class);
        View root = inflater.inflate(R.layout.fragment_userprofile_disabled, container, false);


        final TextView message = root.findViewById(R.id.userprofile_message);
        userProfileDisabledViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                message.setText(s);
            }
        });
        return root;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ToLogin = view.findViewById(R.id.buttonToLogin);
        ToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToLogin();
                  }
        });
    }

    //from user profile disable to login activity
    public void goToLogin() {
        Intent intent = new Intent(getActivity(), Login.class);
        startActivity(intent);
    }

}