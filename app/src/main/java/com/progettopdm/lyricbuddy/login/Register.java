package com.progettopdm.lyricbuddy.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.progettopdm.lyricbuddy.MainActivity;
import com.progettopdm.lyricbuddy.R;

import static com.progettopdm.lyricbuddy.utils.Constants.FIREBASE_REALTIME_DB;

public class Register extends AppCompatActivity {
    EditText registerFullName, registerPhoneNumber, registerEmail, registerPassword, registerConfirmPassword;
    Button createAccountButton, backToLogin ;
    FirebaseAuth fAuth;
    DatabaseReference fDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerFullName = findViewById(R.id.full_name);
        registerPhoneNumber = findViewById(R.id.phone);
        registerEmail = findViewById(R.id.email_address);
        registerPassword = findViewById(R.id.password);
        registerConfirmPassword = findViewById(R.id.confirm_password);

        createAccountButton = findViewById(R.id.button_register);
        backToLogin = findViewById(R.id.button_backLogin);

        //on click tasto login
        backToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Login.class));
                finish();
            }
        });

        fAuth = FirebaseAuth.getInstance();
        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fDatabase = FirebaseDatabase.getInstance(FIREBASE_REALTIME_DB).getReference().child("users");

                // extract the data from form
                String fullName = registerFullName.getText().toString();
                String phoneNumber = registerPhoneNumber.getText().toString();
                String email = registerEmail.getText().toString();
                String password = registerPassword.getText().toString();
                String confirmPassword = registerConfirmPassword.getText().toString();

                if(fullName.isEmpty()){
                    registerFullName.setError("Full Name is Required");
                    return;
                }

                if(phoneNumber.isEmpty()){
                    registerPhoneNumber.setError("Phone Number is Required");
                    return;
                }

                if(phoneNumber.length() != 10){
                    registerPhoneNumber.setError("It is Not a Phone Number");
                    return;
                }

                if(email.isEmpty()){
                    registerEmail.setError("Email is Required");
                    return;
                }
                if(password.isEmpty()){
                    registerPassword.setError("Password is Required");
                    return;
                }
                if(confirmPassword.isEmpty()){
                    registerConfirmPassword.setError("Confirm Password is Required");
                    return;
                }

                if(!password.equals(confirmPassword)){
                    registerConfirmPassword.setError("Password Do not Match.");
                }

                User user = new User(fullName, phoneNumber, email);
                fDatabase.push().setValue(user);

                // register the user using firebase
                fAuth.createUserWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        FirebaseUser fuser = fAuth.getCurrentUser();
                        fuser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                //send user to the next page
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                finish();
                                Toast.makeText(Register.this, "Verification Email Has been Sent.", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d("tag", "onFailure: Email not sent " + e.getMessage());
                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Register.this, e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });

            }



        });


    }
}