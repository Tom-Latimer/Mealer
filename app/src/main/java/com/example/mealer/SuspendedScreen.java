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

        Intent receivedIntent = getIntent();
        String suspensionMessage = receivedIntent.getExtras().getString("suspensionMessage");
        TextView messageTextView = (TextView)findViewById(R.id.textViewSuspensionMessage);
        messageTextView.setText(suspensionMessage);

    }

    public void btnLogOutClick(View view) {
        startActivity(new Intent(SuspendedScreen.this, MainActivity.class));

        FirebaseAuth.getInstance().signOut();

    }
}