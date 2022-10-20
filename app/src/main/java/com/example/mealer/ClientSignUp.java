package com.example.mealer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class ClientSignUp extends AppCompatActivity implements OnClickListener {

    private AppCompatButton signUpButtonClient;

    private EditText firstNameField, lastNameField, emailField,
            passwordField, creditCardNumberField, securityCodeField,
            expirationDateField;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_sign_up);

        mAuth = FirebaseAuth.getInstance();

        firstNameField = (EditText) findViewById(R.id.firstNameField);
        lastNameField = (EditText) findViewById(R.id.lastNameField);
        passwordField = (EditText) findViewById(R.id.passwordField);
        emailField = (EditText) findViewById(R.id.emailField);
        creditCardNumberField = (EditText) findViewById(R.id.creditCardNumberField);
        securityCodeField = (EditText) findViewById(R.id.securityCodeField);
        expirationDateField = (EditText) findViewById(R.id.expirationDateField);

        signUpButtonClient = (AppCompatButton) findViewById(R.id.signUpButtonClient);
        signUpButtonClient.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.signUpButtonClient:
                registerUser();
                break;
        }
    }

    private void registerUser() {
        boolean isAcceptable = true;

        String firstName = firstNameField.getText().toString().trim();
        String lastName = lastNameField.getText().toString().trim();
        String password = passwordField.getText().toString().trim();
        String email = emailField.getText().toString().trim();
        String ccNumber = creditCardNumberField.getText().toString().trim();
        String securityCode = securityCodeField.getText().toString().trim();
        String expirationDate = expirationDateField.getText().toString().trim();

        Verification_Class verify = new Verification_Class();

        String firstNameError = verify.checkFirstName(firstName);
        if (firstNameError != "") {
            firstNameField.setError(firstNameError);
            firstNameField.requestFocus();
            isAcceptable = false;
        }

        String lastNameError = verify.checkLastName(lastName);
        if (lastNameError != "") {
            lastNameField.setError(lastNameError);
            lastNameField.requestFocus();
            isAcceptable = false;
        }

        String passwordError = verify.checkPassword(password);
        if (passwordError != "") {
            passwordField.setError(passwordError);
            passwordField.requestFocus();
            isAcceptable = false;
        }

        String emailError = verify.checkEmail(email);
        if (emailError != "") {
            emailField.setError(emailError);
            emailField.requestFocus();
            isAcceptable = false;
        }

        String ccNumberError = verify.checkCCNumber(ccNumber);
        if (ccNumberError != "") {
            creditCardNumberField.setError(ccNumberError);
            creditCardNumberField.requestFocus();
            isAcceptable = false;
        }

        String securityCodeError = verify.checkSecurityCode(securityCode);
        if (securityCodeError != "") {
            securityCodeField.setError(securityCodeError);
            securityCodeField.requestFocus();
            isAcceptable = false;
        }

        String expirationDateError = verify.checkExpirationDate(expirationDate);
        if (expirationDateError != "") {
            expirationDateField.setError(expirationDateError);
            expirationDateField.requestFocus();
            isAcceptable = false;
        }

        if (isAcceptable) {
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Client_Class client = new Client_Class(firstName,lastName,email,password,
                                "TEMP","TYPE",expirationDate+securityCode+ccNumber);

                        FirebaseDatabase.getInstance().getReference("Users")
                                .child(FirebaseAuth.getInstance().getUid())
                                .setValue(client).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        if (task.isSuccessful()) {
                                            Toast.makeText(ClientSignUp.this,"User has successfully been registered!",Toast.LENGTH_LONG).show();
                                        } else {
                                            Toast.makeText(ClientSignUp.this,"Failed to register!",Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });

                    } else {
                        Toast.makeText(ClientSignUp.this,"Failed to register!",Toast.LENGTH_LONG).show();
                    }
                }
            });
        }


    }
}