package com.example.mealer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

public class SuspendedScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suspended_screen);

        // if temporarily suspended
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        String uid = Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid();
        DatabaseReference userDetails = FirebaseDatabase.getInstance().getReference().child("Users").child(uid);
        userDetails.keepSynced(true);

        TextView messageTextView = (TextView)findViewById(R.id.textViewSuspensionMessage);
        userDetails.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Cook_Class suspendedCook = (Cook_Class) snapshot.getValue(Cook_Class.class);

                if(suspendedCook.get_suspension_date() != ""){
                    messageTextView.setText("You have been suspended until " + suspendedCook.get_suspension_date());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void btnLogOutClick(View view) {
        startActivity(new Intent(SuspendedScreen.this, MainActivity.class));

        FirebaseAuth.getInstance().signOut();

    }
}