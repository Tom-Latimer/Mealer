package com.example.mealer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setStatusBarColor(getResources().getColor(R.color.button_blue));
    }

    public void btnSignInClick(View view){
        startActivity(new Intent(MainActivity.this, SignIn.class));
    }

    public void btnClientSignUpClick(View view){
        startActivity(new Intent(MainActivity.this, ClientSignUp.class));
    }

    public void btnCookSignUpClick(View view){
        startActivity(new Intent(MainActivity.this, CookSignUp.class));
    }

}