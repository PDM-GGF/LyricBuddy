package com.progettopdm.lyricbuddy.ui.userprofile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.progettopdm.lyricbuddy.R;

public class UserProfileDisabledFragment extends Fragment {

    private UserProfileDisabledViewModel userProfileDisabledViewModel;
    //Button logout;

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
}