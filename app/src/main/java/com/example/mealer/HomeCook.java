package com.example.mealer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class HomeCook extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_cook);
        getWindow().setStatusBarColor(getResources().getColor(R.color.button_blue));
    }

    public void btnLogOutClick(View view){
        startActivity(new Intent(HomeCook.this, MainActivity.class));

        FirebaseAuth.getInstance().signOut();
        //finish();
    }
    public void onMenuClick
            (View view){
        startActivity(new Intent(HomeCook.this, Menu.class));


        //finish();
    }
    public void btnProfileClick(View view){
        startActivity(new Intent(HomeCook.this, Profile.class));
    }

    public void btnPurchaseRequestClick(View view){
        startActivity(new Intent(HomeCook.this, PurchaseRequestPage.class));
    }
}