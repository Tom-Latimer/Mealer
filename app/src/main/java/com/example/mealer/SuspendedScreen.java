package com.example.mealer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class SuspendedScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suspended_screen);
    }

    public void btnLogOutClick(View view) {
        startActivity(new Intent(SuspendedScreen.this, MainActivity.class));

        FirebaseAuth.getInstance().signOut();

    }
}