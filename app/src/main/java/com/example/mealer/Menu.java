package com.example.mealer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Menu extends AppCompatActivity {

    ListView listViewMeals;
    DatabaseReference databaseMeals;
    List<Meal_Class> meals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        databaseMeals = (DatabaseReference) FirebaseDatabase.getInstance().getReference("Meals");

        listViewMeals = (ListView) findViewById(R.id.menuListView);

        meals = new ArrayList<>();

        listViewMeals.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int item, long l) {
                Meal_Class meal = meals.get(item);
                showActionMealDialog(meal.get_mealID(), meal.get_name(), meal.get_price(), meal.get_description());
                return true;
            }
        });

    }

    @Override
    protected void onStart() {

        super.onStart();
        databaseMeals.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                //Cook_Class cook = new Cook_Class();
                meals.clear();
                //meals = cook.get_meals(snapshot);

                for (DataSnapshot postSnapshot : snapshot.getChildren()){

                    Meal_Class meal = postSnapshot.getValue(Meal_Class.class);

                    meals.add(meal);
                }

                MealList mealsAdapter = new MealList(Menu.this, meals);
                listViewMeals.setAdapter(mealsAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    public void btnLogOutClick(View view){
        startActivity(new Intent(Menu.this, MainActivity.class));

        FirebaseAuth.getInstance().signOut();
    }

    public void btnAddMealClick(View view){
        startActivity(new Intent(Menu.this, AddMeal.class));

    }


    private void showActionMealDialog(final String mealId,  String mealPrice,String description, String mealName){

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.activity_meal_description, null);
        dialogBuilder.setView(dialogView);

        final TextView txtMealPrice = (TextView) dialogView.findViewById(R.id.textViewPrice);
        final TextView txtMealDescription = (TextView) dialogView.findViewById(R.id.textViewDescription);
        final TextView txtMealName=(TextView) dialogView.findViewById(R.id.textViewName);
        final Button btnDelete = (Button) dialogView.findViewById(R.id.buttonDelete);
        final Button btnOfferMeal = (Button) dialogView.findViewById(R.id.offerMealBtn);


        String title = "Meal name: " + mealId;
        dialogBuilder.setTitle(title);
        txtMealName.setText(mealName);
        txtMealPrice.setText(mealPrice);
        txtMealDescription.setText(description);

        final AlertDialog builder = dialogBuilder.create();
        builder.show();

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteMeal(mealId);
                builder.dismiss();
            }
        });

        btnOfferMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleOfferMeal(mealId);
                builder.dismiss();
            }
        });
    }

    private void deleteMeal(String id) {

        DatabaseReference dR = (DatabaseReference) FirebaseDatabase.getInstance().getReference("Meals").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(id);
        //create a snapshot and check if the meal is offered
        dR.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Meal_Class meal = snapshot.getValue(Meal_Class.class);
                if(meal.isOffered()){
                    Toast.makeText(Menu.this, "You can't delete a meal that is offered", Toast.LENGTH_LONG).show();
                }
                else{
                    DatabaseReference dR = (DatabaseReference) FirebaseDatabase.getInstance().getReference("Meals").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(id);
                    dR.removeValue();
                    Toast.makeText(Menu.this, "Meal deleted", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private boolean toggleOfferMeal(String id) {
        DatabaseReference dR = (DatabaseReference) FirebaseDatabase.getInstance().getReference("Meals").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(id);

        dR.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Meal_Class meal = (Meal_Class) snapshot.getValue(Meal_Class.class);
                if (meal.isOffered()) {
                    meal.set_offered(false);
                } else {
                    meal.set_offered(true);
                }
                dR.setValue(meal);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("TAG",error.getMessage());
            }
        });

        return true;
    }

}