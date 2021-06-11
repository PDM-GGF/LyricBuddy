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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.progettopdm.lyricbuddy.R;
import com.progettopdm.lyricbuddy.login.Login;
import com.progettopdm.lyricbuddy.login.User;
import com.progettopdm.lyricbuddy.ui.search.SearchViewModel;

import static android.content.ContentValues.TAG;
import static com.progettopdm.lyricbuddy.utils.Constants.FIREBASE_REALTIME_DB;

public class UserProfileFragment extends Fragment {

    private UserProfileViewModel userProfileViewModel;
    private Button logout;
    private TextView fName, eAddress, psw, pNumber;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        userProfileViewModel =
                new ViewModelProvider(this).get(UserProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_userprofile_active, container, false);

        fName = root.findViewById(R.id.text_fullName);
        eAddress = root.findViewById(R.id.text_email_address);
        psw = root.findViewById(R.id.text_password);
        pNumber = root.findViewById(R.id.text_phoneNumber);

        UserProfileViewModel.getInfoUser(fName, eAddress, pNumber);


        /*final TextView textView = root.findViewById(R.id.text_password);
        userProfileViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        logout = view.findViewById(R.id.button_logout);
        logout.setOnClickListener(v -> {
                FirebaseAuth.getInstance().signOut();
                goToLogin();
        });
    }

    //from user profile to login activity
    public void goToLogin() {
        Intent intent = new Intent(getActivity(), Login.class);
        startActivity(intent);
    }
}
