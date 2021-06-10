package com.progettopdm.lyricbuddy.ui.userprofile;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.progettopdm.lyricbuddy.login.User;

public class UserProfileViewModel extends ViewModel {
   // private MutableLiveData<String> mText;
    private MutableLiveData<String> fullName, email, phoneNumber;
    DatabaseReference reference;
    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

    public UserProfileViewModel() {
        reference = FirebaseDatabase.getInstance().getReference("users").child(firebaseUser.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                String name = snapshot.child("fullName").getValue().toString();
                String eAddress = snapshot.child("email").getValue().toString();
                String psw = snapshot.child("password").getValue().toString();
                String pNumber = snapshot.child("phoneNumber").getValue().toString();


                //fullName = new MutableLiveData<>();
                fullName.setValue(user.getFullName());
               // email = new MutableLiveData<>();
                email.setValue(user.getEmail());
               /* mText = new MutableLiveData<>();
                mText.setValue("");*/
               // phoneNumber = new MutableLiveData<>();
                phoneNumber.setValue(user.getPhoneNumber());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }



    //public LiveData<String> getText() { return mText;}

    public LiveData<String> getfullName() { return fullName;}

    public LiveData<String> getEmail() { return email;}

    public LiveData<String> getPhoneNumber() { return phoneNumber;}



}
