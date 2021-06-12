package com.progettopdm.lyricbuddy.ui.userprofile;

import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.progettopdm.lyricbuddy.R;
import com.progettopdm.lyricbuddy.login.User;


public class UserProfileViewModel extends ViewModel {
    private MutableLiveData<String> mText;
    private TextView fullname, email, phoneNumber;




    public UserProfileViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is user fragment");
    }


    public LiveData<String> getText() {
        return mText;
    }

    public static void getInfoUser(TextView fName, TextView email, TextView pNumber) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String eAddress = user.getEmail();
            email.setText(eAddress);

        }
    }

}
