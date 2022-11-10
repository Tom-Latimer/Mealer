package com.example.mealer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

    private AppCompatButton signUpButtonClient, backButton;

    private EditText firstNameField, lastNameField, emailField,
            passwordField, creditCardNumberField, securityCodeField,
            expirationDateField, addressField, unitField;

    private FirebaseAuth mAuth;

    boolean isAcceptable = true;

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
        addressField = (EditText) findViewById(R.id.addressInputField);
        unitField = (EditText) findViewById(R.id.unitInputField);

        signUpButtonClient = (AppCompatButton) findViewById(R.id.signUpButtonClient);
        signUpButtonClient.setOnClickListener(this);

        backButton = (AppCompatButton) findViewById(R.id.bckButtonClient);
        backButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.signUpButtonClient:
                registerUser();
                break;
            case R.id.bckButtonClient:
                startActivity(new Intent(ClientSignUp.this, MainActivity.class));
                //finish();
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
        String address = addressField.getText().toString().trim();
        String unitNumber = unitField.getText().toString().trim();

        Verification_Class verify = new Verification_Class();

        String firstNameError = verify.checkFirstName(firstName);
        if (!firstNameError.equals("")) {
            firstNameField.setError(firstNameError);
            firstNameField.requestFocus();
            isAcceptable = false;
        }

        String lastNameError = verify.checkLastName(lastName);
        if (!lastNameError.equals("")) {
            lastNameField.setError(lastNameError);
            lastNameField.requestFocus();
            isAcceptable = false;
        }

        String passwordError = verify.checkPassword(password);
        if (!passwordError.equals("")) {
            passwordField.setError(passwordError);
            passwordField.requestFocus();
            isAcceptable = false;
        }

        String addressError = verify.checkAddress(address);
        if (!addressError.equals("")) {
            addressField.setError(addressError);
            addressField.requestFocus();
            isAcceptable = false;
        }

        String unitError = verify.checkUnitNum(unitNumber);
        if (!unitError.equals("")) {
            unitField.setError(unitError);
            unitField.requestFocus();
            isAcceptable = false;
        }

        String emailError = verify.checkEmail(email);
        if (!emailError.equals("")) {
            emailField.setError(emailError);
            emailField.requestFocus();
            isAcceptable = false;
        }

        String ccNumberError = verify.checkCCNumber(ccNumber);
        if (!ccNumberError.equals("")) {
            creditCardNumberField.setError(ccNumberError);
            creditCardNumberField.requestFocus();
            isAcceptable = false;
        }

        String securityCodeError = verify.checkSecurityCode(securityCode);
        if (!securityCodeError.equals("")) {
            securityCodeField.setError(securityCodeError);
            securityCodeField.requestFocus();
            isAcceptable = false;
        }

        String expirationDateError = verify.checkExpirationDate(expirationDate);
        if (!expirationDateError.equals("")) {
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
                                address+unitNumber,"CLIENT",expirationDate+securityCode+ccNumber);

                        FirebaseDatabase.getInstance().getReference("Users")
                                .child(FirebaseAuth.getInstance().getUid())
                                .setValue(client).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        if (task.isSuccessful()) {
                                            Toast.makeText(ClientSignUp.this,"User has successfully been registered!",Toast.LENGTH_LONG).show();
                                            startActivity(new Intent(ClientSignUp.this, HomeClient.class));
                                            //finish();
                                        } else {
                                            Toast.makeText(ClientSignUp.this,"Failed to register!",Toast.LENGTH_LONG).show();
                                            System.out.println(task.getException().getMessage());
                                        }
                                    }
                                });

                    } else {
                        Toast.makeText(ClientSignUp.this,"Failed to register!",Toast.LENGTH_LONG).show();
                        System.out.println(task.getException().getMessage());
                    }
                }
            });
        }
    }
}