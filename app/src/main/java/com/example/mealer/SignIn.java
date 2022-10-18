package com.example.mealer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SignIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
    }

    public void backbtnSignIn(View view){ //goes back to main activity
        startActivity(new Intent(SignIn.this, MainActivity.class));
    }
    public void btn_SignIn(View view){ //goes to home activity
        startActivity(new Intent(SignIn.this, Home.class));
    }
}