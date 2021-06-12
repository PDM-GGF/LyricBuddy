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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.progettopdm.lyricbuddy.R;
import com.progettopdm.lyricbuddy.login.LoginActivity;
import com.progettopdm.lyricbuddy.login.User;

import org.jetbrains.annotations.NotNull;

public class UserProfileFragment extends Fragment {

    private UserProfileViewModel userProfileViewModel;
    private Button logout;
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        userProfileViewModel =
                new ViewModelProvider(this).get(UserProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_userprofile_active, container, false);
        final TextView textView = root.findViewById(R.id.text_password);
        userProfileViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
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

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        final TextView fName = view.findViewById(R.id.text_fullName);
        final TextView eAddress = view.findViewById(R.id.text_email_address);
        final TextView pNumber = view.findViewById(R.id.text_phoneNumber);

        reference.child("users").child(userID).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<DataSnapshot> task) {
                if(!task.isSuccessful()) {
                    Log.d("Error ", task.getException().getMessage());
                }else{
                    User userProfile = task.getResult().getValue(User.class);
                    if (userProfile != null){
                        String fullName = userProfile.getFullName();
                        String email = userProfile.getEmail();
                        String phone = userProfile.getPhoneNumber();

                        fName.setText(fullName);
                        eAddress.setText(email);
                        pNumber.setText(phone);
                    }
                }
            }

        });

        logout = view.findViewById(R.id.button_logout);
        logout.setOnClickListener(v -> {
                FirebaseAuth.getInstance().signOut();
                goToLogin();
        });
    }

    //from user profile to login activity
    public void goToLogin() {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
    }
}
