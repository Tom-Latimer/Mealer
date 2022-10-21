package com.example.mealer;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class CookSignUp extends AppCompatActivity {

    private static final int SELECT_PICTURE = 1;
    private AppCompatButton signUpButtonCook;

    private EditText firstNameField, lastNameField, emailField,
            passwordField, addressField, unitField, postalCodeField,
            countryField, imgCheque, descriptionField;

    private FirebaseAuth mAuth;

    private boolean isAcceptable = true;


    //int SELECT_PICTURE = 100;
    //ImageView imgCheque;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook_sign_up);

        mAuth = FirebaseAuth.getInstance();

        firstNameField = (EditText) findViewById(R.id.firstNameField2);
        lastNameField = (EditText) findViewById(R.id.lastNameField2);
        passwordField = (EditText) findViewById(R.id.passwordField2);
        emailField = (EditText) findViewById(R.id.emailField2);
        addressField = (EditText) findViewById(R.id.addressField);
        unitField = (EditText) findViewById(R.id.unitField);
        postalCodeField = (EditText) findViewById(R.id.postalCodeField);
        countryField = (EditText) findViewById(R.id.countryField);
        //////imgCheque = (EditText) findViewById(R.id.imgCheque);
        descriptionField = (EditText) findViewById(R.id.descriptionField);

        //signUpButtonCook = (AppCompatButton) findViewById(R.id.signUpButtonCook);
        //signUpButtonCook.setOnClickListener(this);
    }

    public void btnBackToMain(View view){
        startActivity(new Intent(CookSignUp.this, MainActivity.class));
    }

    public void btnCookToHome(View view){
        registerUser();

        if (isAcceptable == true){
            startActivity(new Intent(CookSignUp.this, Home.class));
        }

    }

    public void ChooseImageBtn(View view){
        Intent chooseVoidCheckPhoto = new Intent();
        chooseVoidCheckPhoto.setType("image/*");
        chooseVoidCheckPhoto.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(chooseVoidCheckPhoto, "Select Picture"), SELECT_PICTURE);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            switch (requestCode) {

                case SELECT_PICTURE:
                    if (resultCode == Activity.RESULT_OK) {
                        //data gives you the image uri. Try to convert that to bitmap
                        break;
                    } else if (resultCode == Activity.RESULT_CANCELED) {
                        Log.e(TAG, "Selecting picture cancelled");
                    }
                    break;
            }
        } catch (Exception e) {
            Log.e(TAG, "Exception in onActivityResult : " + e.getMessage());
        }
    }

    //@Override
    //public void onClick(View view) {
    //    switch (view.getId()) {
    //        case R.id.signUpButtonCook:
    //            registerUser();
    //            break;
    //    }
    //}

    private void registerUser() {


        String firstName = firstNameField.getText().toString().trim();
        String lastName = lastNameField.getText().toString().trim();
        String password = passwordField.getText().toString().trim();
        String email = emailField.getText().toString().trim();
        String address = addressField.getText().toString().trim();
        String unitNum = unitField.getText().toString().trim();
        String postalCode = postalCodeField.getText().toString().trim();
        String country = countryField.getText().toString().trim();
        //String voidCheque = imgCheque.getText().toString().trim();
        String description = descriptionField.getText().toString().trim();

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

        String addressError = verify.checkAddress(address);
        if (addressError != "") {
            addressField.setError(addressError);
            addressField.requestFocus();
            isAcceptable = false;
        }

        String unitNumError = verify.checkUnitNum(unitNum);
        if (unitNumError != "") {
            unitField.setError(unitNumError);
            unitField.requestFocus();
            isAcceptable = false;
        }

        String postalCodeError = verify.checkPostalCode(postalCode);
        if (postalCodeError != "") {
            postalCodeField.setError(postalCodeError);
            postalCodeField.requestFocus();
            isAcceptable = false;
        }

        String countryError = verify.checkCountry(country);
        if (countryError != "") {
            countryField.setError(countryError);
            countryField.requestFocus();
            isAcceptable = false;
        }

        //String voidChequeError = verify.checkVoidCheque(voidCheque);
        //if (voidChequeError != "") {
        //    voidChequeField.setError(voidChequeError);
        //    voidChequeField.requestFocus();
        //    isAcceptable = false;
        //}

        String descriptionError = verify.checkDescription(description);
        if (descriptionError != "") {
            descriptionField.setError(descriptionError);
            descriptionField.requestFocus();
            isAcceptable = false;
        }

        if (isAcceptable) {
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Cook_Class cook = new Cook_Class(firstName , lastName, email , password ,  address , "Cook" , "voidCheque" , description);

                        FirebaseDatabase.getInstance().getReference("Users")
                                .child(FirebaseAuth.getInstance().getUid())
                                .setValue(cook).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        if (task.isSuccessful()) {
                                            Toast.makeText(CookSignUp.this,"User has successfully been registered!",Toast.LENGTH_LONG).show();
                                        } else {
                                            Toast.makeText(CookSignUp.this,"Failed to register!",Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });

                    } else {
                        Toast.makeText(CookSignUp.this,"Failed to register!",Toast.LENGTH_LONG).show();
                    }
                }
            });
        }


    }
}