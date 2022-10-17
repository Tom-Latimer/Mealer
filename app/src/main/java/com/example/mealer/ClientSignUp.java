package com.example.mealer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class ClientSignUp extends AppCompatActivity implements OnClickListener {

    AppCompatButton signUpButtonClient;

    private EditText firstNameField, lastNameField, emailField,
            passwordField, creditCardNumberField, securityCodeField,
            expirationDateField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_sign_up);

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
        String firstName = firstNameField.getText().toString().trim();
        String lastName = lastNameField.getText().toString().trim();
        String password = passwordField.getText().toString().trim();
        String email = emailField.getText().toString().trim();
        String ccNumber = creditCardNumberField.getText().toString().trim();
        String securityCode = securityCodeField.getText().toString().trim();
        String expirationDate = expirationDateField.getText().toString().trim();

        if (firstName.isEmpty()) {
            firstNameField.setError("First Name Required");
            firstNameField.requestFocus();
        }

        if (lastName.isEmpty()) {
            lastNameField.setError("Last Name Required");
            lastNameField.requestFocus();
        }

        if (password.isEmpty()) {
            passwordField.setError("Please enter a password");
            passwordField.requestFocus();
        }

        if (email.isEmpty()) {
            emailField.setError("Email Required");
            emailField.requestFocus();
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailField.setError("Invalid Email");
            emailField.requestFocus();
        }

        if (ccNumber.isEmpty()) {
            creditCardNumberField.setError("Field Empty");
            creditCardNumberField.requestFocus();
        } else if (ccNumber.length() < 16) {
            creditCardNumberField.setError("Please enter all numbers");
            creditCardNumberField.requestFocus();
        }

        if (securityCode.isEmpty()) {
            securityCodeField.setError("Field Empty");
            securityCodeField.requestFocus();
        } else if (securityCode.length() < 3) {
            securityCodeField.setError("Please enter all numbers");
            securityCodeField.requestFocus();
        }

        if (expirationDate.isEmpty()) {
            expirationDateField.setError("Field Empty");
            expirationDateField.requestFocus();
        } else if (expirationDate.length() < 5) {
            expirationDateField.setError("Please enter a date");
            expirationDateField.requestFocus();
        }

    }
}