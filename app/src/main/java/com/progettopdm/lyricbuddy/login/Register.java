package com.progettopdm.lyricbuddy.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.progettopdm.lyricbuddy.R;

public class Register extends AppCompatActivity {
    EditText registerFullName, registerEmail, registerPassword, registerConfirmPassword;
    Button createAccountButton /*, goToLogin*/ ;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerFullName = findViewById(R.id.full_name);
        registerEmail = findViewById(R.id.email_address);
        registerPassword = findViewById(R.id.password);
        registerConfirmPassword = findViewById(R.id.confirm_password);

        createAccountButton = findViewById(R.id.button_register);
        //goToLogin = findViewById(R.id.button_goToLogin);

        fAuth = FirebaseAuth.getInstance();


        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // extract the data from form

                String fullName = registerFullName.getText().toString();
                String email = registerEmail.getText().toString();
                String password = registerPassword.getText().toString();
                String confirmPassword = registerConfirmPassword.getText().toString();

                if(fullName.isEmpty()){
                    registerFullName.setError("Full Name is Required");
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

            }

            // register the user using firebase
            /*
             fAuth.createUserWithEmailAndPassword(email,password).addOnSuccessListener(New OnSuccessListener<AuthResult>(){
                @Override
                public void onSuccess(AuthResult authResult){
                    //send user to the next page
                    startActivity(new Intent(getApplicationContent(), MainActivity.class));
                    finish();
                }
             }).addOnFailureListener(new OnFailureListener(){
                @Override
                public void onFailure(@NonNull Exception e){
                    Toast.makeText
                }
             }
            */





        });


    }
}