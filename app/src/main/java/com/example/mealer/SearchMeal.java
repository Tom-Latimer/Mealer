package com.example.mealer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class SearchMeal extends AppCompatActivity {

    RecyclerView searchResultsRecycler;
    ArrayList<Meal_Class> results = new ArrayList<>();
    private EditText mealNameEdt, mealTypeEdt, cuisineTypeEdt;
    private RecyclerView recyclerView;
    private SearchAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_meal);

        searchResultsRecycler = findViewById(R.id.searchList);

        searchResultsRecycler.setHasFixedSize(true);
        searchResultsRecycler.setLayoutManager(new LinearLayoutManager(this));
        //still need to set the adapter
    }

    public void searchMeal(View view) {
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("Meals");

        recyclerView = (RecyclerView) findViewById(R.id.searchList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        mealNameEdt = findViewById(R.id.nameSearchBox);
        String mealName = mealNameEdt.getText().toString().trim();


        //get all children of dbRef
        adapter = new SearchAdapter(this, results);
        //clear adapter of all meals
        results.clear();
        recyclerView.setAdapter(adapter);
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot mealSnapshot : snapshot.getChildren()) {
                        for(DataSnapshot meals : mealSnapshot.getChildren()) {
                            Meal_Class meal = meals.getValue(Meal_Class.class);
                            if (meal.get_name().equals(mealName) || meal.get_Meal_type().equals(mealName) || meal.get_Cuisine_type().equals(mealName)) {
                                results.add(meal);
                            }


                        }
                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                RecyclerView adapter = findViewById(R.id.searchList);

            }
        });
    }

    public void btnBackClick(View view){
        startActivity(new Intent(SearchMeal.this, HomeClient.class));

    }

    public void requestMeal(Meal_Class meal, String clientName, String cookName, String pickupTime) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("MealRequests").child(meal.get_cookID());
        PurchaseRequest request = new PurchaseRequest(meal, clientName, cookName, pickupTime, "Pending");
        ref.push().setValue(request);
    }
}