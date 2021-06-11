package com.progettopdm.lyricbuddy.authentication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

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

import org.jetbrains.annotations.NotNull;

public class RegistrationFragment extends Fragment {
    private UserViewModel userViewModel;

    public RegistrationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        IUserRepository userRepository = new UserRepository(requireActivity().getApplication());
        userViewModel = new ViewModelProvider(this,
                new UserViewModelFactory(requireActivity().getApplication(),
                        userRepository)).get(UserViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registration, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EditText fullNameEditText = view.findViewById(R.id.fullName);
        EditText emailEditText = view.findViewById(R.id.email);
        EditText phoneNumberEditText = view.findViewById(R.id.phoneNumber);
        EditText passwordEditText = view.findViewById(R.id.password);
        EditText confPasswordEditText = view.findViewById(R.id.confPassword);

        Button registrationButton = view.findViewById(R.id.button_createAccount);
        Button backToLogin = view.findViewById(R.id.backtologin);

        registrationButton.setOnClickListener(v -> {
            String fullName = fullNameEditText.getText().toString();
            String email = emailEditText.getText().toString();
            String phoneNumber = phoneNumberEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            String confPassword = confPasswordEditText.getText().toString();

            if(fullName.isEmpty()){
                fullNameEditText.setError("Full Name is Required");
                return;
            }

            if(phoneNumber.isEmpty()){
                phoneNumberEditText.setError("Phone Number is Required");
                return;
            }

            if(phoneNumber.length() != 10){
                phoneNumberEditText.setError("It is Not a Phone Number");
                return;
            }

            if(email.isEmpty()){
                emailEditText.setError("Email is Required");
                return;
            }
            if(password.isEmpty()){
                passwordEditText.setError("Password is Required");
                return;
            }
            if(confPassword.isEmpty()){
                confPasswordEditText.setError("Confirm Password is Required");
                return;
            }

            if(!password.equals(confPassword)){
                confPasswordEditText.setError("Password Do not Match.");
            }
            register(fullName, email, phoneNumber, password, confPassword );

        });

        backToLogin.setOnClickListener(v ->{
            NavHostFragment.findNavController(RegistrationFragment.this).
                    navigate(R.id.action_registrationFragment_to_loginFragment);
        });
    }

    private void register(String fullName, String email, String phoneNumber, String password, String confPassword) {
        userViewModel.createUser(fullName, email, phoneNumber, password, confPassword).observe(getViewLifecycleOwner(), new Observer<LoginResponse>() {
            @Override
            public void onChanged(LoginResponse loginResponse) {
                if (loginResponse != null && loginResponse.isSuccess()) {
                    NavHostFragment.findNavController(RegistrationFragment.this).
                            navigate(R.id.action_registrationFragment_to_mainActivity);
                    userViewModel.setLogged(true);
                } else {
                    userViewModel.setLogged(false);
                    showErrorMessage();
                }
            }
        });
    }

    private void showErrorMessage() {
        Snackbar.make(requireView(), "registration failed", Snackbar.LENGTH_SHORT).show();
    }

}
