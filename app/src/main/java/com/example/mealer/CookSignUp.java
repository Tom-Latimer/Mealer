package com.example.mealer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class CookSignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook_sign_up);
    }

    protected void ChooseImageBtn(){
        Intent chooseVoidCheckPhoto = new Intent(Intent.ACTION_PICK);
        chooseVoidCheckPhoto.setType("image/*");
        startActivityForResult(chooseVoidCheckPhoto, RESULT_LOAD_IMG);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){

        if (resultCode == RESULT_OK) {
            try {
                final Uri photoURI = data.getData();
                final InputStream photoInputStream = getContentResolver().openInputStream(photoURI);
                final Bitmap photoCheque = BitmapFactory.decodeStream(photoInputStream);
                imgCheque.setImageBitmap(photoCheque);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(PostImage.this, "There was an issue.", Toast.LENGTH_LONG).show();
            }

        }else {
            Toast.makeText(PostImage.this, "No image was selected",Toast.LENGTH_LONG).show();
        }
    }
}