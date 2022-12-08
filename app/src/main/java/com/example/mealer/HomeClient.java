package com.example.mealer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class HomeClient extends AppCompatActivity {

    private DatabaseReference dbRef;
    ListView listPurchaseRequests;
    DatabaseReference databasePurchaseRequests;
    List<PurchaseRequest> purchaseRequests;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_client);
        getWindow().setStatusBarColor(getResources().getColor(R.color.button_blue));

        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        dbRef = FirebaseDatabase.getInstance().getReference("Users")
                .child(userId);
        welcomeMessage();

        // createe recyclerView and populate with Purchase Requests

        listPurchaseRequests = (ListView) findViewById(R.id.listViewClientPurchaseRequests);

        databasePurchaseRequests = (DatabaseReference) FirebaseDatabase.getInstance().getReference("PurchaseRequests");

        purchaseRequests = new ArrayList<>();

    }

    @Override
    protected void onStart() {

        super.onStart();
        databasePurchaseRequests.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                purchaseRequests.clear();

                for (DataSnapshot postSnapshot : snapshot.getChildren()){

                    PurchaseRequest purchaseRequest = postSnapshot.getValue(PurchaseRequest.class);

                    purchaseRequests.add(purchaseRequest);
                }

                ClientPurchaseRequestList purchaseRequestAdapter = new ClientPurchaseRequestList(HomeClient.this, purchaseRequests);
                listPurchaseRequests.setAdapter(purchaseRequestAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void btnSearchMealsClick(View view){
        startActivity(new Intent(HomeClient.this, SearchMeal.class));

    }

    public void btnLogOutClick(View view) {
        startActivity(new Intent(HomeClient.this, MainActivity.class));

        FirebaseAuth.getInstance().signOut();
    }
    public void btnSearchClick(View view) {
        startActivity(new Intent(HomeClient.this, SearchMeal.class));
    }


    public void welcomeMessage() {
        dbRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                      DataSnapshot snapshot = task.getResult();
                        String name = snapshot.child("_name").getValue().toString();
                        TextView welcome = findViewById(R.id.greetingtxt);
                        welcome.setText("Welcome " + name);

                }
            }
        });
    }
}