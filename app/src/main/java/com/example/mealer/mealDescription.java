package com.example.mealer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class mealDescription extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_description);
        getWindow().setStatusBarColor(getResources().getColor(R.color.button_blue));
    }
}