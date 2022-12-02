package com.example.mealer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Profile extends AppCompatActivity {

    private DatabaseReference dbRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        dbRef = FirebaseDatabase.getInstance().getReference("Users")
                .child(userId);
        setUpProfile();
    }


    public void setUpProfile(){
        dbRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    DataSnapshot snapshot = task.getResult();
                    String firstName = snapshot.child("_name").getValue().toString();
                    String lastName = snapshot.child("_last_name").getValue().toString();
                    String address= snapshot.child("_address").getValue().toString();
                    String country=snapshot.child("_country").getValue().toString();
                    String description=snapshot.child("description").getValue().toString();
                    TextView firstNameView = findViewById(R.id.firstNameView);
                    TextView lastNameView=findViewById(R.id.lastNameView);
                    TextView addressView=findViewById(R.id.addressView);
                    TextView countryView=findViewById(R.id.countryView);
                    TextView descriptionView=findViewById(R.id.descriptionView);
                    firstNameView.setText(firstName);
                    lastNameView.setText(lastName);
                    addressView.setText(address);
                    countryView.setText(country);
                    descriptionView.setText(description);

                }
            }
        });

    }
}