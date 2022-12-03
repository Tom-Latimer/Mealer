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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PurchaseRequestPage extends AppCompatActivity {

    ListView listViewPurchaseRequests;
    DatabaseReference databasePurchaseRequests;
    List<PurchaseRequest> purchaseRequests;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_request_page);


        getWindow().setStatusBarColor(getResources().getColor(R.color.button_blue));

        databasePurchaseRequests = (DatabaseReference) FirebaseDatabase.getInstance().getReference("PurchaseRequests");

        listViewPurchaseRequests = (ListView) findViewById(R.id.listViewPurchaseRequests);

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

                PurchaseRequestList purchaseRequestAdapter = new PurchaseRequestList(PurchaseRequestPage.this, purchaseRequests);
                listViewPurchaseRequests.setAdapter(purchaseRequestAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    public void btnLogOutClick(View view){
        startActivity(new Intent(PurchaseRequestPage.this, MainActivity.class));

        FirebaseAuth.getInstance().signOut();
    }

    public void btnBackClick(View view){
        startActivity(new Intent(PurchaseRequestPage.this, HomeCook.class));

    }
}
