package com.example.mealer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddMeal extends AppCompatActivity {

    private DatabaseReference databaseUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meal);
    }

    public void btnBackClick(View view){
        startActivity(new Intent(AddMeal.this, Menu.class));

    }

    public static boolean isDigit(String string) {
        try {
            Double.parseDouble(string);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    public void btnAddMealClick(View view){

        EditText txtMealName = (EditText) findViewById(R.id.mealName);
        String mealName = txtMealName.getText().toString().trim();

        EditText txtMealType = (EditText) findViewById(R.id.mealType);
        String mealType = txtMealType.getText().toString().trim();

        EditText txtCuisineType = (EditText) findViewById(R.id.cuisineType);
        String cuisineType = txtCuisineType.getText().toString().trim();

        EditText txtIngredients = (EditText) findViewById(R.id.ingredients);
        String ingredients = txtIngredients.getText().toString().trim();

        EditText txtAllergens = (EditText) findViewById(R.id.allergens);
        String allergens = txtAllergens.getText().toString().trim();

        EditText txtPrice = (EditText) findViewById(R.id.price);
        String price = txtPrice.getText().toString().trim();

        EditText txtDescription = (EditText) findViewById(R.id.mealDescription);
        String description = txtDescription.getText().toString().trim();

        Boolean isAcceptable = true;

        String mealNameError = "";
        if (mealName.isEmpty()) {
            mealNameError = "Meal Name Required";
            txtMealName.setError(mealNameError);
            txtMealName.requestFocus();
            isAcceptable = false;
        }

        String mealTypeError = "";
        if (mealType.isEmpty()) {
            mealTypeError = "Meal Type Required";
            txtMealType.setError(mealTypeError);
            txtMealType.requestFocus();
            isAcceptable = false;
        }

        String cuisineTypeError = "";
        if (cuisineType.isEmpty()) {
            cuisineTypeError = "Cuisine Type Required";
            txtCuisineType.setError(cuisineTypeError);
            txtCuisineType.requestFocus();
            isAcceptable = false;
        }

        String ingredientsError = "";
        if (ingredients.isEmpty()) {
            ingredientsError = "Ingredients Required";
            txtIngredients.setError(ingredientsError);
            txtIngredients.requestFocus();
            isAcceptable = false;
        }

        String allergensError = "";
        if (allergens.isEmpty()) {
            allergensError = "Allergens Required";
            txtAllergens.setError(allergensError);
            txtAllergens.requestFocus();
            isAcceptable = false;
        }

        String priceError = "";
        if (price.isEmpty()) {
            priceError = "Price Required";
            txtPrice.setError(priceError);
            txtPrice.requestFocus();
            isAcceptable = false;
        }
        if (!(isDigit(price))){
            priceError = "Price should be a numeric value";
            txtPrice.setError(priceError);
            txtPrice.requestFocus();
            isAcceptable = false;
        }

        String descriptionError = "";
        if (description.isEmpty()) {
            descriptionError = "Description Required";
            txtDescription.setError(descriptionError);
            txtDescription.requestFocus();
            isAcceptable = false;
        }


        if (isAcceptable){

            Cook_Class cook = new Cook_Class();
            cook.add_meal(new Meal_Class(mealName, mealType, cuisineType, ingredients, allergens, price, description));
            startActivity(new Intent(AddMeal.this, Menu.class));

        }

    }
}