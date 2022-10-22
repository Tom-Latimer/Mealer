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
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
<<<<<<< HEAD:app/src/main/java/com/example/mealer/Home.java
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
=======
>>>>>>> 30719a815c12d0c72a237455a1d1a1366fecad49:app/src/main/java/com/example/mealer/HomeClient.java

public class HomeClient extends AppCompatActivity {

    private DatabaseReference dbRef;
    private FirebaseAuth mAuth;
    DatabaseReference databaseUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_client);

        /*
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        dbRef = FirebaseDatabase.getInstance().getReference("Users")
                .child(userId);
        welcomeMessage();

         */

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseUsers = database.getReference("Users");
        String id = mAuth.getCurrentUser().getUid();
        DatabaseReference username = databaseUsers.child(id).child("type");

        username.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String type = dataSnapshot.getValue().toString();
                TextView greetingText= (TextView) findViewById(R.id.greetingtxt);
//              greetingText.setText("You are logged in as "+ type);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void btnLogOutClick(View view){
        startActivity(new Intent(HomeClient.this, MainActivity.class));

            FirebaseAuth.getInstance().signOut();
    }




//    public void welcomeMessage(View view) {
//        dbRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DataSnapshot> task) {
//                if (task.isSuccessful()) {
//                    User_Class user = (User_Class) task.getResult().getValue();
//                    String type = user.get_type();
//                    TextView greetingText= (TextView) findViewById(R.id.greetingtxt);
//                    greetingText.setText("You are logged in as "+ type);
//
//                }
//            }
//        });
//    }




}