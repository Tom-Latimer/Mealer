package com.example.mealer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class CookSignUp extends AppCompatActivity {

    int SELECT_PICTURE = 100;
    ImageView imgCheque;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook_sign_up);
    }

    public void btnBackToMain(View view){
        startActivity(new Intent(CookSignUp.this, MainActivity.class));
    }

    public void btnCookToHome(View view){
        startActivity(new Intent(CookSignUp.this, Home.class));
    }

    protected void ChooseImageBtn(){
        Intent chooseVoidCheckPhoto = new Intent();
        chooseVoidCheckPhoto.setType("image/*");
        chooseVoidCheckPhoto.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(chooseVoidCheckPhoto, "Select Picture"), SELECT_PICTURE);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        if (resultCode == RESULT_OK){
            if (requestCode == SELECT_PICTURE){
                Uri photoURI = data.getData();
                if (photoURI != null){
                    imgCheque.setImageURI(photoURI);
                }
            }
        }
    }
}