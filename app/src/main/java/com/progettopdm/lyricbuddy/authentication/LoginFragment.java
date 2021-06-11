package com.progettopdm.lyricbuddy.authentication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.snackbar.Snackbar;
import com.progettopdm.lyricbuddy.R;
import com.progettopdm.lyricbuddy.login.LoginResponse;
import com.progettopdm.lyricbuddy.repository.user.IUserRepository;
import com.progettopdm.lyricbuddy.repository.user.UserRepository;
import com.progettopdm.lyricbuddy.ui.userprofile.UserViewModel;
import com.progettopdm.lyricbuddy.ui.userprofile.UserViewModelFactory;

public class LoginFragment extends Fragment {
    private UserViewModel userViewModel;

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        IUserRepository userRepository = new UserRepository(requireActivity().getApplication());
        userViewModel = new ViewModelProvider(this,
                new UserViewModelFactory(requireActivity().getApplication(), userRepository)).get(UserViewModel.class);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        EditText emailEditText = view.findViewById(R.id.editTextemail);
        EditText passwordEditText = view.findViewById(R.id.editTextpassword);
        Button loginButton = view.findViewById(R.id.button_login);
        Button signUpButton = view.findViewById(R.id.button_signup);

        loginButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            login(email, password);
            NavHostFragment.findNavController(LoginFragment.this).
                    navigate(R.id.action_loginFragment_to_mainActivity);
        });

        signUpButton.setOnClickListener(v -> {
            NavHostFragment.findNavController(LoginFragment.this).
                    navigate(R.id.action_loginFragment_to_registrationFragment);
        });
    }

    private void login(String username, String password) {
        userViewModel.getUser(username, password).observe(getViewLifecycleOwner(), new Observer<LoginResponse>() {
            @Override
            public void onChanged(LoginResponse loginResponse) {
                if (loginResponse != null) {
                    if (loginResponse.isSuccess()) {
                        userViewModel.setLogged(true);
                        showMessage();
                    } else {
                        userViewModel.setLogged(false);
                        showErrorMessage();
                    }
                }
            }
        });
    }

    private void showErrorMessage() {
        Snackbar.make(requireView(), "login failed", Snackbar.LENGTH_SHORT).show();
    }

    private void showMessage() {
        Snackbar.make(requireView(), "login OK", Snackbar.LENGTH_SHORT).show();
    }





}
