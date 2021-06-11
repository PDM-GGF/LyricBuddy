package com.progettopdm.lyricbuddy.ui.userprofile;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

import com.google.firebase.auth.FirebaseAuth;
import com.progettopdm.lyricbuddy.R;
import com.progettopdm.lyricbuddy.authentication.LoginFragment;
import com.progettopdm.lyricbuddy.authentication.RegistrationFragment;
import com.progettopdm.lyricbuddy.authentication.WelcomeActivity;
import com.progettopdm.lyricbuddy.login.User;
import com.progettopdm.lyricbuddy.repository.user.UserRepository;
import com.progettopdm.lyricbuddy.ui.search.SearchViewModel;

import static android.content.ContentValues.TAG;


public class UserProfileActiveFragment extends Fragment {

    private UserProfileActiveViewModel userProfileActiveViewModel;
    Button logout;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        userProfileActiveViewModel =
                new ViewModelProvider(this).get(UserProfileActiveViewModel.class);
        View root = inflater.inflate(R.layout.fragment_userprofile_active, container, false);
        final TextView textView = root.findViewById(R.id.psw);
        userProfileActiveViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        logout = view.findViewById(R.id.button_logout);
        logout.setOnClickListener(v ->{
            FirebaseAuth.getInstance().signOut();
            goToLogin();

        });
    }

    public void goToLogin() {
        Intent intent = new Intent(getActivity(), WelcomeActivity.class);
        startActivity(intent);
    }
}

















/*public class UserProfileActiveFragment extends Fragment {

    private UserProfileActiveViewModel userProfileActiveViewModel;
    Button logout;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        userProfileActiveViewModel = new ViewModelProvider(this).get(UserProfileActiveViewModel.class);
        View root = inflater.inflate(R.layout.fragment_userprofile_active, container, false);


        final TextView message = root.findViewById(R.id.userprofile_message);
        userProfileActiveViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
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

        logout = view.findViewById(R.id.button_logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                //goToLogin();
            }
        });
    }

    //from user profile to login activity
    public void goToLogin() {
        Intent intent = new Intent(getActivity(), Login.class);
        startActivity(intent);
     }*/

