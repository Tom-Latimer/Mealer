package com.example.mealer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;


public class SearchMeal extends AppCompatActivity {

    RecyclerView searchResultsRecycler;
    List<Meal_Class> results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_meal);

        searchResultsRecycler = findViewById(R.id.searchList);

        searchResultsRecycler.setHasFixedSize(true);
        searchResultsRecycler.setLayoutManager(new LinearLayoutManager(this));
        //still need to set the adapter
    }

    public void requestMeal(Meal_Class meal, String clientName, String cookName, String pickupTime) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("MealRequests").child(meal.get_cookID());
        PurchaseRequest request = new PurchaseRequest(meal, clientName, cookName, pickupTime, "Pending");
        ref.push().setValue(request);s
    }
}