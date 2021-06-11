package com.progettopdm.lyricbuddy.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.strictmode.WebViewMethodCalledOnWrongThreadViolation;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.progettopdm.lyricbuddy.MainActivity;
import com.progettopdm.lyricbuddy.R;

public class Login extends AppCompatActivity {
    Button LoginButton, signUpButton, skipRegistrationButton;
    EditText emailAddress, password;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();


        signUpButton = findViewById(R.id.button_signup);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Register.class));
            }
        });

        emailAddress = findViewById(R.id.edit_text_email);
        password = findViewById(R.id.edit_text_password);
        LoginButton = findViewById(R.id.button_login);

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // extract / validate

                if(emailAddress.getText().toString().isEmpty()){
                    emailAddress.setError("Email is Missing");
                    return;
                }

                if(password.getText().toString().isEmpty()){
                    password.setError("Password is Missing");
                    return;
                }

                // data is valid


                // login user
                firebaseAuth.signInWithEmailAndPassword(emailAddress.getText().toString(), password.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        //Login is successfull
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Login.this, e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

    }



    @Override
    protected void onStart() {
        super.onStart();
        if(firebaseAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }
    }
}

