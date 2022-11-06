package com.example.mealer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomeClient extends AppCompatActivity {

    private DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_client);


        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        dbRef = FirebaseDatabase.getInstance().getReference("Users")
                .child(userId);
        welcomeMessage();


    }

    public void btnLogOutClick(View view) {
        startActivity(new Intent(HomeClient.this, MainActivity.class));

        FirebaseAuth.getInstance().signOut();
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