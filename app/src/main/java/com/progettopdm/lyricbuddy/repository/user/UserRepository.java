package com.progettopdm.lyricbuddy.repository.user;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.progettopdm.lyricbuddy.login.LoginResponse;
import com.progettopdm.lyricbuddy.login.User;
import com.progettopdm.lyricbuddy.utils.Constants;

import static com.progettopdm.lyricbuddy.utils.Constants.FIREBASE_REALTIME_DB;

public class UserRepository implements IUserRepository{
    private final Application application;
    private final FirebaseAuth mAuth;
    private MutableLiveData<LoginResponse> loginResultMutableLiveData;
    private final DatabaseReference mDatabase;

    public UserRepository(Application application) {
        this.application = application;
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance(FIREBASE_REALTIME_DB).getReference();
    }

    public MutableLiveData<LoginResponse> login(String email, String password) {
        loginResultMutableLiveData = new MutableLiveData<>();
        LoginResponse loginResponse = new LoginResponse();

        mAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                FirebaseUser user = mAuth.getCurrentUser();
                setAuthenticationToken(user.getIdToken(false).getResult().getToken());
                setUserId(user.getUid());
                loginResponse.setSuccess(true);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                loginResponse.setSuccess(false);
            }
        });



        /*mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(ContextCompat.getMainExecutor(application), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        LoginResponse loginResponse = new LoginResponse();
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            setAuthenticationToken(user.getIdToken(false).getResult().getToken());
                            setUserId(user.getUid());
                            loginResponse.setSuccess(true);
                        } else {
                            loginResponse.setSuccess(false);
                        }
                        loginResultMutableLiveData.postValue(loginResponse);
                    }
                   });*/
        return loginResultMutableLiveData;
    }

    @Override
    public MutableLiveData<LoginResponse> register(String fullName, String email, String phoneNumber, String password, String confPassword) {
        loginResultMutableLiveData = new MutableLiveData<>();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(ContextCompat.getMainExecutor(application), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        LoginResponse registrationResult = new LoginResponse();
                        if (task.isSuccessful()) {
                            FirebaseUser firebaseUser = mAuth.getCurrentUser();
                            setAuthenticationToken(firebaseUser.getIdToken(false).getResult().getToken());
                            User user = new User(fullName, email, phoneNumber, firebaseUser.getUid());
                            mDatabase.child("users").child(user.getId()).setValue(user);
                            setUserId(user.getId());
                            registrationResult.setSuccess(true);
                        } else {
                            // If sign in fails, display a message to the user.
                            registrationResult.setSuccess(false);
                        }
                        loginResultMutableLiveData.postValue(registrationResult);
                    }
                });
        return loginResultMutableLiveData;
    }

    private void setAuthenticationToken(String token) {
        SharedPreferences sharedPref = application.getSharedPreferences(
                "shared_preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("authenticationToken", token);
        editor.apply();
    }

    private void setUserId(String userId) {
        SharedPreferences sharedPref = application.getSharedPreferences(
                "shared_preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("userId", userId);
        editor.apply();
    }
}
