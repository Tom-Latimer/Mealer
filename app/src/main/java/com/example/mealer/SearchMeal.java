package com.example.mealer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class SearchMeal extends AppCompatActivity implements SearchAdapter.OnInfoListener {
    boolean suspended = false;
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
        adapter = new SearchAdapter(this, results,this);
        //clear adapter of all meals
        results.clear();
        recyclerView.setAdapter(adapter);
        //get the cookid of the meal and turn it into a string
        DatabaseReference dbRef2 = FirebaseDatabase.getInstance().getReference("Users");



        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot mealSnapshot : snapshot.getChildren()) {
                        for(DataSnapshot meals : mealSnapshot.getChildren()) {
                            Meal_Class meal = meals.getValue(Meal_Class.class);
                            String cookId = meal.get_cookID();


                            dbRef2.child(cookId).child("_suspended").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if (!results.isEmpty() && results.contains(meal)) {
                                        if (!results.get(results.indexOf(meal)).isOffered()) {
                                            if (snapshot.getValue().toString().equals("true")) {
                                                results.remove(meal);
                                            }
                                        }

                                        adapter.notifyDataSetChanged();
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });

                            if (meal.get_name().equals(mealName) || meal.get_Meal_type().equals(mealName) || meal.get_Cuisine_type().equals(mealName)) {
                                results.add(meal);
                            }


                        }
                    }

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

    public void requestMeal(Meal_Class meal, String cookName, String pickupTime) {
        //reference to push meal request
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("PurchaseRequests").child(meal.get_cookID());
        //current user
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        //Reference to get current users name
        DatabaseReference clientRef = FirebaseDatabase.getInstance().getReference("Users").child(user.getUid());
        clientRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Client_Class client = snapshot.getValue(Client_Class.class);
                PurchaseRequest request = new PurchaseRequest(meal, snapshot.getKey(), cookName, pickupTime, "Pending");
                String id = ref.push().getKey();
                request.setPurchaseRequestID(id);
                ref.child(id).setValue(request);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("TAG",error.getMessage());
            }
        });

    }

    @Override
    public void onInfoClick(int position) {
       Meal_Class meal =  results.get(position);

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.search_result_item, null);
        dialogBuilder.setView(dialogView);

        final TextView txtCookName = dialogView.findViewById(R.id.searchViewCookName);
        final TextView txtCookRating = dialogView.findViewById(R.id.searchViewCookRating);
        final TextView txtMealName = dialogView.findViewById(R.id.searchViewMealName);
        final TextView txtMealType = dialogView.findViewById(R.id.searchViewMealType);
        final TextView txtCuisineType = dialogView.findViewById(R.id.searchViewCuisineType);
        final TextView txtIngredients = dialogView.findViewById(R.id.searchViewIngredients);
        final TextView txtAllergens = dialogView.findViewById(R.id.searchViewAllergens);
        final TextView txtMealPrice = dialogView.findViewById(R.id.searchViewPrice);
        final TextView txtMealDescription = dialogView.findViewById(R.id.searchViewDescription);
        final EditText pickupTimeBox = dialogView.findViewById(R.id.pickUpTimeEditText);
        final AppCompatButton purchaseMealBtn = dialogView.findViewById(R.id.purchaseMealBtn);

        String title = "Meal ID: " + meal.get_mealID();
        dialogBuilder.setTitle(title);
        txtMealName.setText(meal.get_name());
        txtMealPrice.setText(meal.get_price());
        txtMealDescription.setText(meal.get_description());
        txtMealType.setText(meal.get_Meal_type());
        txtCuisineType.setText(meal.get_Cuisine_type());
        txtIngredients.setText(meal.get_ingredients());
        txtAllergens.setText(meal.get_allergens());

        String pickupTime = pickupTimeBox.getText().toString().trim();

        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("Users").child(meal.get_cookID());
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Cook_Class cook = (Cook_Class) snapshot.getValue(Cook_Class.class);
                txtCookRating.setText(String.valueOf(cook.get_average_rating()));
                txtCookName.setText(cook.get_name());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        final AlertDialog builder = dialogBuilder.create();
        builder.show();

        purchaseMealBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestMeal(meal,txtCookName.getText().toString().trim(),pickupTime);
                builder.dismiss();
            }
        });
    }
}